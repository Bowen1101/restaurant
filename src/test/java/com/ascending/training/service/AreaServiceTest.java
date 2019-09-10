package com.ascending.training.service;

import com.ascending.training.bowen.init.AppInitializer;
import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.service.AreaService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class AreaServiceTest {

    private Area a = new Area();
    private String expectedAreaName = "Crystal City Test";

    @Autowired
    private AreaService areaService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    private AreaDaoImpl areaDaoImpl;


    @Before
    public void init() {
//        areaService = new AreaService();
        a.setAreaName(expectedAreaName);
        a.setStateName("VA Test");
        a.setZipCode(22202);
        areaService.save(a);
    }

    @Test
    public void saveTest() {
        List<Area> areas = areaService.getAreas();
        int expectedNumOfArea =8;
        Assert.assertEquals(expectedNumOfArea, areas.size());
    }

    @Test
    public void updateTest(){
        a.setStateName("VA");
        a.setZipCode(22200);
        a.setAreaName(expectedAreaName);
        areaService.update(a);
        System.out.println(a.getId()+a.getAreaName()+a.getStateName()+a.getZipCode());
        Assert.assertEquals(a.getZipCode(),22200);
    }

    @Test
    public void deleteTest(){
        areaService.delete(expectedAreaName);
        int expectedNumOfArea = 7;
        List<Area> areas = areaService.getAreas();
        Assert.assertEquals(expectedNumOfArea,areas.size());
    }

    @Test
    public void getAreasTest(){
        int expectedNumOfArea = 8;
        List<Area> areas = areaService.getAreas();

        for (Area area : areas) {
            System.out.println(area.getAreaName());
        }

        Assert.assertEquals(expectedNumOfArea,areas.size());
    }

    @Test
    public void getAreaByNameTest(){
        Area b = areaService.getAreaByName(expectedAreaName);
        Assert.assertEquals(expectedAreaName,b.getAreaName());
    }

    @After
    public void cleanup(){
        if (areaService.getAreaByName(expectedAreaName)!=null){
            areaService.delete(expectedAreaName);}

        areaService = null;
    }
}
