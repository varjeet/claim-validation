package com.sap.claimvalidation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="header")
public class Header {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String registartion_date;
    private String damage_date;
    private String  repair_start_Date;
    private String repair_end_date;

    private String customer;
    private String object_type;
    private String object_number;
    private String source;
    private String source_object_number;

    @OneToOne(mappedBy = "header")
    private Version version;

}


