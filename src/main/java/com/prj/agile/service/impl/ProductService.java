package com.prj.agile.service.impl;

import com.prj.agile.entity.insurance.Product;
import com.prj.agile.exception.ProductNotFoundException;
import com.prj.agile.repository.insurance.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Optional<Product> createGenericProduct(){
        String description = "GENERIC";
        Product product = new Product();
        product.setDescription(description);
        product.setSusepIdentification("SUSEP-001");
        product.setInsuranceCoverage(new BigDecimal("0.90"));
        product.setInsuredIndex(new BigDecimal("0.04"));
        product.setCostIndex(new BigDecimal("0.05"));
        product.setBonusDiscountMultiplier(new BigDecimal("0.02"));
        product.setCoverageMultiplier(new BigDecimal("0.07"));
        product.setInsuranceDeductible(new BigDecimal("0.95"));
        product.setCreatedAt(new Date());

        productRepository.save(product);
        return productRepository.findByDescription(description);
    }


    public Product findProductByDescription(String description) {
        return productRepository.findByDescription(description)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with description: " + description));
    }



}
