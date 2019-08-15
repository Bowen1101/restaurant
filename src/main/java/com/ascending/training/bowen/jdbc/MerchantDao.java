package com.ascending.training.bowen.jdbc;

import com.ascending.training.bowen.model.Merchant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MerchantDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/RestaurantNearby_db";
    static final String USER = "admin";
    static final String PASS = "Bws2017!";
    public List<Merchant> getMerchant(){
        List<Merchant> merchants = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM merchants";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                long restaurantId = rs.getLong("restaurant_id");
                Merchant merchant = new Merchant();
//                merchant.setId(id);
                merchant.setName(name);
                merchant.setFirstName(firstName);
                merchant.setLastName(lastName);
                merchant.setEmail(email);
                merchant.setTel(tel);
                merchant.setAddress(address);
//                merchant.setRestaurantId(restaurantId);
                merchants.add(merchant);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return merchants;
    }

    public static void main(String[] args) {
        MerchantDao merchantDao = new MerchantDao();
        List<Merchant> merchants = merchantDao.getMerchant();

        for (Merchant merchant : merchants){
            System.out.println(merchant.getName());
        }
    }
}
