package org.modsen.web.configuration;

import org.hibernate.SessionFactory;
import org.modsen.common.dao.hibernate.PartyDAO;
import org.modsen.common.dao.hibernate.implementation.PartyDAOHibernate;
import org.modsen.common.services.PartyService;
import org.modsen.common.services.implementation.PartyServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartyBeanConfiguration {


    @Bean
    @Autowired
    public PartyDAO partyDAO(SessionFactory sessionFactory) {
        return new PartyDAOHibernate(sessionFactory);
    }

    @Bean
    @Autowired
    public PartyService partyService(PartyDAO partyDAO) {
        return new PartyServiceImplementation(partyDAO);
    }
}
