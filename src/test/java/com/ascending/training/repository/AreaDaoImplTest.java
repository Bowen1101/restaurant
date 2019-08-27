package com.ascending.training.repository;

import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.repository.AreaDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AreaDaoImplTest {
    private AreaDaoImpl areaDaoImpl;
    private Area a = new Area();
    private String expectedAreaName = "Crystal City Test";

    @Before
    public void init() {
        areaDaoImpl = new AreaDaoImpl();
        a.setAreaName(expectedAreaName);
        a.setStateName("VA Test");
        a.setZipCode(22202);
        areaDaoImpl.save(a);
    }

    @Test
    public void saveTest() {
        int expectedNumOfArea = 5;
        List<Area> areas = areaDaoImpl.getAreas();
        Assert.assertEquals(expectedNumOfArea, areas.size());
    }

    @Test
    public void updateTest(){
        a.setStateName("VA");
        a.setZipCode(22200);
        a.setAreaName(expectedAreaName);
        areaDaoImpl.update(a);
        System.out.println(a.getId()+a.getAreaName()+a.getStateName()+a.getZipCode());
        Assert.assertEquals(a.getZipCode(),22200);
    }

    @Test
    public void deleteTest(){
        areaDaoImpl.delete(expectedAreaName);
        int expectedNumOfArea = 4;
        List<Area> areas = areaDaoImpl.getAreas();
        Assert.assertEquals(expectedNumOfArea,areas.size());
    }

    @Test
    public void getAreasTest(){
        int expectedNumOfArea = 5;
        List<Area> areas = areaDaoImpl.getAreas();

        for (Area area : areas) {
            System.out.println(area.getAreaName());
        }

        Assert.assertEquals(expectedNumOfArea,areas.size());
    }

    @Test
    public void getAreaByNameTest(){
       Area b = areaDaoImpl.getAreaByName(expectedAreaName);
       Assert.assertEquals(expectedAreaName,b.getAreaName());
    }

    @After
    public void cleanup(){
        if (areaDaoImpl.getAreaByName(expectedAreaName)!=null){
            areaDaoImpl.delete(expectedAreaName);}

        areaDaoImpl = null;
    }
}
