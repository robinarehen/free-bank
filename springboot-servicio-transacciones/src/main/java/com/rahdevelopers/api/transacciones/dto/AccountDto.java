package com.rahdevelopers.api.transacciones.dto;

import lombok.Data;

@Data
public class AccountDto {

	private Long Id;
	private String accountIban;
	private Double amount;
}
