package com.prj.agile.entity.insurance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Proposal")
@Table(name = "proposal",
        schema= "insurance"
)
public class Proposal {

    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName="client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name="created_at", columnDefinition = "DATE", nullable = false)
    private Date createdAt;

    @Column(name="proposal_end_date", columnDefinition = "DATE", nullable = false)
    private Date proposalEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposal_budget_id")
    private Budget budget;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Price> priceList = new ArrayList<>();

    public Proposal(Date createdAt, Date proposalEndDate, Budget budget, List<Price> priceList) {
        this.createdAt = createdAt;
        this.proposalEndDate = proposalEndDate;
        this.budget = budget;
        this.priceList = priceList;
    }
}
