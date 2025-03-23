package com.prj.agile.service.impl;

import com.prj.agile.dto.request.SimulationRequestDTO;
import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.service.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    private final RestClient restClient;
    private final InsuredService insuredService;


    @Autowired
    public SimulationService(RestClient restClient, InsuredService insuredService){
        this.restClient = restClient;
        this.insuredService = insuredService;
    }

    public ClientDTO fetchClient(String document){
        ClientDTO dto;
        try{
            dto = restClient.getClientByDocument(document);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println(dto);
        return dto;
    }


    public String createSimulation(SimulationRequestDTO simulationRequestDTO){
        // Valida cliente e salva no schema
        ClientDTO clientDTO = fetchClient(simulationRequestDTO.getClientDocument());
        if(clientDTO != null){
            insuredService.persistInsuredData(clientDTO);
        } else {
            String e = "Cliente nao cadastrado. Acionar time de onboarding";
            System.out.println(e);
            return e;
        }
        return "OK";
    }

}
