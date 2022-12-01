package org.modsen.web.configuration;

import org.modsen.common.dao.hibernate.PartyDAO;
import org.modsen.common.dao.hibernate.implementation.PartyDAOHibernate;
import org.modsen.common.services.PartyService;
import org.modsen.common.services.implementation.PartyServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartyBeanConfiguration {
    @Bean
    public PartyDAO eventDAO() {
        return new PartyDAOHibernate();
    }

    @Bean
    public PartyService eventService() {
        return new PartyServiceImplementation(eventDAO());
    }
}
