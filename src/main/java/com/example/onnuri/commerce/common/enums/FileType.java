package com.example.onnuri.commerce.common.enums;

public enum FileType {

    ACCOUNT("account", "장부 파일"),
    RULE("rule", "규칙 적용 파일");

    private final String name;
    private final String description;

    FileType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
