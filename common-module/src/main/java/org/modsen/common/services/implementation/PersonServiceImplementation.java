package org.modsen.common.services.implementation;

import org.modsen.common.dao.hibernate.InvitationDAO;
import org.modsen.common.dao.hibernate.PersonDAO;
import org.modsen.common.dao.pojo.Invitation;
import org.modsen.common.dao.pojo.Party;
import org.modsen.common.dao.pojo.Person;
import org.modsen.common.exceptions.NoSuchEntityFoundException;
import org.modsen.common.services.PersonService;

import java.util.List;

public class PersonServiceImplementation implements PersonService {

    private PersonDAO personDAO;
    private InvitationDAO invitationDAO;

    public PersonServiceImplementation(PersonDAO personDAO, InvitationDAO invitationDAO) {
        this.personDAO = personDAO;
        this.invitationDAO = invitationDAO;
    }

    @Override
    public Person getPersonById(long id) {
        return personDAO.getPersonById(id).orElseThrow(()->new NoSuchEntityFoundException("Person with id "+id+" not found!"));
    }

    @Override
    public List<Person> getAllPeople() {
        return personDAO.getAllPeople();
    }

    @Override
    public void invitePerson(Invitation invitation) {
        invitationDAO.saveInvitation(invitation);
    }

    @Override
    public void addPerson(Person person) {
        personDAO.savePerson(person);
    }

    @Override
    public void changePerson(Person person) {
        personDAO.savePerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        personDAO.removePerson(person);
    }
}
