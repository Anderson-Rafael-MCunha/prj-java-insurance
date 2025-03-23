package com.prj.agile.repository.insurance;

import com.prj.agile.entity.insurance.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer>{

}
