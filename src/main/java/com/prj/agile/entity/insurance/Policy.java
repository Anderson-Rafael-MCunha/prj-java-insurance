package com.prj.agile.entity.insurance;

import com.prj.agile.entity.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Policy")
@Table(name = "policy",
        schema= "insurance"
)
public class Policy {

    @Id
    @SequenceGenerator(
            name = "policy_sequence",
            sequenceName="policy_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name="init_date", columnDefinition = "DATE", nullable = false)
    private Date initDate;

    @Column(name="endDate", columnDefinition = "DATE", nullable = false)
    private Date endDate;

    @Column(name="broker", columnDefinition = "TEXT", nullable = false)
    private String broker;

    @Column(name="insurance_company", columnDefinition = "TEXT", nullable = false)
    private String insuranceCompany;

    @Column(name="susep_subscripton_id", columnDefinition = "INTEGER", nullable = false)
    private Integer susepSubscriptionId;

    @Column(name="additional_information", columnDefinition = "TEXT", nullable = false)
    private String additionalInformation;

    @Column(name="payment_condition", columnDefinition = "INTEGER", nullable = false)
    private Integer paymentCondition;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "policy_price_id")
    private Price price;

    @ManyToMany
    @JoinTable(
            name = "policy_beneficiary",
            joinColumns = @JoinColumn(name = "policy_id"),
            inverseJoinColumns = @JoinColumn(name = "beneficiary_id")
    )
    private Set<Client> beneficiarylist = new HashSet<>();

    public Policy(Date initDate, Date endDate, String broker, String insuranceCompany, Integer susepSubscriptionId, String additionalInformation, Integer paymentCondition, Price price, Set<Client> beneficiarylist) {
        this.initDate = initDate;
        this.endDate = endDate;
        this.broker = broker;
        this.insuranceCompany = insuranceCompany;
        this.susepSubscriptionId = susepSubscriptionId;
        this.additionalInformation = additionalInformation;
        this.paymentCondition = paymentCondition;
        this.price = price;
        this.beneficiarylist = beneficiarylist;
    }
}