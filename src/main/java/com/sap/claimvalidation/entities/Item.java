package com.sap.claimvalidation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Integer item_number;


    @Column
    private String material;

    @Column
    private String partCausingDamage;

    @Column
    private String type;

    @Column
    private String amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "version_id", referencedColumnName = "id")
    private Version version;


}
