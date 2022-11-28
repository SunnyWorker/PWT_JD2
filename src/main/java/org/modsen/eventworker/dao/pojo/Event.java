package org.modsen.eventworker.dao.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Event {
    @Id
    private Long id;
    private String theme;
    private String description;
    private Person organizer;
    private Timestamp time;
    private Place place;
}
