package com.prj.agile.mapper.client;

import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.dto.response.PhoneDTO;
import com.prj.agile.entity.client.Address;
import com.prj.agile.entity.client.Client;
import com.prj.agile.entity.client.ClientType;
import com.prj.agile.entity.client.Phone;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setDocument(client.getDocument());
        dto.setName(client.getName());
        dto.setBirthDate(client.getBirthDate());
        dto.setCreatedAt(client.getCreatedAt());
        dto.setPep(client.getPep());
        dto.setEmail(client.getEmail());
        dto.setStatus(client.getStatus());
        dto.setClientType(ClientTypeMapper.toDTO(client.getClientType()));
        dto.setClientAddress(AddressMapper.toDTO(client.getClientAddress()));
        dto.setPhones(client.getPhones().stream().map(PhoneMapper::toDTO).collect(Collectors.toList()));
        return dto;
    }

    public static Client toEntity(ClientDTO dto, ClientType clientType, Address address, List<Phone> phones) {
        Client client = new Client(
                dto.getDocument(),
                dto.getName(),
                dto.getBirthDate(),
                dto.getCreatedAt(),
                dto.getPep(),
                dto.getEmail(),
                dto.getStatus(),
                clientType,
                address
        );
        client.setPhones(phones);
        return client;
    }

    public static Client toEntity(ClientDTO dto) {
        Client client = new Client(
                dto.getDocument(),
                dto.getName(),
                dto.getBirthDate(),
                dto.getCreatedAt(),
                dto.getPep(),
                dto.getEmail(),
                dto.getStatus(),
                ClientTypeMapper.toEntity(dto.getClientType()),
                AddressMapper.toEntity(dto.getClientAddress())
        );
        client.setId(dto.getId());
        List<Phone> phones = Optional.ofNullable(dto.getPhones())
                .orElse(Collections.emptyList())
                .stream()
                .map(PhoneMapper::toEntity)
                .collect(Collectors.toList());

        client.setPhones(phones);
        return client;
    }
}
