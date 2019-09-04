package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.model.Restaurant;
import com.ascending.training.bowen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class RestaurantDaoImpl implements RestaurantDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Restaurant restaurant) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(restaurant);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The restaurant %s was inserted into the table.", restaurant.toString()));

        return isSuccess;
    }

    @Override
    public boolean save(Restaurant restaurant, String areaName) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            AreaDaoImpl areaDaoImpl = new AreaDaoImpl();
            Area area = areaDaoImpl.getAreaByName(areaName);
            restaurant.setArea(area);
            session.save(restaurant);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The restaurant %s was inserted into the table.", restaurant.toString()));

        return isSuccess;
    }

    @Override
    public boolean update(Restaurant restaurant) {
        Transaction transaction = null;
        boolean isSuccess= true;

        try (Session session = HibernateUtil.getSessionFactory().openSession() ) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(restaurant);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The restaurant $s was updated", restaurant.toString()));

        return isSuccess;
    }

    @Override
    public boolean update(Restaurant restaurant, String areaName, String name) {
        Transaction transaction = null;
        boolean isSuccess= true;

        try (Session session = HibernateUtil.getSessionFactory().openSession() ) {
            transaction = session.beginTransaction();
            AreaDaoImpl areaDaoImpl = new AreaDaoImpl();
            Area area = areaDaoImpl.getAreaByName(areaName);
            restaurant.setArea(area);
            restaurant.setName(name);
            session.saveOrUpdate(restaurant);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The restaurant $s was updated", restaurant.toString()));

        return isSuccess;

    }

    @Override
    public boolean delete(String restaurantName) {
        String hql = "DELETE Restaurant where name = :restaurantName";
        int deleteCount= 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("restaurantName", restaurantName);

            transaction = session.beginTransaction();
            deleteCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.debug(e.getMessage());
        }

        logger.debug(String.format("The restaurant %s was deleted",restaurantName));

        return deleteCount>=1 ? true:false;
    }

    @Override
    public List<Restaurant> getRestaurants() {
        String hql = "FROM Restaurant as res left join fetch res.merchants";
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Restaurant> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Restaurant getRestaurantByName(String restaurantName) {
        if (restaurantName == null) return null;

        String hql = "FROM Restaurant as res left join fetch res.merchants where lower(res.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Restaurant> query = session.createQuery(hql);
            query.setParameter("name", restaurantName.toLowerCase());

            Restaurant restaurant = query.uniqueResult();
            if(restaurant !=null) {
                logger.debug(restaurant.toString());
            }
            return restaurant;
        }
    }
}
