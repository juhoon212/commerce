package com.example.onnuri.commerce.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyPolicyDto {

    private String companyId;
    private String companyName;
    private List<CategoryPolicyDto> categories;
}
