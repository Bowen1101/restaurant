package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.model.Customer;
import com.ascending.training.bowen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Customer customer) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The customer %s was inserted into the table.",customer.toString()));

        return isSuccess;
    }

    @Override
    public boolean save(Customer customer, String areaName) {
        Transaction transaction = null;
        boolean isSuccess = true;
        AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Area area = areaDaoImpl.getAreaByName(areaName);
            customer.setArea(area);
            session.save(customer);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The customer %s was inserted into the table.",customer.toString()));

        return isSuccess;
    }

    @Override
    public boolean update(Customer customer) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The customer %s was updated",customer.toString()));

        return isSuccess;
    }

    @Override
    public boolean update(Customer customer, String areaName, String customerName) {
        Transaction transaction = null;
        boolean isSuccess = true;
        AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Area area = areaDaoImpl.getAreaByName(areaName);
            customer.setArea(area);
            customer.setName(customerName);
            session.saveOrUpdate(customer);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The customer %s was updated",customer.toString()));

        return isSuccess;
    }

    @Override
    public boolean delete(String customerName) {
        String hql = "DELETE Customer as c where c.name = :customerName";
        int deleteCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("customerName",customerName);

            transaction = session.beginTransaction();
            deleteCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.debug(e.getMessage());
        }
        logger.debug(String.format("The customer %s was deleted",customerName));

        return deleteCount>=1 ? true:false;
    }

    @Override
    public List<Customer> getCustomers() {
        String hql = "FROM Customer";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        if (customerName == null) return null;

        String hql = "FROM Customer as customer where lower(customer.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("name", customerName.toLowerCase());

            Customer customer = query.uniqueResult();
            if (customer != null) {
                logger.debug(customer.toString());
            }
            return customer;
        }
    }
}
