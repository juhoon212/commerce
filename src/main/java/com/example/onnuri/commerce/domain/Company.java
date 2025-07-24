package com.example.onnuri.commerce.domain;

import lombok.Data;

import java.util.List;

@Data
public class Company {

    private String companyId;
    private String companyName;
    private List<Category> categories;
}
