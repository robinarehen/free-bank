package com.rahdevelopers.api.transacciones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahdevelopers.api.transacciones.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	Optional<AccountEntity> findByAccountIban(String accountIban);

}
