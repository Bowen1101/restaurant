package com.ascending.training.bowen.jdbc;

import com.ascending.training.bowen.model.Area;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AreaDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/RestaurantNearby_db";
    static final String USER = "admin";
    static final String PASS = "Bws2017!";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public int saveArea(Area area) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = conn.prepareStatement("INSERT INTO areas(area_name,state_name,zipcode) values (?,?,?)");
            ps.setString(1,area.getAreaName());
            ps.setString(2,area.getStateName());
            ps.setLong(3,area.getZipCode());
            status=ps.executeUpdate();
            conn.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public int updateArea(Area area) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = conn.prepareStatement("UPDATE areas set area_name=?, state_name=?, zipcode=? where id =?");
            ps.setString(1,area.getAreaName());
            ps.setString(2,area.getStateName());
            ps.setLong(3,area.getZipCode());
            ps.setLong(4,area.getId());
            status=ps.executeUpdate();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public int deleteArea(String areaName) {
        int status = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = conn.prepareStatement("DELETE from areas where area_name=?");
            ps.setString(1,areaName);
            status=ps.executeUpdate();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public Area getAreaByName(String areaName){
        Area area = new Area();
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = conn.prepareStatement("SELECT * from areas where area_name=?");
            ps.setString(1,areaName);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
//                area.setId(rs.getLong(1));
                area.setAreaName(rs.getString(2));
                area.setStateName(rs.getString(3));
                area.setZipCode(rs.getLong(4));
            }
            conn.close();
            }
        catch(Exception e){
            e.printStackTrace();
        }
        return area;
    }

    public List<Area> getAreas(){
        List<Area> areas = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM areas";
            rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                long id  = rs.getLong("id");
                String areaName = rs.getString("area_name");
                String stateName = rs.getString("state_name");
                long zipCode = rs.getLong("zipcode");
                //Fill the object
                Area area = new Area();
//                area.setId(id);
                area.setAreaName(areaName);
                area.setStateName(stateName);
                area.setZipCode(zipCode);
                areas.add(area);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }

        logger.trace("Trace - Area Size = " + areas.size());
        logger.debug("Debug - Area Size = " + areas.size());
        logger.info("Info - Area Size = " + areas.size());
        logger.warn("Warn - Area Size = " + areas.size());
        logger.error("Error - Area Size = " + areas.size());

        return areas;
    }

    public static void main(String[] args) {
        AreaDao areaDao = new AreaDao();
        List<Area> areas = areaDao.getAreas();
        for (Area area : areas) {
            System.out.println(area.getAreaName());
        }
    }
}
