package com.ascending.training.bowen.service;

import com.ascending.training.bowen.model.Area;
import com.ascending.training.bowen.repository.AreaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaDao areaDao;

    public boolean save(Area area){
        return areaDao.save(area);
    }

    public boolean update(Area area){
        return areaDao.update(area);
    }

    public boolean delete(String areaName){
        return areaDao.delete(areaName);
    }

    public List<Area> getAreas(){
        return areaDao.getAreas();
    }

    public Area getAreaByName(String areaName){
        return areaDao.getAreaByName(areaName);
    }
}
