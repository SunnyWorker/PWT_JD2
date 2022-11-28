package org.modsen.eventworker.dao.pojo;

import jakarta.persistence.*;
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
    @Column(length = 1024)
    private String description;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person organizer;
    @Column(nullable = false)
    private Timestamp time;
    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

}
