package org.modsen.common.services;

import org.modsen.common.dao.pojo.Invitation;
import org.modsen.common.dao.pojo.Party;
import org.modsen.common.dao.pojo.Person;

import java.util.List;

public interface PersonService {
    Person getPersonById(long id);

    List<Person> getAllPeople();

    void invitePerson(Invitation invitation);

    void addPerson(Person person);

    void changePerson(Person person);

    void deletePerson(Person person);
}
