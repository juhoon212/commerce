package com.example.onnuri.commerce.application.mapper;

import com.example.onnuri.commerce.application.dto.CompanyListPolicyDto;
import com.example.onnuri.commerce.application.dto.CompanyPolicyDto;
import com.example.onnuri.commerce.domain.policy.CompanyListPolicy;
import com.example.onnuri.commerce.domain.policy.CompanyPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PolicyJsonMapper {

    public CompanyListPolicy mapToCompanyListPolicy(final CompanyListPolicyDto companyListPolicyDto) {

        log.info("mapToCompanyListPolicy() called with companyListPolicyDto: {}", companyListPolicyDto);

        final List<CompanyPolicy> companyPolicies = companyListPolicyDto.getCompanies()
                .stream()
                .map(CompanyPolicy::generateCompanyPolicy)
                .toList();

        return CompanyListPolicy.generateCategoryListPolicy(companyPolicies);
    }
}
