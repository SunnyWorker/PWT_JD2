package org.modsen.web.configuration;

import org.hibernate.SessionFactory;
import org.modsen.common.dao.hibernate.InvitationDAO;
import org.modsen.common.dao.hibernate.PartyDAO;
import org.modsen.common.dao.hibernate.PersonDAO;
import org.modsen.common.dao.hibernate.implementation.InvitationDAOHibernate;
import org.modsen.common.dao.hibernate.implementation.PartyDAOHibernate;
import org.modsen.common.dao.hibernate.implementation.PersonDAOHibernate;
import org.modsen.common.services.PartyService;
import org.modsen.common.services.PersonService;
import org.modsen.common.services.implementation.PartyServiceImplementation;
import org.modsen.common.services.implementation.PersonServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonBeanConfiguration {

    @Bean
    @Autowired
    public InvitationDAO invitationDAO(SessionFactory sessionFactory) {
        return new InvitationDAOHibernate(sessionFactory);
    }

    @Bean
    @Autowired
    public PersonDAO personDAO(SessionFactory sessionFactory) {
        return new PersonDAOHibernate(sessionFactory);
    }

    @Bean
    @Autowired
    public PersonService personService(PersonDAO personDAO, InvitationDAO invitationDAO) {
        return new PersonServiceImplementation(personDAO, invitationDAO);
    }
}

