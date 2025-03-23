package com.prj.agile.dto;

import com.prj.agile.dto.response.ClientDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BudgetDTO {

    private Integer id;
    private Date createdAt;
    private Integer accumulatedBonus;
    private BigDecimal insuredValue;
    private ClientDTO client;
    private ProductDTO product;
    private ProposalDTO proposal;
}
