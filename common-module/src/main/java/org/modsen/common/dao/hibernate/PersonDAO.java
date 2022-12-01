package org.modsen.common.dao.hibernate;

import org.modsen.common.dao.pojo.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {
    Optional<Person> getPersonById(long id);

    List<Person> getAllPeople();

    void savePerson(Person person);

    void removePerson(Person person);
}
