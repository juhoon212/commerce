package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.application.dto.ClassifyDataFileResultWrapper;
import com.example.onnuri.commerce.domain.MatchedAccountingPolicySet;
import com.example.onnuri.commerce.domain.account.BankTransactionRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoFileDataGenerateService {

    private final FileDataStoreGenerator fileDataStoreGenerator;
    private final SaveClassifiedDataService saveClassifiedDataService;

    public void generateFileData(final String key) {
        // 이 제네레이터에서 매칭하고
        ExecutorService executorService = new ThreadPoolExecutor(2, 4,
                30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        // 비동기 기반으로 진행 -> 사용자 많아질 시 고려
        final CompletableFuture<ClassifyDataFileResultWrapper> future =
                CompletableFuture.supplyAsync(() -> fileDataStoreGenerator.generateFileDataStore(key), executorService);

        ClassifyDataFileResultWrapper results;
        try {
            results = future.get();
            log.info("future.get() completed results: {}", results);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

        final List<MatchedAccountingPolicySet> matchedResults = results.matchedAccountingPolicySets();
        final List<BankTransactionRecord> notMatchedResults = results.unmatchedBankTransactionRecords();

        // 해당 list를 돌면서 매칭 된 내용 DB에 저장
        saveClassifiedDataService.saveClassifiedDataResults(matchedResults, notMatchedResults);

    }
}
