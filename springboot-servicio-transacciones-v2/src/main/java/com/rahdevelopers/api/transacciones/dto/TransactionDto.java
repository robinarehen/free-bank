package com.rahdevelopers.api.transacciones.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionDto {

	private Long Id;
	private String reference;
	private String accountIban;
	private Double amount;
	private LocalDateTime date;
	private Double fee;
	private String description;
	
}
