package com.prj.agile.service.impl;

import com.prj.agile.dto.response.ClientDTO;
import com.prj.agile.entity.client.Address;
import com.prj.agile.entity.client.Client;
import com.prj.agile.entity.client.ClientType;
import com.prj.agile.entity.client.Phone;
import com.prj.agile.exception.InsuredPersistenceException;
import com.prj.agile.mapper.AddressMapper;
import com.prj.agile.mapper.ClientMapper;
import com.prj.agile.mapper.ClientTypeMapper;
import com.prj.agile.mapper.PhoneMapper;
import com.prj.agile.repository.AddressRepository;
import com.prj.agile.repository.ClientRepository;
import com.prj.agile.repository.ClientTypeRepository;
import com.prj.agile.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuredService {

    private final ClientRepository clientRepository;
    private final ClientTypeRepository clientTypeRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;

    public InsuredService(ClientRepository clientRepository, ClientTypeRepository clientTypeRepository,
                         AddressRepository addressRepository, PhoneRepository phoneRepository) {
        this.clientRepository = clientRepository;
        this.clientTypeRepository = clientTypeRepository;
        this.addressRepository = addressRepository;
        this.phoneRepository = phoneRepository;
    }

    public void persistInsuredData(ClientDTO clientDTO){
        try {
            ClientType clientType = clientTypeRepository.save(ClientTypeMapper.toEntity(clientDTO.getClientType()));

            Address address = addressRepository.save(AddressMapper.toEntity(clientDTO.getClientAddress()));

            List<Phone> phones = clientDTO.getPhones().stream().map(PhoneMapper::toEntity).collect(Collectors.toList());

            Client client = clientRepository.save(ClientMapper.toEntity(clientDTO, clientType, address, phones));

            for (Phone phone : phones) {
                phone.setClient(client);
                phoneRepository.save(phone);
            }
        } catch (Exception e){
            throw new InsuredPersistenceException(e.getMessage());
        }
    }



}
