package com.mebitech.persistence.entities;

import java.util.Date;
import java.util.Map;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import com.mebitech.core.api.persistence.entities.ILevel;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;

@Entity
@Table(name = "C_Level")
public class LevelImpl implements ILevel {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @Column(name = "IS_DELETED")
    private Boolean deleted;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "NAME")
    private String name;


    @Transient
    private CrudType[] crudTypes;
    @Transient
    private String[] entityAttributes;

    public LevelImpl() {
    }

    public LevelImpl(Long id, Date createDate, Boolean deleted
            , boolean active, String name, Long version, Date invoiceDate, Long invoiceNumber) {
        super();
        this.id = id;
        this.deleted = deleted;
        this.createDate = createDate;
        this.active = active;
        this.name = name;

    }

    public LevelImpl(ILevel level) {
        this.deleted = level.getDeleted();
        this.id = level.getId();
        this.createDate = level.getCreateDate();
        this.active = level.getActive();
        this.name = level.getName();
    }

    @JsonIgnore
    @Override
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }

    @Override
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "LevelImpl [id=" + id + "]" +
                ", createDate=" + createDate + "]";
    }

    @Override
    public Map<String, String> getProperties() {
//TODO: Return Table Column Name;
        return null;
    }

    @Override
    public void applyEntitySecurity(IEntitySecurity entitySecurity) {
        crudTypes = entitySecurity.getCrudType();
        entityAttributes = entitySecurity.getEntityAttributes();
    }
}