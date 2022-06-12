package com.sap.claimvalidation.entities;

import com.sap.claimvalidation.utils.VersionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="version")
public class Version {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column
    private String purchasingOrganization;

    @Column
    private String salesOrganization;

    @Column
    private String distributionChannel;

    @Column
    private VersionStatus versionCategory;

    @Column
    private String personResponsible;

    @OneToOne(mappedBy = "version")
    private Item item;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private Header header;
}