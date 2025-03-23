package com.prj.agile.entity.client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Adress")
@Table(name = "address",
        schema= "insurance"
)
public class Address {
    @Id
    @SequenceGenerator(
            name = "adress_sequence",
            sequenceName="adress_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adress_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "country", nullable = false, columnDefinition = "TEXT")
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "neighborhood", nullable = false, columnDefinition = "TEXT")
    private String neighborhood;

    @Column(name = "street", nullable = false, columnDefinition = "TEXT")
    private String street;

    @Column(name = "number", nullable = false, columnDefinition = "INTEGER")
    private Integer number;

    @Column(name = "complement", nullable = false, columnDefinition = "TEXT")
    private String complement;

    @Column(name = "zipcode", nullable = false, columnDefinition = "TEXT")
    private String zipCode;

    @OneToMany(mappedBy = "clientAddress")
    private List<Client> clients = new ArrayList<>();

    public Address(String zipCode, String complement, Integer number, String street, String neighborhood, String city, String state, String country) {
        this.zipCode = zipCode;
        this.complement = complement;
        this.number = number;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}