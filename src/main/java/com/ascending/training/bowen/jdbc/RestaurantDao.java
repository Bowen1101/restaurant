package com.ascending.training.bowen.jdbc;

import com.ascending.training.bowen.model.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/RestaurantNearby_db";
    static final String USER = "admin";
    static final String PASS = "Bws2017!";
    public List<Restaurant> getRestaurant(){
        List<Restaurant> restaurants = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM restaurants";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String address = rs.getString("address");
                String tel = rs.getString("tel");
                int starrated = rs.getInt("starrated");
                long areaId = rs.getLong("area_id");
                Restaurant restaurant = new Restaurant();
//                restaurant.setId(id);
                restaurant.setName(name);
                restaurant.setCategory(category);
                restaurant.setAddress(address);
                restaurant.setTel(tel);
                restaurant.setStarrated(starrated);
//                restaurant.setAreaId(areaId);
                restaurants.add(restaurant);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return restaurants;
    }

    public static void main(String[] args) {
        RestaurantDao restaurantDao = new RestaurantDao();
        List<Restaurant> restaurants = restaurantDao.getRestaurant();

        for(Restaurant restaurant : restaurants){
            System.out.println(restaurant.getName());
        }
    }
}
