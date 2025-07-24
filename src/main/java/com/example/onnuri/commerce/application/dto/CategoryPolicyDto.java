package com.example.onnuri.commerce.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryPolicyDto {
    private String categoryId;
    private String categoryName;
    private List<String> keywords;
}
