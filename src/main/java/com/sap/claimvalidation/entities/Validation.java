package com.sap.claimvalidation.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="validation")
public class Validation {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column
    private String ruleServiceId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "claim_id", referencedColumnName = "id")
    private Claim vocabulary;

}