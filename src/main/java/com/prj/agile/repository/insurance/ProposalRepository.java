package com.prj.agile.repository.insurance;

import com.prj.agile.entity.insurance.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Integer>{

}
