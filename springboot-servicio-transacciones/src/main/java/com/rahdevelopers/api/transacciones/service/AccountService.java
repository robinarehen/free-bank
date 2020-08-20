package com.rahdevelopers.api.transacciones.service;

import com.rahdevelopers.api.transacciones.dto.AccountDto;

public interface AccountService {

	public AccountDto create(AccountDto dto);

	public AccountDto getByAccountIban(String accountIban);
}
