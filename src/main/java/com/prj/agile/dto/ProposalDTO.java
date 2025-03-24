package com.prj.agile.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Calendar;
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

    public static ProposalDTO createProposalDTO(BudgetDTO budgetDTO){
        ProposalDTO proposalDTO = new ProposalDTO();

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        Date datePlus7Days = calendar.getTime();

        proposalDTO.setCreatedAt(today);
        proposalDTO.setProposalEndDate(datePlus7Days);
        proposalDTO.setBudget(budgetDTO);
        return proposalDTO;
    }

}
