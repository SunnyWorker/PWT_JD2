package org.modsen.common.dao.hibernate.implementation;

import jakarta.persistence.criteria.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.modsen.common.dao.hibernate.PartyDAO;
import org.modsen.common.dao.pojo.Event;
import org.modsen.common.dao.pojo.Party;
import org.modsen.common.services.sorting.enums.SortingMethod;
import org.modsen.common.services.sorting.SortingParameter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PartyDAOHibernate implements PartyDAO {
    private SessionFactory sessionFactory;

    public PartyDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Party> findPartyById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Party party;
        try {
            tx = session.beginTransaction();
            party = session.get(Party.class,id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
        return Optional.ofNullable(party);
    }

    @Override
    public List<Party> findAllParties(List<SortingParameter> sortingParameters)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Party> cr = criteriaBuilder.createQuery(Party.class);
        Root<Party> root = cr.from(Party.class);
        List<Order> orderList = sort(criteriaBuilder,root, sortingParameters);
        cr.select(root);
        cr.orderBy(orderList);
        List<Party> parties = null;
        try {
            tx = session.beginTransaction();
            parties = session.createQuery(cr).getResultList();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return parties;
    }

    protected List<Order> sort(CriteriaBuilder criteriaBuilder, Root<Party> root, List<SortingParameter> sortingParameters) {
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
    public void saveParty(Party party) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(party);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeParty(Party party) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.remove(party);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
