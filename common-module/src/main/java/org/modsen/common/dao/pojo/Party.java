package org.modsen.common.dao.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person organizer;
    @Column(nullable = false)
    private Timestamp time;
    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;
}
