package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.domain.Account;
import com.example.onnuri.commerce.domain.AccountingPolicySet;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class FileDataStore {
    // 데이터 처리를 위한 buffer 역할을 하는 ConcurrentHashMap
    private final Map<String, ConcurrentLinkedQueue<AccountingPolicySet>> fileDataMap = new ConcurrentHashMap<>();

    public void addFileData(final String key, final AccountingPolicySet accountingPolicySet) {
        fileDataMap.putIfAbsent(key, new ConcurrentLinkedQueue<>());
        fileDataMap.get(key).add(accountingPolicySet);
    }


    private void clearQueue(String key) {
        final ConcurrentLinkedQueue<AccountingPolicySet> accounts = fileDataMap.get(key);
        accounts.clear();
    }

    public void clearMap(String key) {
        fileDataMap.remove(key);
        clearQueue(key);
    }

    public void clearAllMapValues() {
        fileDataMap.clear();
    }

    public boolean isEmpty() {
        return fileDataMap.isEmpty();
    }

    public boolean isRegisteredKey(final String key) {
        return fileDataMap.containsKey(key);
    }
}
