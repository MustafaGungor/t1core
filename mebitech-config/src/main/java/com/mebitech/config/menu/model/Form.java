package com.mebitech.config.menu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tayipdemircan on 9.11.2016.
 */
@XmlRootElement(name = "form")
@XmlAccessorType(XmlAccessType.FIELD)
public class Form {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String path;

    @XmlElement(name = "permission")
    private Set<Permission> permissionList;

    @JsonIgnore
    private Map<String,Permission> permissions= new HashMap<String, Permission>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Map<String, Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Map<String, Permission> permissions) {
        this.permissions = permissions;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
