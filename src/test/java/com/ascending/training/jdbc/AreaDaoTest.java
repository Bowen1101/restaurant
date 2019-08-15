package com.ascending.training.jdbc;

import com.ascending.training.bowen.jdbc.AreaDao;
import com.ascending.training.bowen.model.Area;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;



public class AreaDaoTest {
    private AreaDao areaDao;
    private Area a = new Area();
    private String expectedAreaName = "Crystal City Test";
    @Before
    public void init(){
        areaDao = new AreaDao();
        a.setAreaName(expectedAreaName);
        a.setStateName("VA Test");
        a.setZipCode(22202);
        areaDao.saveArea(a);
    }

    @Test
    public void saveAreaTest() {
        int expectedNumOfArea = 5;
        List<Area> areas = areaDao.getAreas();
        Assert.assertEquals(expectedNumOfArea,areas.size());
    }

    @Test
    public void updateAreaTest(){
        a.setZipCode(22200);
        a.setAreaName(expectedAreaName);
//        a.setId(5);
        areaDao.updateArea(a);
        System.out.println(a.getId()+a.getAreaName()+a.getStateName()+a.getZipCode());
        Assert.assertEquals(a.getZipCode(),22200);
    }

    @Test
    public void deleteAreaTest(){
        areaDao.deleteArea(expectedAreaName);
        int expectedNumOfArea = 4;
        List<Area> areas = areaDao.getAreas();
        Assert.assertEquals(expectedNumOfArea,areas.size());
    }

    @Test
    public void getAreaByNameTest(){
        Area b = areaDao.getAreaByName(expectedAreaName);
        Assert.assertEquals(expectedAreaName,b.getAreaName());
    }

    @Test
    public void getAreasTest() {
        int expectedNumOfArea = 5;
        List<Area> areas = areaDao.getAreas();

        for (Area area : areas) {
            System.out.println(area.getAreaName());
        }

        Assert.assertEquals(expectedNumOfArea,areas.size());

    }

    @After
    public void cleanup(){
        if (areaDao.getAreaByName(expectedAreaName)!=null){
        areaDao.deleteArea(expectedAreaName);}

        areaDao = null;
    }
}
