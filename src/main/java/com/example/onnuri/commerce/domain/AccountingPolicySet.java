package com.example.onnuri.commerce.domain;

import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.domain.policy.IntegratedPolicy;
import lombok.Getter;

import java.util.List;

@Getter
public class AccountingPolicySet {

    private final List<Account> accounts;
    private final List<IntegratedPolicy> integratedPolicies;

    private AccountingPolicySet(List<Account> accounts, List<IntegratedPolicy> integratedPolicies) {
        this.accounts = accounts;
        this.integratedPolicies = integratedPolicies;
    }

    public static AccountingPolicySet generateAccountingPolicySet(
            final List<Account> accounts,
            final List<IntegratedPolicy> integratedPolicies
    ) {
        return new AccountingPolicySet(accounts, integratedPolicies);
    }
}
