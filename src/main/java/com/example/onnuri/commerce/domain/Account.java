package com.example.onnuri.commerce.domain;

import com.example.onnuri.commerce.common.enums.FileType;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@ToString
public class Account extends BaseFile {

    private final List<String> headers;
    private final List<BankTransactionRecord> bankTransactionRecords = new ArrayList<>();

    private Account(FileType fileType, List<String> headers, List<List<String>> data) {
        super(fileType);
        this.headers = headers;
        parseData(data);
    }

    public static Account generateAccount(
            final List<String> headers,
            final List<List<String>> data
    ) {
        log.info("headers = {}", headers);
        log.info("data = {}", data);
        return new Account(FileType.ACCOUNT, headers, data);
    }

    // data 들을 BankTransactionRecord로 세팅
    private void parseData(List<List<String>> dataFrames) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < dataFrames.size(); ++i) {
            LocalDateTime transactionDateTime = null;
            String description = "";
            BigDecimal depositAmount = null;
            BigDecimal withdrawAmount = null;
            BigDecimal balanceAfterTransaction = null;
            String transactionBranch = "";

            for (int j = 0; j < dataFrames.get(i).size(); ++j) {
                final String headerValue = headers.get(j);

                // 공백 제거
                final String currentValue = dataFrames.get(i).get(j).trim();

                log.info("headerValue = {}, currentValue = {}", headerValue, currentValue);

                switch (headerValue) {
                    case "거래일시":
                        transactionDateTime = LocalDateTime.parse(currentValue, formatter);
                        break;
                    case "적요":
                        description = currentValue;
                        break;
                    case "입금액":
                        depositAmount = new BigDecimal(currentValue);
                        break;
                    case "출금액":
                        withdrawAmount = new BigDecimal(currentValue);
                        break;
                    case "거래후잔액":
                        balanceAfterTransaction = new BigDecimal(currentValue);
                        break;
                    case "거래점":
                        transactionBranch = currentValue;
                        break;
                    default:
                        log.warn("Unknown header: {}", headerValue);
                }
            }

            final BankTransactionRecord record = BankTransactionRecord.generateBankTransactionRecord(
                    transactionDateTime,
                    description,
                    depositAmount,
                    withdrawAmount,
                    balanceAfterTransaction,
                    transactionBranch
            );

            log.info("record = {}", record);

            bankTransactionRecords.add(record);
            log.info("bankTransactionRecords = {}", bankTransactionRecords);
        }
    }
}
