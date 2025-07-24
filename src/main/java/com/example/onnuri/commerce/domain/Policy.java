package com.example.onnuri.commerce.domain;

import com.example.onnuri.commerce.common.enums.FileType;
import lombok.Getter;

@Getter
public class Policy extends BaseFile{

    private String ruleName;

    private Policy(FileType fileType, String ruleName) {
        super(fileType);
        this.ruleName = ruleName;
    }

    public static Policy generateRule(
            final String ruleName
    ) {
        return new Policy(FileType.RULE, ruleName);
    }
}
