package org.modsen.eventworker.dao.hibernate.implementation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.modsen.eventworker.dao.hibernate.EventDAO;
import org.modsen.eventworker.dao.pojo.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventDAOHibernate implements EventDAO {
    @Autowired
    private static SessionFactory sessionFactory;

    @Override
    public Event findEventById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Event event = null;
        try {
            tx = session.beginTransaction();
            event = session.get(Event.class,id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return event;
    }

    @Override
    public List<Event> findAllEvents() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Event> cr = criteriaBuilder.createQuery(Event.class);
        Root<Event> root = cr.from(Event.class);
        cr.select(root);
        List<Event> events = null;
        try {
            tx = session.beginTransaction();
            events = session.createQuery(cr).getResultList();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return events;
    }

    @Override
    public void saveEvent(Event event) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(event);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeEvent(Event event) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.remove(event);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
