package org.modsen.common.dao.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invitation {
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Id
    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;
}
