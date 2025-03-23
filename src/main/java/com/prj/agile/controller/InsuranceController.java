package com.prj.agile.controller;

import com.prj.agile.dto.request.SimulationRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/insurance")
public class InsuranceController {

    @Operation(summary = "Realiza contratacao do seguro", description = "Esse endpoint realiza a contratacao do seguro e geracao da Apolice")
    @PostMapping
    public ResponseEntity<String> createSimulation(@RequestBody SimulationRequestDTO simulationRequestDTO) {
        return ResponseEntity.ok("ok");
    }
}
