package com.ascending.training.bowen.repository;

import com.ascending.training.bowen.model.Area;

import java.util.List;

public interface AreaDao {
    boolean save(Area area);
    boolean update(Area area);
    boolean delete(String areaName);
    List<Area> getAreas();
    Area getAreaByName(String areaName);

}
