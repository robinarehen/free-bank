package com.rahdevelopers.api.transacciones.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rahdevelopers.api.transacciones.dto.AccountDto;
import com.rahdevelopers.api.transacciones.dto.ResponseDto;
import com.rahdevelopers.api.transacciones.dto.TransactionDto;
import com.rahdevelopers.api.transacciones.entity.TransactionEntity;
import com.rahdevelopers.api.transacciones.repository.TransactionRepository;
import com.rahdevelopers.api.transacciones.util.ObjectMapperUtil;
import com.rahdevelopers.api.transacciones.util.StatusUtil;

@Service
public class TransactionServiceImpl implements TransactionService {

	private ObjectMapperUtil mapperUtil;
	private TransactionRepository repository;
	private AccountService accountService;

	@Autowired
	public TransactionServiceImpl(ObjectMapperUtil mapperUtil, TransactionRepository repository,
			AccountService accountService) {
		super();
		this.mapperUtil = mapperUtil;
		this.repository = repository;
		this.accountService = accountService;
	}

	@Override
	public List<TransactionDto> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.repository.findAll(pageable).stream().map(entity -> {
			return this.mapperUtil.entityToDto(TransactionDto.class, entity);
		}).collect(Collectors.toList());
	}

	@Override
	public TransactionDto create(TransactionDto dto) {
		// TODO Auto-generated method stub
		this.createUpdateAccount(dto);

		if (this.repository.findByReference(dto.getReference()).isPresent()) {
			throw new IllegalArgumentException("La referencia ya existe");
		}

		TransactionEntity entity = this.mapperUtil.dtoToEntity(TransactionEntity.class, dto);
		return this.mapperUtil.entityToDto(TransactionDto.class, this.repository.save(entity));
	}

	@Override
	public ResponseDto getByReference(String reference, String channel) {
		// TODO Auto-generated method stub
		return this.getResponseDto(reference, channel);
	}

	@Override
	public List<TransactionDto> getByAccountIban(String accountIban, String order) {
		// TODO Auto-generated method stub
		Sort sortOrder = Sort.by("amount").ascending();
		if (order != null && order.equals("DESC")) {
			sortOrder = Sort.by("amount").descending();
		}
		Pageable pageable = PageRequest.of(0, 10, sortOrder);
		return this.repository.findByAccountIban(accountIban, pageable).stream().map(entity -> {
			return this.mapperUtil.entityToDto(TransactionDto.class, entity);
		}).collect(Collectors.toList());
	}

	private void createUpdateAccount(TransactionDto dto) {
		Optional<AccountDto> accountDto = this.accountService.getByAccountIban(dto.getAccountIban());

		AccountDto accountSave = accountDto.map(account -> {
			Double amount = dto.getAmount();
			if (amount > 0) {
				amount += account.getAmount();
			} else if (account.getAmount() < Math.abs(dto.getAmount())) {
				// Math.abs : change a negative number to positive number
				throw new IllegalArgumentException("Saldo Insuficiente para un debito.");
			}
			amount = account.getAmount() - Math.abs(dto.getAmount());
			account.setAmount(amount);
			return account;
		}).orElseGet(() -> {
			Double amount = dto.getAmount();
			AccountDto account = new AccountDto();
			account.setAccountIban(dto.getAccountIban());
			if (dto.getAmount() < 0) {
				throw new IllegalArgumentException("Una cuenta nueva no puede inicar con un debito.");
			}
			account.setAmount(amount);
			return account;
		});
		this.accountService.create(accountSave);
	}

	private ResponseDto getResponseDto(String reference, String channel) {

		ResponseDto responseDto = new ResponseDto();
		Optional<TransactionEntity> entity = this.repository.findByReference(reference);

		if (!entity.isPresent()) {
			responseDto.setReference(reference);
			responseDto.setStatus(StatusUtil.INVALID.getValue());
			return responseDto;
		}

		LocalDate transactionDate = entity.get().getDate().toLocalDate();

		switch (channel) {
		case "CLIENT":
		case "ATM":
			return entity.map(values -> {
				responseDto.setReference(reference);
				responseDto.setAmount(values.getAmount());
				responseDto.setStatus(this.getStatus(transactionDate));
				return responseDto;
			}).get();
		case "INTERNAL":
			return entity.map(values -> {
				responseDto.setReference(reference);
				responseDto.setAmount(values.getAmount());
				responseDto.setFee(values.getFee());
				responseDto.setStatus(this.getStatus(transactionDate));
				return responseDto;
			}).get();
		}

		throw new IllegalArgumentException("invalid channel");
	}

	private String getStatus(LocalDate transactionDate) {
		if (transactionDate.isBefore(LocalDate.now())) {
			return StatusUtil.SETTLED.getValue();
		}
		if (transactionDate.isEqual(LocalDate.now())) {
			return StatusUtil.PENDING.getValue();
		}
		if (transactionDate.isAfter(LocalDate.now())) {
			return StatusUtil.FUTURE.getValue();
		}
		return StatusUtil.INVALID.getValue();
	}

}
