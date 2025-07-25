package com.example.onnuri.commerce.core.mapper;

import com.example.onnuri.commerce.infrastructure.dto.GetClassifiedResultDto;
import com.example.onnuri.commerce.web.vo.GetClassifiedResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetClassifiedResultsMapper {

    public List<GetClassifiedResultResponse> mapToGetClassifiedResultResponse(final List<GetClassifiedResultDto> results) {
        return results.stream()
                .map(result ->
                        new GetClassifiedResultResponse(
                                result.accountId(),
                                result.companyId(),
                                result.keyword(),
                                result.transactionDateTime(),
                                result.description(),
                                result.depositAmount(),
                                result.withdrawAmount(),
                                result.balanceAfterTransaction(),
                                result.transactionBranch(),
                                result.isMatched(),
                                result.categoryId(),
                                result.categoryName()
                        ))
                .toList();
    }
}
