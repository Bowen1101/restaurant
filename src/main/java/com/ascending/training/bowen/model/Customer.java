package com.ascending.training.bowen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class Customer {
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
//    @Column(name="area_id")
//    private long areaId;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    public Area getArea(){
        return area;
    }

    public void setArea(Area area){
        this.area = area;
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

//    public long getAreaId(){
//        return areaId;
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

//    public void setAreaId(long areaId){
//        this.areaId = areaId;
//    }
}
