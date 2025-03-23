package com.prj.agile.service.impl;

import com.prj.agile.dto.BudgetDTO;
import com.prj.agile.dto.ProductDTO;
import com.prj.agile.dto.request.SimulationRequestDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.entity.insurance.Budget;
import com.prj.agile.mapper.insurance.BudgetMapper;
import com.prj.agile.repository.insurance.BudgetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository){
        this.budgetRepository = budgetRepository;
    }

    public Budget saveBudget(BudgetDTO budgetDTO){
        return budgetRepository.save(BudgetMapper.toEntity(budgetDTO));
    }

}
