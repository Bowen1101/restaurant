package com.ascending.training.bowen.jdbc;

import com.ascending.training.bowen.model.Customer;
import com.ascending.training.bowen.model.Merchant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/RestaurantNearby_db";
    static final String USER = "admin";
    static final String PASS = "Bws2017!";
    public List<Customer> getCustomer(){
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM customers";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                long areaId = rs.getLong("area_id");
                Customer customer = new Customer();
                customer.setName(name);
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(email);
                customer.setTel(tel);
                customer.setAddress(address);
//                customer.setAreaId(areaId);
                customers.add(customer);


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
        return customers;
    }

    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.getCustomer();

        for (Customer customer : customers){
            System.out.println(customer.getName());
        }
    }
}
