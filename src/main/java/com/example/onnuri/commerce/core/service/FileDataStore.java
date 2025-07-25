package com.example.onnuri.commerce.core.service;

import com.example.onnuri.commerce.core.domain.AccountingPolicySet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
public class FileDataStore {
    // 데이터 처리를 위한 buffer 역할을 하는 ConcurrentHashMap
    private final Map<String, ConcurrentLinkedQueue<AccountingPolicySet>> fileDataMap = new ConcurrentHashMap<>();

    public void addFileData(final String key, final AccountingPolicySet accountingPolicySet) {
        log.info("key = {}", key);
        fileDataMap.putIfAbsent(key, new ConcurrentLinkedQueue<>());
        fileDataMap.get(key).add(accountingPolicySet);
    }

    public ConcurrentLinkedQueue<AccountingPolicySet> getQueue(final String key) {
        return fileDataMap.get(key);
    }

    private void clearQueue(String key) {
        final ConcurrentLinkedQueue<AccountingPolicySet> accounts = fileDataMap.get(key);
        accounts.clear();
    }

    public AccountingPolicySet getAccountingPolicySetValue(final String key) {
        final ConcurrentLinkedQueue<AccountingPolicySet> queue = fileDataMap.get(key);
        return queue.poll();
    }

    public void clearMap(String key) {
        fileDataMap.remove(key);
    }

    public void clearAllMapValues() {
        fileDataMap.clear();
    }

    public boolean isEmpty() {
        return fileDataMap.isEmpty();
    }

}
