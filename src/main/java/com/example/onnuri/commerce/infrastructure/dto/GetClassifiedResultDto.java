package com.example.onnuri.commerce.infrastructure.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetClassifiedResultDto(
    Long accountId,
    String companyId,
    String keyword,
    LocalDateTime transactionDateTime,
    String description,
    BigDecimal depositAmount,
    BigDecimal withdrawAmount,
    BigDecimal balanceAfterTransaction,
    String transactionBranch,
    Boolean isMatched,
    String categoryId,
    String categoryName
) {}
