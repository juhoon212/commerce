package com.example.onnuri.commerce.core.domain;

import com.example.onnuri.commerce.core.domain.account.BankTransactionRecord;
import com.example.onnuri.commerce.core.domain.policy.IntegratedPolicy;
import lombok.Getter;

@Getter
public class MatchedAccountingPolicySet {

    // 장부 하나의 row
    private final BankTransactionRecord bankTransactionRecord;
    private final IntegratedPolicy integratedPolicy;

    private MatchedAccountingPolicySet(BankTransactionRecord bankTransactionRecord, IntegratedPolicy integratedPolicy) {
        this.bankTransactionRecord = bankTransactionRecord;
        this.integratedPolicy = integratedPolicy;
    }

    public static MatchedAccountingPolicySet generateMatchedAccountingPolicySet(
            final BankTransactionRecord bankTransactionRecord,
            final IntegratedPolicy integratedPolicy
    ) {
        return new MatchedAccountingPolicySet(
                bankTransactionRecord,
                integratedPolicy
        );
    }
}
