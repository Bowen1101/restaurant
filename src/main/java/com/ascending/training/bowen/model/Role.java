package com.ascending.training.bowen.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "allowed_resource")
    private String allowedResource;
    @Column(name = "allowed_read")
    private boolean allowedRead;
    @Column(name = "allowed_create")
    private boolean allowedCreate;
    @Column(name = "allowed_update")
    private boolean allowedUpdate;
    @Column(name = "allowed_delete")
    private boolean allowedDelete;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public List<User> getUsers(){
        return users;
    }

    public void setUsers(User user){
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllowedResource() {
        return allowedResource;
    }

    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }

    public boolean getAllowedRead() {
        return allowedRead;
    }

    public boolean isAllowedRead() {
        return allowedRead;
    }

    public void setAllowedRead(boolean allowedRead) {
        this.allowedRead = allowedRead;
    }

    public boolean getAllowedCreate() {
        return allowedCreate;
    }

    public boolean isAllowedCreate(){
        return allowedCreate;
    }

    public void setAllowedCreate(boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }

    public boolean getAllowedUpdate() {
        return allowedUpdate;
    }

    public boolean isAllowedUpdate(){
        return allowedUpdate;
    }

    public void setAllowedUpdate(boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }

    public boolean getAllowedDelete() {
        return allowedDelete;
    }

    public boolean isAllowedDelete(){
        return allowedDelete;
    }

    public void setAllowedDelete(boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }


}
