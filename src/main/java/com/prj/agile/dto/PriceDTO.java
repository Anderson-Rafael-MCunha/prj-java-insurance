package com.prj.agile.dto;

import com.prj.agile.entity.insurance.Proposal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class PriceDTO implements Cloneable {

    private Integer id;

    private BigDecimal insuredValue;
    private BigDecimal insuredIndex;
    private BigDecimal baseValueAmount;
    private BigDecimal clientRisk;
    private Integer clientDiscount;
    private BigDecimal productDiscount;
    private BigDecimal clientDiscountAmount;
    private BigDecimal clientRiskComponent;


    private BigDecimal costIndex;
    private BigDecimal costAmount;
    private BigDecimal productProfitLossComponent;

    private BigDecimal coverageAddition;
    private String coverageType;
    private BigDecimal coverageAdditionAmount;
    private BigDecimal coverageComponent;


    private BigDecimal insurancePremium;
    private BigDecimal insuranceDeductibleIndex;
    private BigDecimal insuranceDeductibleAmount;

    private String protocol;

    private ProposalDTO proposal;

    @Override
    public PriceDTO clone() {
        try {
            return (PriceDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar PriceDTO", e);
        }
    }

}

