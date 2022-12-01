package org.modsen.common.dao.hibernate.implementation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.modsen.common.dao.hibernate.PersonDAO;
import org.modsen.common.dao.pojo.Party;
import org.modsen.common.dao.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PersonDAOHibernate implements PersonDAO {
    private SessionFactory sessionFactory;

    public PersonDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Person> getPersonById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Person person;
        try {
            tx = session.beginTransaction();
            person = session.get(Person.class,id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return Optional.ofNullable(person);
    }

    @Override
    public List<Person> getAllPeople() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Person> cr = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = cr.from(Person.class);
        cr.select(root);
        List<Person> people = null;
        try {
            tx = session.beginTransaction();
            people = session.createQuery(cr).getResultList();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return people;
    }

    @Override
    public void savePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(person);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removePerson(Person person) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.remove(person);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
