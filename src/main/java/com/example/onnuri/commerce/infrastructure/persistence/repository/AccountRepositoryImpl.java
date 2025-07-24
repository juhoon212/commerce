package com.example.onnuri.commerce.infrastructure.persistence.repository;

import com.example.onnuri.commerce.infrastructure.dto.GetClassifiedResultDto;
import com.example.onnuri.commerce.infrastructure.persistence.entity.QAccountEntity;
import com.example.onnuri.commerce.infrastructure.persistence.entity.QCategoryEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.onnuri.commerce.infrastructure.persistence.entity.QAccountEntity.*;
import static com.example.onnuri.commerce.infrastructure.persistence.entity.QCategoryEntity.*;

@Slf4j
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GetClassifiedResultDto> findClassifiedResult(final String companyId) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                GetClassifiedResultDto.class,
                                accountEntity.id,
                                accountEntity.companyId,
                                accountEntity.keyword,
                                accountEntity.transactionDatetime,
                                accountEntity.description,
                                accountEntity.depositAmount,
                                accountEntity.withdrawAmount,
                                accountEntity.balanceAfterTransaction,
                                accountEntity.transactionBranch,
                                accountEntity.isMatched,
                                categoryEntity.id,
                                categoryEntity.name
                        )
                )
                .from(accountEntity)
                .leftJoin(categoryEntity)
                .on(accountEntity.categoryId.eq(categoryEntity.id))
                .where(isCompanyIdEqual(companyId))
                .orderBy(accountEntity.id.desc())
                .fetch();
    }

    private BooleanExpression isCompanyIdEqual(final String companyId) {
        return StringUtils.hasText(companyId) ? accountEntity.companyId.eq(companyId) : null;
    }
}
