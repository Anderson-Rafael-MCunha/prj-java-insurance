package com.prj.agile.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SimulationResponseDTO {

    private Integer proposalId;
    private Date proposalCreatedDate;
    private Date proposalEndDate;
    private List<PriceResponseDTO> priceResponseList;


}
