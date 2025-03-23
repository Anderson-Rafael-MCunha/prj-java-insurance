package com.prj.agile.entity.insurance;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Price")
@Table(name = "price",
        schema= "insurance"
)
public class Price {

    @Id
    @SequenceGenerator(
            name = "price_sequence",
            sequenceName="price_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name="protocol", columnDefinition = "VARCHAR", nullable = false)
    private String protocol;

    @Column(name="insured_value", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insuredValue;

    @Column(name="insured_index", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insuredIndex;

    @Column(name="base_value_amount", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal baseValueAmount;

    @Column(name="client_risk", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal clientRisk;

    @Column(name="client_discount", columnDefinition = "INTEGER", nullable = false)
    private Integer clientDiscount;

    @Column(name="product_discount", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal productDiscount;

    @Column(name="client_discount_amount", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal clientDiscountAmount;

    @Column(name="cost_index", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal costIndex;

    @Column(name="cost_amount", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal costAmout;

    @Column(name="coverage_addition", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal coverageAddition;

    @Column(name="coverage_type", columnDefinition = "VARCHAR", nullable = false)
    private String coverageType;

    @Column(name="coverage_addition_amount", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal coverageAdditionAmount;

    @Column(name="client_rik_component", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal clientRiskComponent;

    @Column(name="product_profit_loss_component", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal productProfitLossComponent;

    @Column(name="coverage_component", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal coverageComponent;

    @Column(name="insurance_premium", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insurancePremium;

    @Column(name="insurance_deductible_index", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insuranceDeductibleIndex;

    @Column(name="insurance_deductible_amount", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insuranceDeductibleAmout;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Proposal proposal;

    @OneToOne(mappedBy = "price")
    private Policy policy;

}
