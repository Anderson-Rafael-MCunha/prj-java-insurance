package com.prj.agile.mapper.insurance;

import com.prj.agile.dto.PolicyDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.entity.client.Client;
import com.prj.agile.entity.insurance.Policy;
import com.prj.agile.mapper.client.ClientMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PolicyMapper {

    public static PolicyDTO toDTO(Policy policy) {
        PolicyDTO dto = new PolicyDTO();
        dto.setId(policy.getId());
        dto.setInitDate(policy.getInitDate());
        dto.setEndDate(policy.getEndDate());
        dto.setBroker(policy.getBroker());
        dto.setInsuranceCompany(policy.getInsuranceCompany());
        dto.setSusepSubscriptionId(policy.getSusepSubscriptionId());
        dto.setAdditionalInformation(policy.getAdditionalInformation());
        dto.setPaymentCondition(policy.getPaymentCondition());
        dto.setPrice(PriceMapper.toDTO(policy.getPrice()));
        Set<ClientDTO> clientDTOSet = policy.getBeneficiarylist().stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toSet());
        dto.setBeneficiarylist(clientDTOSet);
        return dto;
    }

    public static Policy toEntity(PolicyDTO dto) {
        Set<Client> clientSet = dto.getBeneficiarylist().stream()
                .map(ClientMapper::toEntity)
                .collect(Collectors.toSet());
        return new Policy(
                dto.getInitDate(),
                dto.getEndDate(),
                dto.getBroker(),
                dto.getInsuranceCompany(),
                dto.getSusepSubscriptionId(),
                dto.getAdditionalInformation(),
                dto.getPaymentCondition(),
                PriceMapper.toEntity(dto.getPrice()),
                clientSet
        );
    }
}
