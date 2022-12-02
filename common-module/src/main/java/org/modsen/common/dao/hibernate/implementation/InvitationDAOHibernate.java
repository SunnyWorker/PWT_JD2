package org.modsen.common.dao.hibernate.implementation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.modsen.common.dao.hibernate.InvitationDAO;
import org.modsen.common.dao.pojo.Invitation;

public class InvitationDAOHibernate implements InvitationDAO {

    private final SessionFactory sessionFactory;

    public InvitationDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveInvitation(Invitation invitation) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(invitation);
            session.persist(invitation);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
