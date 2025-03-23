package com.prj.agile.mapper.insurance;


import com.prj.agile.dto.BudgetDTO;
import com.prj.agile.entity.insurance.Budget;
import com.prj.agile.mapper.client.ClientMapper;

public class BudgetMapper {

    public static BudgetDTO toDTO(Budget budget) {
        BudgetDTO dto = new BudgetDTO();
        dto.setId(budget.getId());
        dto.setCreatedAt(budget.getCreatedAt());
        dto.setAccumulatedBonus(budget.getAccumulatedBonus());
        dto.setInsuredValue(budget.getInsuredValue());
        dto.setClient(ClientMapper.toDTO(budget.getClient()));
        dto.setProduct(ProductMapper.toDTO(budget.getProduct()));
        return dto;
    }

    public static Budget toEntity(BudgetDTO dto) {
        return new Budget(
                ProductMapper.toEntity(dto.getProduct()),
                ClientMapper.toEntity(dto.getClient()),
                dto.getInsuredValue(),
                dto.getAccumulatedBonus(),
                dto.getCreatedAt()
        );
    }
}