package com.prj.agile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Integer id;
    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;
    private String complement;
    private String zipCode;

}
