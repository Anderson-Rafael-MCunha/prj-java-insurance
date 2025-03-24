package com.prj.agile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceResponseDTO {

    private Integer policy;
    private Integer proposal;
    private String protocol;
    private Date policyInitialDate;
    private Date policyEndDate;
    private String broker;
    private String insuranceCompany;
    private String susepSubscription;
    private String additionalInformation;
    private String paymentCondition;
    private String premiumAmount;
    private String insuranceDeductibleAmount;
    private String coverageType;
    private ClientDTO client;
    private List<ClientDTO> beneficiaries;




}
