package com.sap.claimvalidation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="claim")
public class Claim {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    @Column
    private String item_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;

    @Column
    private Integer claim_number;

    @Column
    private String version_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "version_id", insertable = false, updatable = false)
    private Version version;

    @Column
    private String header_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "header_id", insertable = false, updatable = false)
    private Header header;
//    @OneToOne(mappedBy = "claim")
//    private Validation validation;


}
