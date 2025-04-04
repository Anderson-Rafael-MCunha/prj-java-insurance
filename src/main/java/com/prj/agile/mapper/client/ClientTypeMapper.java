package com.prj.agile.mapper.client;

import com.prj.agile.dto.response.ClientTypeDTO;
import com.prj.agile.entity.client.ClientType;

public class ClientTypeMapper {

    public static ClientTypeDTO toDTO(ClientType clientType) {
        ClientTypeDTO dto = new ClientTypeDTO();
        dto.setId(clientType.getId());
        dto.setDescription(clientType.getDescription());
        return dto;
    }

    public static ClientType toEntity(ClientTypeDTO dto) {
        return new ClientType(dto.getDescription());
    }

}
