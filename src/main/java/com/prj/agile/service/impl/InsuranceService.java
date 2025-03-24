package com.prj.agile.service.impl;

import com.prj.agile.dto.PolicyDTO;
import com.prj.agile.dto.PriceDTO;
import com.prj.agile.dto.ProposalDTO;
import com.prj.agile.dto.request.InsuranceRequestDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.dto.response.InsuranceResponseDTO;
import com.prj.agile.dto.response.PriceResponseDTO;
import com.prj.agile.dto.response.SimulationResponseDTO;
import com.prj.agile.entity.client.Client;
import com.prj.agile.entity.insurance.Policy;
import com.prj.agile.entity.insurance.Price;
import com.prj.agile.exception.ClientNotFoundException;
import com.prj.agile.exception.PricingNotFoundException;
import com.prj.agile.mapper.client.ClientMapper;
import com.prj.agile.mapper.insurance.PolicyMapper;
import com.prj.agile.mapper.insurance.PriceMapper;
import com.prj.agile.repository.insurance.PolicyRepository;
import com.prj.agile.service.RestClient;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InsuranceService {

    private final PricingService pricingService;
    private final PolicyRepository policyRepository;
    private final RestClient restClient;

    public InsuranceService(PricingService pricingService, PolicyRepository policyRepository, RestClient restClient){
        this.pricingService = pricingService;
        this.policyRepository = policyRepository;
        this.restClient = restClient;
    }


    public ClientDTO fetchBeneficiary(String document){
        ClientDTO dto;
        try{
            dto = restClient.getClientByDocument(document);
        } catch (Exception e){
            throw new ClientNotFoundException("Beneficiario nao encontrado na base");
        }
        return dto;
    }


    public InsuranceResponseDTO createInsurance(InsuranceRequestDTO insuranceRequestDTO){
        Set<ClientDTO> clientSet = new HashSet<>();

        Price price = pricingService.findPriceByProtocol(insuranceRequestDTO.getProtocol());

        if(price != null ){

            List<String> documents = insuranceRequestDTO.getBeneficiaryDocuments();

            for (String document : documents) {
                ClientDTO dto = fetchBeneficiary(document);
                if (dto != null) {
                    clientSet.add(dto);
                }
            }

            PolicyDTO policyDTO = PolicyDTO.createPolicy(PriceMapper.toDTO(price), clientSet, insuranceRequestDTO);

            Policy policy = policyRepository.save(PolicyMapper.toEntity(policyDTO));

            return getInsuranceResponse(PolicyMapper.toDTO(policy), PriceMapper.toDTO(price));
        } else {
            throw new PricingNotFoundException("Cotacao nao foi encontrada no sistema!");
        }

    }



    public static InsuranceResponseDTO getInsuranceResponse(PolicyDTO policyDTO, PriceDTO priceDTO){
        InsuranceResponseDTO insuranceResponseDTO = new InsuranceResponseDTO();

        insuranceResponseDTO.setPolicy(policyDTO.getId());
        insuranceResponseDTO.setProposal(priceDTO.getProposal().getId());
        insuranceResponseDTO.setProtocol(priceDTO.getProtocol());
        insuranceResponseDTO.setPolicyInitialDate(policyDTO.getInitDate());
        insuranceResponseDTO.setPolicyEndDate(policyDTO.getEndDate());
        insuranceResponseDTO.setBroker(policyDTO.getBroker());
        insuranceResponseDTO.setInsuranceCompany(policyDTO.getInsuranceCompany());
        insuranceResponseDTO.setSusepSubscription(policyDTO.getSusepSubscriptionId().toString());
        insuranceResponseDTO.setAdditionalInformation(policyDTO.getAdditionalInformation());
        insuranceResponseDTO.setPaymentCondition(policyDTO.getPaymentCondition());
        insuranceResponseDTO.setPremiumAmount(priceDTO.getInsurancePremium().toString());
        insuranceResponseDTO.setInsuranceDeductibleAmount(priceDTO.getInsuranceDeductibleAmount().toString());
        insuranceResponseDTO.setCoverageType(priceDTO.getCoverageType());
        insuranceResponseDTO.setClient(priceDTO.getProposal().getBudget().getClient());
        insuranceResponseDTO.setBeneficiaries(policyDTO.getBeneficiarylist().stream().toList());

        return insuranceResponseDTO;

    }




}
