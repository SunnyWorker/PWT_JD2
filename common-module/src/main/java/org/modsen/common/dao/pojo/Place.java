package org.modsen.common.dao.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String country;
    private String city;
    private String street;
    private Integer buildingNumber;
    private Integer additionalBuildingNumber;
    @Column(length = 1024)
    private String additionalDescription;
}
