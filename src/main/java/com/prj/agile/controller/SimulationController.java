package com.prj.agile.controller;

import com.prj.agile.dto.request.SimulationRequestDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.service.impl.SimulationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/insurance")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService){
        this.simulationService = simulationService;
    }

    @Operation(summary = "Confere cadastro do cliente", description = "Endpoint dedicado a conferir existencia do cliente na simulacao do seguro")
    @GetMapping("/getByDocument/{document}")
    public ResponseEntity<ClientDTO> getClientByDocument(@PathVariable String document) {
        Optional<ClientDTO> clientDTO = Optional.ofNullable(simulationService.fetchClient(document));
        return clientDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria nova Simulacao do premio", description = "Esse endpoint simula valores para contratacao de um seguro")
    @PostMapping
    public ResponseEntity<String> createSimulation(@RequestBody SimulationRequestDTO simulationRequestDTO) {
        simulationService.createSimulation(simulationRequestDTO);
        return ResponseEntity.ok("ok");
    }

}
