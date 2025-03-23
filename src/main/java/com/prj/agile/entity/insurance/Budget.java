package com.prj.agile.entity.insurance;

import com.prj.agile.entity.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Budget")
@Table(name = "budget",
        schema= "insurance"
)
public class Budget {

    @Id
    @SequenceGenerator(
            name = "budget_sequence",
            sequenceName="budget_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name="created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Date createdAt;

    @Column(name="accumulated_bonus", columnDefinition = "INTEGER", nullable = false)
    private Integer accumulatedBonus;

    @Column(name="insured_value", columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal insuredValue;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "budget_client_id")
    private Client client;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "budget_product_id")
    private Product product;

    @OneToOne(mappedBy = "budget")
    private Proposal proposal;


}