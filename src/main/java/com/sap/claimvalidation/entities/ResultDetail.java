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
@Table(name="result_detail")
public class ResultDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column
    private String severity;

    @Column
    private String message;

    @Column
    private String messageClassId;

    @ManyToOne
    @JoinColumn(name="result_id", nullable=false)
    private Result result;

}
