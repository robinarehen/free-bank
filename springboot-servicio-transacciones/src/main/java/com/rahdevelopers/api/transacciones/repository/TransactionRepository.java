package com.rahdevelopers.api.transacciones.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rahdevelopers.api.transacciones.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

	Optional<TransactionEntity> findByReference(String reference);

	List<TransactionEntity> findByAccountIban(String accountIban, Pageable pageable);

}
