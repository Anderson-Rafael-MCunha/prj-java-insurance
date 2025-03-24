package com.prj.agile.service.impl;

import com.prj.agile.dto.BudgetDTO;
import com.prj.agile.dto.ProductDTO;
import com.prj.agile.dto.request.SimulationRequestDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.entity.insurance.Budget;
import com.prj.agile.mapper.insurance.BudgetMapper;
import com.prj.agile.repository.client.ClientRepository;
import com.prj.agile.repository.insurance.BudgetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final ClientRepository clientRepository;

    public BudgetService(BudgetRepository budgetRepository,
                         ClientRepository clientRepository){
        this.budgetRepository = budgetRepository;
        this.clientRepository = clientRepository;
    }

    public Budget saveBudget(BudgetDTO budgetDTO){
        Budget budget = BudgetMapper.toEntity(budgetDTO);
        return budgetRepository.save(budget);
    }

}
