package com.example.onnuri.commerce.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryPolicyDto {
    private String categoryId;
    private String categoryName;
    private List<String> keywords;
}
