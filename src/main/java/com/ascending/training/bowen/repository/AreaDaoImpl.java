package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.util.List;


public class AreaDaoImpl implements AreaDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public boolean save(Area area){
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(area);
            transaction.commit();

        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }


        if (isSuccess) logger.debug(String.format("The area %s was inserted into the table.",area.toString()));

        return isSuccess;
    }

    @Override
    public boolean update(Area area) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(area);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The area %s was updated", area.toString()));

        return isSuccess;
    }

    @Override
    public boolean delete(String areaName) {
        String hql = "DELETE Area where areaName = :areaName";
        int deletedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("areaName", areaName);

            transaction = session.beginTransaction();
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction !=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The area %s was deleted", areaName));

        return deletedCount >=1 ? true : false;
    }

    @Override
    public List<Area> getAreas() {
        String hql = "FROM Area";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Area> query = session.createQuery(hql);
            return query.list();
        }

    }

    @Override
    public Area getAreaByName(String areaName) {
        if (areaName == null) return null;

        String hql = "FROM Area as a where lower(a.areaName) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Area> query = session.createQuery(hql);
            query.setParameter("name", areaName.toLowerCase());

            Area area = query.uniqueResult();
            if (area != null) {
            logger.debug(area.toString());}
//            logger.debug(area.getAreaName().toString());

            return area;
        }

    }

}
