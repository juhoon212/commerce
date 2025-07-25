package com.example.onnuri.commerce.core.domain.policy;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CategoryPolicy {
    private final String categoryId;
    private final String categoryName;
    private final List<String> keywords;

    private CategoryPolicy(String categoryId, String categoryName, List<String> keywords) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.keywords = keywords;
    }

    public static CategoryPolicy generateCategoryPolicy(
            String categoryId,
            String categoryName,
            List<String> keywords
    ) {
        return new CategoryPolicy(categoryId, categoryName, keywords);
    }
}
