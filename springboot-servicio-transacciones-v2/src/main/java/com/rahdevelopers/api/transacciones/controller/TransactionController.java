package com.rahdevelopers.api.transacciones.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rahdevelopers.api.transacciones.dto.TransactionDto;
import com.rahdevelopers.api.transacciones.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService service;

	@GetMapping("/{page}/{pageSize}")
	public ResponseEntity<List<TransactionDto>> getAll(@PathVariable Integer page, @PathVariable Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return ResponseEntity.ok(this.service.getAll(pageable));
	}

	@PostMapping
	public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(dto));
	}

	@GetMapping("/search")
	public ResponseEntity<?> getByField(@RequestParam Map<String, String> params) {
		String reference = params.get("reference");
		String channel = params.get("channel");
		if (reference != null) {
			return ResponseEntity.ok(this.service.getByReference(reference, channel));
		}

		String accountIban = params.get("accountIban");
		String order = params.getOrDefault("order", "ASC");
		if (accountIban == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok(this.service.getByAccountIban(accountIban, order));
	}
}
