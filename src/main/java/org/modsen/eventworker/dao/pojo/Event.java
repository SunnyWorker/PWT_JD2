package org.modsen.eventworker.dao.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Event {
    @Id
    private Long id;
    @Column(nullable = false)
    private String theme;
    private String description;
    @Column(nullable = false)
    @OneToMany
    private Person organizer;
    @Column(nullable = false)
    private Timestamp time;
    @OneToMany
    @Column(nullable = false)
    private Place place;

}
