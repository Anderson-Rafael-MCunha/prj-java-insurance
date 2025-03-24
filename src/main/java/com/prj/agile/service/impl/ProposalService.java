package com.prj.agile.service.impl;

import com.prj.agile.dto.ProposalDTO;
import com.prj.agile.entity.insurance.Proposal;
import com.prj.agile.mapper.insurance.ProposalMapper;
import com.prj.agile.repository.insurance.ProposalRepository;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;

    public ProposalService(ProposalRepository proposalRepository){
        this.proposalRepository = proposalRepository;
    }

    public Proposal saveProposal(ProposalDTO proposalDTO){
        return proposalRepository.save(ProposalMapper.toEntity(proposalDTO));
    }
}
