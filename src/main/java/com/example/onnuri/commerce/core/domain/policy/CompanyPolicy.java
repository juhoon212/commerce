package com.example.onnuri.commerce.core.domain.policy;

import com.example.onnuri.commerce.core.dto.CategoryPolicyDto;
import com.example.onnuri.commerce.core.dto.CompanyPolicyDto;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CompanyPolicy {

    private final String companyId;
    private final String companyName;
    private final List<CategoryPolicy> categories;

    private CompanyPolicy(String companyId, String companyName, List<CategoryPolicy> categories) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.categories = categories;
    }

    public static CompanyPolicy generateCompanyPolicy(final CompanyPolicyDto dto) {
        final List<CategoryPolicyDto> dtoCategories = dto.getCategories();

        final List<CategoryPolicy> categories = dtoCategories.stream()
                .map(it -> {
                    return CategoryPolicy.generateCategoryPolicy(
                            it.getCategoryId(),
                            it.getCategoryName(),
                            it.getKeywords()
                    );
                })
                .toList();

        return new CompanyPolicy(dto.getCompanyId(), dto.getCompanyName(), categories);
    }
}
