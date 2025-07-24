package com.example.onnuri.commerce.domain;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    private String categoryId;
    private String categoryName;
    private List<String> keywords;
}
