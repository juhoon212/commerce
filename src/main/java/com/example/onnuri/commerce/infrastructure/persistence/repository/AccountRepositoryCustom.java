package com.example.onnuri.commerce.infrastructure.persistence.repository;

import com.example.onnuri.commerce.infrastructure.dto.GetClassifiedResultDto;

import java.util.List;

public interface AccountRepositoryCustom {

    List<GetClassifiedResultDto> findClassifiedResult(String companyId);
}
