package com.ascending.training.repository;

import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.model.Customer;
import com.ascending.training.bowen.model.Merchant;
import com.ascending.training.bowen.model.Restaurant;
import com.ascending.training.bowen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class MappingTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void areaMappingTest() {
        String hql = "FROM Area";
        List<Area> areas = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Area> query = session.createQuery(hql);
            areas = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(areas);
    }

    @Test
    public void restaurantMappingTest(){
        String hql = "FROM Restaurant";
        List<Restaurant> restaurants = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Restaurant> query = session.createQuery(hql);
            restaurants = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(restaurants);
    }

    @Test
    public void merchantMappingTest(){
        String hql = "FROM Merchant";
        List<Merchant> merchants = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Merchant> query = session.createQuery(hql);
            merchants = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(merchants);
    }

    @Test
    public void customerMappingTest(){
        String hql = "FROM Customer";
        List<Customer> customers = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession())
        {
            Query<Customer> query = session.createQuery(hql);
            customers = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(customers);
    }

}

