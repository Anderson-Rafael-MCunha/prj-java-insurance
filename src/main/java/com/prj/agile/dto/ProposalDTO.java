package com.prj.agile.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProposalDTO {

    private Integer id;
    private Date createdAt;
    private Date proposalEndDate;
    private BudgetDTO budget;
    private List<PriceDTO> priceList;

}
