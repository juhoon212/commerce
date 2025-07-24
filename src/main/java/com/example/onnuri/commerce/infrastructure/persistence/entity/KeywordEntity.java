package com.example.onnuri.commerce.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "keyword")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class KeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category_id", nullable = false, length = 128)
    private String categoryId;

    @Column(name = "name", nullable = false, length = 128)
    private String name;
}