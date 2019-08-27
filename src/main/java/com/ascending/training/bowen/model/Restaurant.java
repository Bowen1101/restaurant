package com.ascending.training.bowen.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="category")
    private String category;
    @Column(name="address")
    private String address;
    @Column(name="tel")
    private String tel;
    @Column(name="starrated")
    private int starrated;
//    @Column(name="area_id")
//    private long areaId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Merchant> merchants;

    public Set<Merchant> getMerchants(){
        return merchants;
    }

    public void setMerchants(Set<Merchant> merchants){
        this.merchants = merchants;
    }

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

    public String getCategory(){
        return category;
    }

    public String getAddress(){
        return address;
    }

    public String getTel(){
        return tel;
    }

    public int getStarrated(){
        return starrated;
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

    public void setCategory(String category){
        this.category = category;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public void setStarrated(int starrated){
        this.starrated = starrated;
    }

//    public void setAreaId(long areaId){
//        this.areaId = areaId;
//    }
}

