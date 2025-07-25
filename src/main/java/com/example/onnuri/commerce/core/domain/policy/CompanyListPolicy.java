package com.example.onnuri.commerce.core.domain.policy;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CompanyListPolicy {

    private final List<CompanyPolicy> companies;

    private CompanyListPolicy(List<CompanyPolicy> companies) {
        this.companies = companies;
    }

    public static CompanyListPolicy generateCategoryListPolicy(final List<CompanyPolicy> companyPolicies) {
        return new CompanyListPolicy(companyPolicies);
    }
}
