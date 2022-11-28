package org.modsen.eventworker.dao.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Byte age;
}
