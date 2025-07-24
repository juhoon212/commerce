package com.example.onnuri.commerce.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Table(name = "category")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryEntity {
    @Id
    @Column(name = "id", nullable = false, length = 128)
    private String id;

    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

}