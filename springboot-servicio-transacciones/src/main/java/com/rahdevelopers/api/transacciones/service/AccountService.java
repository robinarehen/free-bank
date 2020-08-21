package com.rahdevelopers.api.transacciones.service;

import java.util.Optional;

import com.rahdevelopers.api.transacciones.dto.AccountDto;

public interface AccountService {

	public AccountDto create(AccountDto dto);

	public Optional<AccountDto> getByAccountIban(String accountIban);
}
