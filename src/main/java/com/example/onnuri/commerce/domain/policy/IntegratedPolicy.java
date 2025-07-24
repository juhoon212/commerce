package com.example.onnuri.commerce.domain.policy;

import lombok.Getter;

/**
 * json row처럼 하나의 객체로 만드는 클래스
 */
@Getter
public class IntegratedPolicy {

    private final String companyId;
    private final String companyName;
    private final String categoryId;
    private final String categoryName;
    private final String keyword;

    private IntegratedPolicy(String companyId, String companyName, String categoryId, String categoryName, String keyword) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.keyword = keyword;
    }

    public static IntegratedPolicy generateIntegratedPolicy(
            String companyId,
            String companyName,
            String categoryId,
            String categoryName,
            String keyword
    ) {
        return new IntegratedPolicy(companyId, companyName, categoryId, categoryName, keyword);
    }
}
