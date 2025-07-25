package com.example.onnuri.commerce.core.helper;

import com.example.onnuri.commerce.core.domain.policy.CategoryPolicy;
import com.example.onnuri.commerce.core.domain.policy.CompanyListPolicy;
import com.example.onnuri.commerce.core.domain.policy.CompanyPolicy;
import com.example.onnuri.commerce.core.domain.policy.IntegratedPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class PolicyClassifier {

    public List<IntegratedPolicy> classifyPolicy(final CompanyListPolicy policy) {
        return policy.getCompanies()
                .stream()
                .flatMap(this::classifyCompanyPolicy)
                .toList();
    }

    private Stream<IntegratedPolicy> classifyCompanyPolicy(final CompanyPolicy policy) {
        return policy.getCategories()
                .stream()
                .flatMap(categoryPolicy -> classifyCategoryPolicy(policy, categoryPolicy));
    }

    private Stream<IntegratedPolicy> classifyCategoryPolicy(final CompanyPolicy companyPolicy, final CategoryPolicy policy) {
        return policy.getKeywords()
                .stream()
                .map(keyword -> IntegratedPolicy.generateIntegratedPolicy(
                        companyPolicy.getCompanyId(),
                        companyPolicy.getCompanyName(),
                        policy.getCategoryId(),
                        policy.getCategoryName(),
                        keyword
                ));
    }
}
