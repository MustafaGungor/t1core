package com.mebitech.rest.dto;

import com.mebitech.core.api.persistence.entities.IUser;

import java.util.Date;
import java.util.List;

/**
 * Created by tayipdemircan on 1.11.2016.
 */
public class UserDto {
    public UserDto(){

    }

    public UserDto(IUser obj){
        setActive(obj.getActive());
        setCreateDate(obj.getCreateDate());
        setEmail(obj.getEmail());
        setId(obj.getId());
        setName(obj.getName());
        setSurname(obj.getSurname());
        setUserName(obj.getUserName());
    }

    private Long id;
    private String userName;
    private String email;
    private String name;
    private String surname;
    private Boolean active;
    private Date createDate;
    private List<SimpleDto> groups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<SimpleDto> getGroups() {
        return groups;
    }

    public void setGroups(List<SimpleDto> groups) {
        this.groups = groups;
    }
}
