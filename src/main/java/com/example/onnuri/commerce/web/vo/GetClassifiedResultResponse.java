package com.example.onnuri.commerce.web.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetClassifiedResultResponse(
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
) {
}
