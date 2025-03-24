package com.prj.agile.dto;

import com.prj.agile.dto.request.InsuranceRequestDTO;
import com.prj.agile.dto.response.ClientDTO;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class PolicyDTO {

    private Integer id;
    private Date initDate;
    private Date endDate;
    private String broker;
    private String insuranceCompany;
    private Integer susepSubscriptionId;
    private String additionalInformation;
    private Integer paymentCondition;
    private PriceDTO price;
    private Set<ClientDTO> beneficiarylist;


    public static PolicyDTO createPolicy(PriceDTO price, Set<ClientDTO> clientSet, InsuranceRequestDTO insuranceRequestDTO){
        PolicyDTO policyDTO = new PolicyDTO();

        Date initDate = new Date();

        // Criando um calendário para adicionar 1 ano
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initDate);
        calendar.add(Calendar.YEAR, 1); // Adiciona 1 ano

        Date endDate = calendar.getTime();

        policyDTO.setInitDate(initDate);
        policyDTO.setEndDate(endDate);

        policyDTO.setBroker("Broker Generica");
        policyDTO.setInsuranceCompany("Seguradora SA");
        policyDTO.setSusepSubscriptionId(100);
        policyDTO.setAdditionalInformation("Cobertura 90% do valor segurado");
        policyDTO.setPaymentCondition(insuranceRequestDTO.getPaymentType());
        policyDTO.setPrice(price);
        policyDTO.setBeneficiarylist(clientSet);

        return policyDTO;
    }

}
