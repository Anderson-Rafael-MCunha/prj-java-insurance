package com.prj.agile.controller;

import com.prj.agile.dto.request.InsuranceRequestDTO;
import com.prj.agile.dto.request.SimulationRequestDTO;
import com.prj.agile.dto.response.InsuranceResponseDTO;
import com.prj.agile.dto.response.SimulationResponseDTO;
import com.prj.agile.service.impl.InsuranceService;
import com.prj.agile.service.impl.SimulationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService){
        this.insuranceService = insuranceService;
    }

    @Operation(summary = "Realiza contratacao do seguro", description = "Esse endpoint realiza a contratacao do seguro e geracao da Apolice")
    @PostMapping
    public ResponseEntity<InsuranceResponseDTO> contractInsurance(@RequestBody InsuranceRequestDTO insuranceRequestDTO) {
        try {
            InsuranceResponseDTO insuranceResponseDTO = insuranceService.createInsurance(insuranceRequestDTO);
            return ResponseEntity.ok(insuranceResponseDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
