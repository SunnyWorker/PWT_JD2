package org.modsen.web.configuration;

import org.modsen.common.dao.hibernate.EventDAO;
import org.modsen.common.dao.hibernate.implementation.EventDAOHibernate;
import org.modsen.common.services.EventService;
import org.modsen.common.services.implementation.EventServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBeanConfiguration {
    @Bean
    public EventDAO eventDAO() {
        return new EventDAOHibernate();
    }

    @Bean
    public EventService eventService() {
        return new EventServiceImplementation(eventDAO());
    }
}
