package com.example.onnuri.commerce.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class AccountingPolicySet {

    private final List<Account> accounts;
    private final Policy policy;

    private AccountingPolicySet(List<Account> accounts, Policy policy) {
        this.accounts = accounts;
        this.policy = policy;
    }

    public static AccountingPolicySet generateAccountingPolicySet(
            final List<Account> accounts,
            final Policy policy
    ) {
        return new AccountingPolicySet(accounts, policy);
    }
}
