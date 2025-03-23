package com.prj.agile.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimulationRequestDTO {

    private String clientDocument;
    private Integer accumulatedBonus;
    private BigDecimal insuredValue;
    private String product;

}
