package com.ascending.training.bowen.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "areas")
public class Area {

//    @Column(name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "area_name")
    private String areaName;
    @Column(name = "state_name")
    private String stateName;
    @Column(name = "zipcode")
    private long zipCode;




    @OneToMany(mappedBy = "area", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Restaurant> restaurants;

    @OneToMany(mappedBy = "area", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Customer> customers;

    public Set<Restaurant> getRestaurants(){
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants){
        this.restaurants = restaurants;
    }

    public Set<Customer> getCustomers(){
        return customers;
    }

    public void setCustomers(Set<Customer> customers){
        this.customers = customers;
    }

    public long getId(){
        return id;
    }

    public String getAreaName(){
        return areaName;
    }

    public String getStateName(){
        return stateName;
    }

    public long getZipCode(){
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return id == area.id &&
                areaName.equals(area.areaName) &&
                Objects.equals(stateName, area.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areaName, stateName);
    }

    public void setAreaName(String areaName){
        this.areaName = areaName;
    }

    public void setStateName(String stateName){
        this.stateName = stateName;
    }

    public void setZipCode(long zipCode){
        this.zipCode = zipCode;
    }
}
