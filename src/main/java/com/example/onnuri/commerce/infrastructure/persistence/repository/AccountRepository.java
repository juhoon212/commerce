package com.example.onnuri.commerce.infrastructure.persistence.repository;

import com.example.onnuri.commerce.infrastructure.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>, AccountRepositoryCustom {

}
