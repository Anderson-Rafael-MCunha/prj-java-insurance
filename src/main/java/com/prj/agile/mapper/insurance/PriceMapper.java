package com.prj.agile.mapper.insurance;

import com.prj.agile.dto.PriceDTO;
import com.prj.agile.entity.insurance.Price;
import com.prj.agile.entity.insurance.Proposal;

public class PriceMapper {

    public static PriceDTO toDTO(Price price) {
        PriceDTO dto = new PriceDTO();
        dto.setId(price.getId());
        dto.setProtocol(price.getProtocol());
        dto.setInsuredValue(price.getInsuredValue());
        dto.setInsuredIndex(price.getInsuredIndex());
        dto.setBaseValueAmount(price.getBaseValueAmount());
        dto.setClientRisk(price.getClientRisk());
        dto.setClientDiscount(price.getClientDiscount());
        dto.setProductDiscount(price.getProductDiscount());
        dto.setClientDiscountAmount(price.getClientDiscountAmount());
        dto.setCostIndex(price.getCostIndex());
        dto.setCostAmount(price.getCostAmout());
        dto.setCoverageAddition(price.getCoverageAddition());
        dto.setCoverageType(price.getCoverageType());
        dto.setCoverageAdditionAmount(price.getCoverageAdditionAmount());
        dto.setClientRiskComponent(price.getClientRiskComponent());
        dto.setProductProfitLossComponent(price.getProductProfitLossComponent());
        dto.setCoverageComponent(price.getCoverageComponent());
        dto.setInsurancePremium(price.getInsurancePremium());
        dto.setInsuranceDeductibleIndex(price.getInsuranceDeductibleIndex());
        dto.setInsuranceDeductibleAmount(price.getInsuranceDeductibleAmout());
        dto.setProposal(ProposalMapper.toDTO(price.getProposal()));
        return dto;
    }

    public static Price toEntity(PriceDTO dto) {
        return new Price(
                dto.getProtocol(),
                dto.getInsuredValue(),
                dto.getInsuredIndex(),
                dto.getBaseValueAmount(),
                dto.getClientRisk(),
                dto.getClientDiscount(),
                dto.getProductDiscount(),
                dto.getClientDiscountAmount(),
                dto.getCostIndex(),
                dto.getCostAmount(),
                dto.getCoverageAddition(),
                dto.getCoverageType(),
                dto.getCoverageAdditionAmount(),
                dto.getClientRiskComponent(),
                dto.getProductProfitLossComponent(),
                dto.getCoverageComponent(),
                dto.getInsurancePremium(),
                dto.getInsuranceDeductibleIndex(),
                dto.getInsuranceDeductibleAmount(),
                ProposalMapper.toEntity(dto.getProposal())
        );
    }
}
