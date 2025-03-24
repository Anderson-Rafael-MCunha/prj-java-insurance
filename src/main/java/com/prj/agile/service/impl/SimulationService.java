package com.prj.agile.service.impl;

import com.prj.agile.dto.BudgetDTO;
import com.prj.agile.dto.ProductDTO;
import com.prj.agile.dto.ProposalDTO;
import com.prj.agile.dto.request.SimulationRequestDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.dto.response.PriceResponseDTO;
import com.prj.agile.dto.response.SimulationResponseDTO;
import com.prj.agile.entity.insurance.Budget;
import com.prj.agile.entity.insurance.Price;
import com.prj.agile.entity.insurance.Product;
import com.prj.agile.entity.insurance.Proposal;
import com.prj.agile.exception.SimulationErrorException;
import com.prj.agile.mapper.client.ClientMapper;
import com.prj.agile.mapper.insurance.BudgetMapper;
import com.prj.agile.mapper.insurance.ProductMapper;
import com.prj.agile.mapper.insurance.ProposalMapper;
import com.prj.agile.service.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SimulationService {

    private final RestClient restClient;
    private final InsuredService insuredService;
    private final ProductService productService;
    private final BudgetService budgetService;
    private final ProposalService proposalService;
    private final PricingService pricingService;


    @Autowired
    public SimulationService(
            RestClient restClient,
            InsuredService insuredService,
            ProductService productService,
            BudgetService budgetService,
            ProposalService proposalService,
            PricingService pricingService
    ){
        this.restClient = restClient;
        this.insuredService = insuredService;
        this.productService = productService;
        this.budgetService  = budgetService;
        this.proposalService = proposalService;
        this.pricingService = pricingService;
    }

    public ClientDTO fetchClient(String document){
        ClientDTO dto;
        try{
            dto = restClient.getClientByDocument(document);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return dto;
    }


    public SimulationResponseDTO createSimulation(SimulationRequestDTO simulationRequestDTO){

        try{
            ClientDTO clientDTO = fetchClient(simulationRequestDTO.getClientDocument());

            if(clientDTO != null){

                ClientDTO savedClientDTO = ClientMapper.toDTO(insuredService.persistInsuredData(clientDTO));
                productService.createGenericProduct();
                Product product = productService.findProductByDescription(simulationRequestDTO.getProduct());
                ProductDTO productDTO = ProductMapper.toDTO(product);
                BudgetDTO budgetDTO = BudgetDTO.createBudgetDTO(savedClientDTO, productDTO, simulationRequestDTO);
                Budget budget = budgetService.saveBudget(budgetDTO);
                ProposalDTO proposalDTO = ProposalDTO.createProposalDTO(BudgetMapper.toDTO(budget));
                Proposal proposal = proposalService.saveProposal(proposalDTO);

                List<Price> priceList = pricingService
                        .calculatePremium(productDTO, BudgetMapper.toDTO(budget), ProposalMapper.toDTO(proposal));

                return getSimulationResponse(priceList, proposalDTO);
            } else {
                String e = "Cliente nao cadastrado. Acionar time de onboarding";
                System.out.println(e);
                throw new SimulationErrorException("Client avaliado nao possui cadastro");
            }
        } catch (Exception e){
            throw new SimulationErrorException("Excecao no calculo do premio e franquia do seguro");
        }
    }


    public static SimulationResponseDTO getSimulationResponse(List<Price> priceList, ProposalDTO proposalDTO){
        SimulationResponseDTO simulationResponseDTO = new SimulationResponseDTO();

        simulationResponseDTO.setProposalId(proposalDTO.getId());
        simulationResponseDTO.setProposalCreatedDate(proposalDTO.getCreatedAt());
        simulationResponseDTO.setProposalEndDate(proposalDTO.getProposalEndDate());

        List<PriceResponseDTO> priceResponseDTOList = getPriceResponseDTOS(priceList);

        simulationResponseDTO.setPriceResponseList(priceResponseDTOList);

        return simulationResponseDTO;

    }

    private static List<PriceResponseDTO> getPriceResponseDTOS(List<Price> priceList) {
        List<PriceResponseDTO> priceResponseDTOList = new ArrayList<>();

        for(Price price : priceList){
            PriceResponseDTO priceResponseDTO = new PriceResponseDTO();
            priceResponseDTO.setType(price.getCoverageType());
            priceResponseDTO.setProtocol(price.getProtocol());
            priceResponseDTO.setPremium(price.getInsurancePremium().toString());
            priceResponseDTO.setDeductible(price.getInsuranceDeductibleAmout().toString());
            priceResponseDTOList.add(priceResponseDTO);
        }
        return priceResponseDTOList;
    }


}
