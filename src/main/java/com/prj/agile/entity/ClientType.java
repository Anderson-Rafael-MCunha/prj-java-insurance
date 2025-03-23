package com.prj.agile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ClientType")
@Table(name = "client_type",
        schema= "insurance"
)
public class ClientType {

    @Id
    @SequenceGenerator(
            name = "client_type_sequence",
            sequenceName="client_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_type_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToOne(mappedBy = "clientType")
    private Client client;


    public ClientType(String description) {
        this.description = description;
    }
}