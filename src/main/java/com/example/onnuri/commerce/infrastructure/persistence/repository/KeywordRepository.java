package com.example.onnuri.commerce.infrastructure.persistence.repository;

import com.example.onnuri.commerce.infrastructure.persistence.entity.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<KeywordEntity, Long> {
}
