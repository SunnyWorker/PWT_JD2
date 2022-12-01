package org.modsen.common.dao.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Organization {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 1024)
    private String description;
}
