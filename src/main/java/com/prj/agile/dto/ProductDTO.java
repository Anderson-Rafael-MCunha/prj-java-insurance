package com.prj.agile.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProductDTO {

    private Integer id;
    private String description;
    private String susepIdentification;
    private BigDecimal insuranceCoverage;
    private BigDecimal insuredIndex;
    private BigDecimal costIndex;
    private BigDecimal bonusDiscountMultiplier;
    private BigDecimal coverageMultiplier;
    private BigDecimal insuranceDeductible;
    private Date createdAt;

}
