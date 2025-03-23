package com.prj.agile.mapper.insurance;

import com.prj.agile.dto.ProductDTO;
import com.prj.agile.entity.insurance.Product;


public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setSusepIdentification(product.getSusepIdentification());
        dto.setInsuranceCoverage(product.getInsuranceCoverage());
        dto.setInsuredIndex(product.getInsuredIndex());
        dto.setCostIndex(product.getCostIndex());
        dto.setBonusDiscountMultiplier(product.getBonusDiscountMultiplier());
        dto.setCoverageMultiplier(product.getCoverageMultiplier());
        dto.setInsuranceDeductible(product.getInsuranceDeductible());
        dto.setCreatedAt(product.getCreatedAt());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        return new Product(
                dto.getDescription(),
                dto.getSusepIdentification(),
                dto.getInsuranceCoverage(),
                dto.getInsuredIndex(),
                dto.getCostIndex(),
                dto.getCoverageMultiplier(),
                dto.getBonusDiscountMultiplier(),
                dto.getInsuranceDeductible(),
                dto.getCreatedAt()
        );
    }

}
