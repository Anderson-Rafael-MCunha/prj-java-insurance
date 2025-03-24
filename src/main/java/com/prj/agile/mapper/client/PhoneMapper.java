package com.prj.agile.mapper.client;

import com.prj.agile.dto.response.PhoneDTO;
import com.prj.agile.entity.client.Phone;

public class PhoneMapper {

    public static PhoneDTO toDTO(Phone phone) {
        PhoneDTO dto = new PhoneDTO();
        dto.setId(phone.getId());
        dto.setArea(phone.getArea());
        dto.setDdd(phone.getDdd());
        dto.setNumber(phone.getNumber());
        dto.setPhoneType(phone.getPhoneType());
        dto.setPhoneStatus(phone.getPhoneStatus());
        return dto;
    }

    public static Phone toEntity(PhoneDTO dto) {
        return new Phone(null, dto.getPhoneStatus(), dto.getPhoneType(), dto.getNumber(), dto.getDdd(), dto.getArea());
    }

}
