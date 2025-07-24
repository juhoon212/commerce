package com.example.onnuri.commerce.web;

import com.example.onnuri.commerce.exception.NotFoundFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static com.example.onnuri.commerce.util.StringUtil.*;

@Slf4j
@Component
public class FileValidator {
    public void validateFile(final MultipartFile file) {
        if (file.isEmpty()) {
            throw new NotFoundFileException("해당 파일이 비어있습니다.");
        }

        final String originalFilename = file.getOriginalFilename();

        if (originalFilename == null) {
            throw new NotFoundFileException("File 이름이 비어있습니다.");
        }

        // csv랑 json 파일만 들어온다는 전제 하에
        if (!originalFilename.endsWith(CSV_FILE_PREFIX) && !originalFilename.endsWith(JSON_FILE_PREFIX)) {
            log.error("파일 형식이 맞지 않습니다. 들어온 파일 이름 = {}, 형식 : {}", file.getOriginalFilename(), file.getContentType());
        }
    }
}
