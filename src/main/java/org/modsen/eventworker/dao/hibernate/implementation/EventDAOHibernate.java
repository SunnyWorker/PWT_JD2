package org.modsen.eventworker.dao.hibernate.implementation;

import jakarta.persistence.criteria.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.modsen.eventworker.dao.hibernate.EventDAO;
import org.modsen.eventworker.dao.pojo.Event;
import org.modsen.eventworker.services.sorting.SortingParameter;
import org.modsen.eventworker.services.sorting.enums.SortingMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventDAOHibernate implements EventDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Event> findEventById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Event event;
        try {
            tx = session.beginTransaction();
            event = session.get(Event.class,id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> findAllEvents(List<SortingParameter> sortingParameters)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Event> cr = criteriaBuilder.createQuery(Event.class);
        Root<Event> root = cr.from(Event.class);
        List<Order> orderList = sort(criteriaBuilder,root, sortingParameters);
        cr.select(root);
        cr.orderBy(orderList);
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

    protected List<Order> sort(CriteriaBuilder criteriaBuilder, Root<Event> root, List<SortingParameter> sortingParameters) {
        List<Order> orderList = new ArrayList<>();
        Join join;
        for (SortingParameter sortingParameter : sortingParameters) {
            join = null;
            if(sortingParameter.getJoinEntityNames()!=null) {
                for (String s : sortingParameter.getJoinEntityNames()) {
                    join = root.join(s);
                }
            }
            if(join!=null) addSortingMethod(orderList,criteriaBuilder,join, sortingParameter, sortingParameter.getSortFields());
            else addSortingMethod(orderList,criteriaBuilder,root, sortingParameter, sortingParameter.getSortFields());
        }
        return orderList;
    }

    protected void addSortingMethod(List<Order> orderList,
                                  CriteriaBuilder criteriaBuilder,
                                  From from,
                                  SortingParameter sortingParameter,
                                  String... fieldNames)
    {
        if(sortingParameter==null) return;
        for (String fieldName : fieldNames) {
            if(sortingParameter.getSortingMethod() == SortingMethod.asc)
                orderList.add(criteriaBuilder.asc(from.get(fieldName)));
            else if(sortingParameter.getSortingMethod() == SortingMethod.desc)
                orderList.add(criteriaBuilder.desc(from.get(fieldName)));
        }
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
