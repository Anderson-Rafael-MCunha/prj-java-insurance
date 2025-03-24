package com.prj.agile.repository.insurance;

import com.prj.agile.entity.insurance.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{

}
