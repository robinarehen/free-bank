package com.rahdevelopers.api.transacciones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class ResponseDto {

	private String reference;
	private String status;
	@JsonInclude(Include.NON_NULL)
	private Double amount;
	@JsonInclude(Include.NON_NULL)
	private Double fee;
}
