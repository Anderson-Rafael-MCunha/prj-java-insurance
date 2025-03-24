package com.prj.agile.mapper.insurance;

import com.prj.agile.dto.PriceDTO;
import com.prj.agile.dto.ProposalDTO;
import com.prj.agile.entity.insurance.Proposal;

import java.util.Collections;
import java.util.stream.Collectors;

public class ProposalMapper {

    public static ProposalDTO toDTO(Proposal proposal) {
        ProposalDTO dto = new ProposalDTO();
        dto.setId(proposal.getId());
        dto.setCreatedAt(proposal.getCreatedAt());
        dto.setProposalEndDate(proposal.getProposalEndDate());
        dto.setBudget(BudgetMapper.toDTO(proposal.getBudget()));
        dto.setPriceList(proposal.getPriceList().stream().map(PriceMapper::toDTO).collect(Collectors.toList()));
        return dto;
    }

    public static Proposal toEntity(ProposalDTO dto) {
        Proposal proposal = new Proposal(
                dto.getCreatedAt(),
                dto.getProposalEndDate(),
                BudgetMapper.toEntity(dto.getBudget())
        );
        proposal.setId(dto.getId());
        return proposal;
    }
}