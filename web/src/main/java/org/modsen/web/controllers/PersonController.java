package org.modsen.web.controllers;

import org.modsen.common.dao.pojo.Invitation;
import org.modsen.common.dao.pojo.Person;
import org.modsen.common.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get")
    public Person getPersonById(@RequestParam long id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/getAll")
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @PostMapping("/invite")
    public void invitePerson(@RequestBody Invitation invitation) {
        personService.invitePerson(invitation);
    }

    @PostMapping("/add")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PatchMapping("/change")
    public void changePerson(@RequestBody Person person) {
        personService.changePerson(person);
    }

    @DeleteMapping("/delete")
    public void deletePerson(@RequestBody Person person) {
        personService.deletePerson(person);
    }
}
