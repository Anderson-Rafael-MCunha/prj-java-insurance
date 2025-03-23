package com.prj.agile.entity.insurance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "product",
        schema= "insurance"
)
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName="product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "susep_identification", nullable = false)
    private String susepIdentification;

    @Column(name = "insurance_coverage", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insuranceCoverage;

    @Column(name = "insurance_index", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insuredIndex;

    @Column(name = "cost_index", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal costIndex;

    @Column(name = "bonus_discount_multiplier", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal bonusDiscountMultiplier;

    @Column(name = "coverage_multiplier", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal coverageMultiplier;

    @Column(name = "insurance_deductible", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insuranceDeductible;

    @Column(name="created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Budget> budgetList = new ArrayList<>();


    public Product(String description, String susepIdentification, BigDecimal insuranceCoverage, BigDecimal insuredIndex, BigDecimal costIndex, BigDecimal coverageMultiplier, BigDecimal bonusDiscountMultiplier, BigDecimal insuranceDeductible, Date createdAt) {
        this.description = description;
        this.susepIdentification = susepIdentification;
        this.insuranceCoverage = insuranceCoverage;
        this.insuredIndex = insuredIndex;
        this.costIndex = costIndex;
        this.coverageMultiplier = coverageMultiplier;
        this.bonusDiscountMultiplier = bonusDiscountMultiplier;
        this.insuranceDeductible = insuranceDeductible;
        this.createdAt = createdAt;
    }
}
