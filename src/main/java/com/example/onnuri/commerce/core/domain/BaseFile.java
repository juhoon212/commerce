package com.example.onnuri.commerce.core.domain;

import com.example.onnuri.commerce.common.enums.FileType;

public abstract class BaseFile {
    private final FileType fileType;

    public BaseFile(FileType fileType) {
        this.fileType = fileType;
    }
}
