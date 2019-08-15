package com.ascending.training.bowen.model;


import javax.persistence.*;

@Entity
@Table(name="merchants")
public class Merchant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="tel")
    private String tel;
    @Column(name="address")
    private String address;
//    @Column(name="restaurant_id")
//    private long restaurantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Restaurant getRestaurant(){
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getTel(){
        return tel;
    }

    public String getAddress(){
        return address;
    }

//    public long getRestaurantId(){
//        return restaurantId;
//    }

//    public void setId(long id){
//        this.id = id;
//    }

    public void setName(String name){
        this.name = name;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public void setAddress(String address){
        this.address = address;
    }

//    public void setRestaurantId(long restaurantId){
//        this.restaurantId = restaurantId;
//    }
}
