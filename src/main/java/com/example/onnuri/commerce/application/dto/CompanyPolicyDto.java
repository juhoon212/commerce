package com.example.onnuri.commerce.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyPolicyDto {

    private String companyId;
    private String companyName;
    private List<CategoryPolicyDto> categories;
}
