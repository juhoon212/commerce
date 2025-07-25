package com.example.onnuri.commerce.core.domain.policy;

import com.example.onnuri.commerce.common.enums.FileType;
import com.example.onnuri.commerce.core.domain.BaseFile;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Policy extends BaseFile {

    private final CompanyListPolicy companyListPolicy;

    private Policy(FileType fileType, CompanyListPolicy companyListPolicy) {
        super(fileType);
        this.companyListPolicy = companyListPolicy;
    }

    public static Policy generatePolicy(
            final CompanyListPolicy companyListPolicy
    ) {
        return new Policy(FileType.RULE, companyListPolicy);
    }
}
