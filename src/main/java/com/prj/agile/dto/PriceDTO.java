package com.prj.agile.dto;

import com.prj.agile.entity.insurance.Proposal;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceDTO {

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
    ;

    private BigDecimal coverageAddition;
    private String coverageType;
    private BigDecimal coverageAdditionAmount;
    private BigDecimal coverageComponent;


    private BigDecimal insurancePremium;
    private BigDecimal insuranceDeductibleIndex;
    private BigDecimal insuranceDeductibleAmount;

    private String protocol;

    private ProposalDTO proposal;

}

