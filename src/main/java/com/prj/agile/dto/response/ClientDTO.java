package com.prj.agile.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Integer id;
    private String document;
    private String name;
    private Date birthDate;
    private Date createdAt;
    private Boolean pep;
    private String email;
    private String status;
    private ClientTypeDTO clientType;
    private AddressDTO clientAddress;
    private List<PhoneDTO> phones;

}


