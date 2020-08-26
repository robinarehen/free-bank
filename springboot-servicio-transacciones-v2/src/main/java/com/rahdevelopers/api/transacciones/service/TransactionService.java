package com.rahdevelopers.api.transacciones.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.rahdevelopers.api.transacciones.dto.ResponseDto;
import com.rahdevelopers.api.transacciones.dto.TransactionDto;

public interface TransactionService {

	public List<TransactionDto> getAll(Pageable pageable);
	
	public TransactionDto create(TransactionDto dto);
	
	public ResponseDto getByReference(String reference, String channel);
	
	public List<TransactionDto> getByAccountIban(String accountIban, String order);
}
