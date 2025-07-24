package com.example.onnuri.commerce.application.dto;

import com.example.onnuri.commerce.domain.MatchedAccountingPolicySet;
import com.example.onnuri.commerce.domain.account.BankTransactionRecord;

import java.util.List;

public record ClassifyDataFileResultWrapper(
        List<MatchedAccountingPolicySet> matchedAccountingPolicySets,
        List<BankTransactionRecord> unmatchedBankTransactionRecords
) {}
