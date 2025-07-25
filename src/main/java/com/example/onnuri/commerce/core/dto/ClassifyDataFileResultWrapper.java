package com.example.onnuri.commerce.core.dto;

import com.example.onnuri.commerce.core.domain.MatchedAccountingPolicySet;
import com.example.onnuri.commerce.core.domain.account.BankTransactionRecord;

import java.util.List;

public record ClassifyDataFileResultWrapper(
        List<MatchedAccountingPolicySet> matchedAccountingPolicySets,
        List<BankTransactionRecord> unmatchedBankTransactionRecords
) {}
