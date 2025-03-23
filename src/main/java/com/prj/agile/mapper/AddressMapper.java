package com.prj.agile.mapper;

import com.prj.agile.dto.response.AddressDTO;
import com.prj.agile.entity.client.Address;

public class AddressMapper {

    public static AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setCountry(address.getCountry());
        dto.setState(address.getState());
        dto.setCity(address.getCity());
        dto.setNeighborhood(address.getNeighborhood());
        dto.setStreet(address.getStreet());
        dto.setNumber(address.getNumber());
        dto.setComplement(address.getComplement());
        dto.setZipCode(address.getZipCode());
        return dto;
    }

    public static Address toEntity(AddressDTO dto) {
        return new Address(
                dto.getZipCode(),
                dto.getComplement(),
                dto.getNumber(),
                dto.getStreet(),
                dto.getNeighborhood(),
                dto.getCity(),
                dto.getState(),
                dto.getCountry()
        );
    }
}
