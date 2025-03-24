package com.prj.agile.service;

import com.prj.agile.config.FeignConfig;
import com.prj.agile.dto.response.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restClient", url = "${client.api.url}", configuration = FeignConfig.class)
public interface RestClient {

    @GetMapping("/getByDocument/{document}")
    ClientDTO getClientByDocument(@PathVariable("document") String document);

}
