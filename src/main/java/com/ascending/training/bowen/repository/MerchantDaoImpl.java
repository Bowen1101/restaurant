package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Merchant;
import com.ascending.training.bowen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class MerchantDaoImpl implements MerchantDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Merchant merchant) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(merchant);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.debug(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The merchant %s was inserted into the table.",merchant.toString()));

        return isSuccess;
    }

    @Override
    public boolean update(Merchant merchant) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(merchant);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.debug(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The merchant %s was updated",merchant.toString()));

        return isSuccess;
    }

    @Override
    public boolean delete(String merchantName) {
        String hql = "DELETE Merchant where name = :merchantName";
        int deleteCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("merchantName",merchantName);

            transaction = session.beginTransaction();
            deleteCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.debug(e.getMessage());
        }
        logger.debug(String.format("The merchant %s was deleted",merchantName));
        return deleteCount>=1 ? true:false;
    }

    @Override
    public List<Merchant> getMerchants() {
        String hql = "FROM Merchant";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Merchant> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Merchant getMerchantByName(String merchantName) {
        if (merchantName == null) return  null;

        String hql = "FROM Merchant as m where lower(m.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Merchant> query = session.createQuery(hql);
            query.setParameter("name", merchantName.toLowerCase());

            Merchant merchant = query.uniqueResult();
            if(merchant != null) {
                logger.debug(merchant.toString());
            }
            return merchant;
        }
    }
}
