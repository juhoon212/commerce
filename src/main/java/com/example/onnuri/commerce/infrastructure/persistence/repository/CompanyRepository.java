package com.example.onnuri.commerce.infrastructure.persistence.repository;

import com.example.onnuri.commerce.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {
}
