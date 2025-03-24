package com.prj.agile.dto;

import com.prj.agile.dto.request.SimulationRequestDTO;
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


    public static BudgetDTO createBudgetDTO(ClientDTO clientDTO, ProductDTO productDTO, SimulationRequestDTO simulationRequestDTO){
        BudgetDTO budgetDTO = new BudgetDTO();
        budgetDTO.setCreatedAt(new Date());
        budgetDTO.setAccumulatedBonus(simulationRequestDTO.getAccumulatedBonus());
        budgetDTO.setInsuredValue(new BigDecimal(simulationRequestDTO.getInsuredValue()));
        budgetDTO.setClient(clientDTO);
        budgetDTO.setProduct(productDTO);
        return budgetDTO;
    }

}
