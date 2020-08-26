package com.rahdevelopers.api.transacciones.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahdevelopers.api.transacciones.dto.AccountDto;
import com.rahdevelopers.api.transacciones.entity.AccountEntity;
import com.rahdevelopers.api.transacciones.repository.AccountRepository;
import com.rahdevelopers.api.transacciones.util.ObjectMapperUtil;

@Service
public class AccountServiceImpl implements AccountService {

	private ObjectMapperUtil mapperUtil;
	private AccountRepository repository;

	@Autowired
	public AccountServiceImpl(ObjectMapperUtil mapperUtil, AccountRepository repository) {
		super();
		this.mapperUtil = mapperUtil;
		this.repository = repository;
	}

	@Override
	public AccountDto create(AccountDto dto) {
		// TODO Auto-generated method stub
		AccountEntity entity = this.mapperUtil.dtoToEntity(AccountEntity.class, dto);
		return this.mapperUtil.entityToDto(AccountDto.class, this.repository.save(entity));
	}

	@Override
	public Optional<AccountDto> getByAccountIban(String accountIban) {
		// TODO Auto-generated method stub
		return this.repository.findByAccountIban(accountIban).map( entity -> {
			return this.mapperUtil.entityToDto(AccountDto.class, entity);
		});
	}

}
