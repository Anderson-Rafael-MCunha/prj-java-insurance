package com.prj.agile.service.impl;

import com.prj.agile.dto.BudgetDTO;
import com.prj.agile.dto.PriceDTO;
import com.prj.agile.dto.ProductDTO;
import com.prj.agile.dto.ProposalDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.entity.insurance.Price;
import com.prj.agile.entity.insurance.Product;
import com.prj.agile.exception.PricingNotFoundException;
import com.prj.agile.exception.ProductNotFoundException;
import com.prj.agile.mapper.insurance.BudgetMapper;
import com.prj.agile.mapper.insurance.PriceMapper;
import com.prj.agile.repository.insurance.PriceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

@Service
public class PricingService {

    private final PriceRepository priceRepository;

    public PricingService(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }

    public List<Price> calculatePremium(ProductDTO productDTO, BudgetDTO budgetDTO , ProposalDTO proposalDTO){
        List<Price> priceList = new ArrayList<>();

        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setProposal(proposalDTO);

        // Commom princing components
        priceDTO.setInsuredValue(budgetDTO.getInsuredValue());
        priceDTO.setInsuredIndex(productDTO.getInsuredIndex());
        priceDTO.setBaseValueAmount(calculateBaseAmountValue(priceDTO.getInsuredValue(), priceDTO.getInsuredIndex()));
        priceDTO.setClientRisk(estimateClientRisk(budgetDTO));
        priceDTO.setClientDiscount(budgetDTO.getAccumulatedBonus());
        priceDTO.setProductDiscount(productDTO.getBonusDiscountMultiplier());
        priceDTO.setClientDiscountAmount(calculateDiscountAmount(priceDTO.getClientDiscount(), priceDTO.getProductDiscount()));
        priceDTO.setCostIndex(productDTO.getCostIndex());
        priceDTO.setCostAmount(calculateCostAmount(priceDTO.getBaseValueAmount(), priceDTO.getCostIndex()));
        priceDTO.setCoverageAddition(productDTO.getCoverageMultiplier());
        priceDTO.setInsuranceDeductibleIndex(productDTO.getInsuranceDeductible());

        priceDTO.setClientRiskComponent(calculateRiskComponent(priceDTO));
        priceDTO.setProductProfitLossComponent(calculateProfitLossComponent(priceDTO));

        PriceDTO bronzePrice = calculateBronzePrice(priceDTO, budgetDTO, proposalDTO);
        PriceDTO silverPrice = calculateSilverPrice(priceDTO, budgetDTO, proposalDTO);
        PriceDTO goldPrice = calculateGoldPrice(priceDTO, budgetDTO, proposalDTO);

        Price savedBronzePrice = priceRepository.save(PriceMapper.toEntity(bronzePrice));
        Price savedSilverPrice = priceRepository.save(PriceMapper.toEntity(silverPrice));
        Price savedGoldPrice = priceRepository.save(PriceMapper.toEntity(goldPrice));

        priceList.add(savedBronzePrice);
        priceList.add(savedSilverPrice);
        priceList.add(savedGoldPrice);

        return priceList;
    }

    public static BigDecimal calculateBaseAmountValue(BigDecimal insuredValue, BigDecimal insuredIndex){
        return insuredValue.multiply(insuredIndex);
    }

    public static BigDecimal estimateClientRisk(BudgetDTO budgetDTO) {
        ClientDTO clientDTO = budgetDTO.getClient();

        // Random value should use clientInfo to estimate with more accuracy
        // In this project, the estimation will be random
        Random random = new Random();
        double clientRisk = random.nextDouble();

        return new BigDecimal(clientRisk).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateDiscountAmount(Integer clientBonus, BigDecimal productRate){
        return productRate.multiply(new BigDecimal(clientBonus));
    }

    public static BigDecimal calculateCostAmount(BigDecimal baseValue, BigDecimal costIndex){
        return baseValue.multiply(costIndex);
    }

    public static BigDecimal calculateRiskComponent(PriceDTO priceDTO){
        BigDecimal result;
        BigDecimal baseValue = priceDTO.getBaseValueAmount();
        BigDecimal clientRisk = priceDTO.getClientRisk();
        BigDecimal discount = priceDTO.getClientDiscountAmount();
        BigDecimal clientAddition = BigDecimal.ONE.add(clientRisk);
        BigDecimal clientDiscount = BigDecimal.ONE.subtract(discount);
        BigDecimal clientAmount = clientAddition.multiply(clientDiscount);
        result = baseValue.multiply(clientAmount);
        return  result;
    }

    public static BigDecimal calculateProfitLossComponent(PriceDTO priceDTO){
        return priceDTO.getCostAmount();
    }


    public static BigDecimal calculatePremium(PriceDTO priceDTO){
        BigDecimal result;
        result = priceDTO.getClientRiskComponent()
                .add(priceDTO.getProductProfitLossComponent())
                .add(priceDTO.getCoverageComponent());
        return result;
    }

    public static BigDecimal calculateDeductibleAmount(PriceDTO priceDTO){
        return priceDTO.getInsuranceDeductibleIndex()
                .multiply(priceDTO.getInsurancePremium());
    }

    public static String generatePriceProtocol(PriceDTO priceDTO, BudgetDTO budgetDTO, ProposalDTO proposalDTO){
        String protocol = null;
        String clientPart = budgetDTO.getClient().getDocument().substring(0,4);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String datePart = sdf.format(budgetDTO.getCreatedAt());

        String proposalPart =  proposalDTO.getId().toString();

        String coveragepart = priceDTO.getCoverageType();

        protocol = clientPart.concat(datePart).concat(proposalPart).concat(coveragepart);

        return protocol;
    }


    public PriceDTO calculateBronzePrice(PriceDTO basePrice, BudgetDTO budgetDTO, ProposalDTO proposalDTO){

        BigDecimal coverageMultiplier = BigDecimal.ONE;
        basePrice.setCoverageType("BRONZE");

        BigDecimal additionalValue = basePrice.getCoverageAddition().multiply(coverageMultiplier);

        basePrice.setCoverageAdditionAmount(additionalValue);
        basePrice.setCoverageComponent(additionalValue);
        basePrice.setInsurancePremium(calculatePremium(basePrice));
        basePrice.setInsuranceDeductibleAmount(calculateDeductibleAmount(basePrice));
        basePrice.setProtocol(generatePriceProtocol(basePrice, budgetDTO, proposalDTO));

        return basePrice;

    }

    public PriceDTO calculateSilverPrice(PriceDTO basePrice, BudgetDTO budgetDTO, ProposalDTO proposalDTO){

        BigDecimal coverageMultiplier = new BigDecimal("2");
        basePrice.setCoverageType("SILVER");

        BigDecimal additionalValue = basePrice.getCoverageAddition().multiply(coverageMultiplier);

        basePrice.setCoverageAdditionAmount(additionalValue);
        basePrice.setCoverageComponent(additionalValue);
        basePrice.setInsurancePremium(calculatePremium(basePrice));
        basePrice.setInsuranceDeductibleAmount(calculateDeductibleAmount(basePrice));
        basePrice.setProtocol(generatePriceProtocol(basePrice, budgetDTO, proposalDTO));

        return basePrice;

    }

    public PriceDTO calculateGoldPrice(PriceDTO basePrice, BudgetDTO budgetDTO, ProposalDTO proposalDTO){

        BigDecimal coverageMultiplier = new BigDecimal("3");
        basePrice.setCoverageType("GOLD");

        BigDecimal additionalValue = basePrice.getCoverageAddition().multiply(coverageMultiplier);

        basePrice.setCoverageAdditionAmount(additionalValue);
        basePrice.setCoverageComponent(additionalValue);
        basePrice.setInsurancePremium(calculatePremium(basePrice));
        basePrice.setInsuranceDeductibleAmount(calculateDeductibleAmount(basePrice));
        basePrice.setProtocol(generatePriceProtocol(basePrice, budgetDTO, proposalDTO));

        return basePrice;

    }


    public Price findPriceByProtocol(String protocol) {
        return priceRepository.findByProtocol(protocol)
                .orElseThrow(() -> new PricingNotFoundException("Princing quote not found with protocol: " + protocol));
    }






}
