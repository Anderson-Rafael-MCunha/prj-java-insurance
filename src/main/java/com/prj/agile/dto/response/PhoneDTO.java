package com.prj.agile.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {

    private Integer id;
    private String area;
    private String ddd;
    private String number;
    private String phoneType;
    private String phoneStatus;

}
