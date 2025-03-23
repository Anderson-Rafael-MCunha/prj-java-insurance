package com.prj.agile.dto;

import com.prj.agile.dto.response.ClientDTO;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
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
    private String paymentCondition;
    private PriceDTO price;
    private Set<ClientDTO> beneficiarylist;

}
