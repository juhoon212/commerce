package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.application.mapper.GetClassifiedResultsMapper;
import com.example.onnuri.commerce.infrastructure.dto.GetClassifiedResultDto;
import com.example.onnuri.commerce.infrastructure.persistence.repository.AccountRepository;
import com.example.onnuri.commerce.web.vo.GetClassifiedResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetClassifiedResultService {

    private final AccountRepository accountRepository;
    private final GetClassifiedResultsMapper getClassifiedResultsMapper;

    public List<GetClassifiedResultResponse> getClassifiedResults(final String companyId) {
        log.info("Fetching classified results from the database.");
        final List<GetClassifiedResultDto> classifiedResults = accountRepository.findClassifiedResult(companyId);
        log.info("Classified results fetched: {}", classifiedResults);

        return getClassifiedResultsMapper.mapToGetClassifiedResultResponse(classifiedResults);
    }
}
