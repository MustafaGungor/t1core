package com.mebitech.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Created by seda on 23.11.2016.
 */
@MappedSuperclass
public abstract class ABaseEntity implements Serializable {

    @Version
    @JsonIgnore
    protected Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

//    @Id
//    @GeneratedValue
//    @Column(name = "ID", nullable = false)
//    private Long id;
//
//    @Column(name = "IS_DELETED")
//    private Boolean deleted;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "CREATE_DATE", nullable = false)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "MODIFY_DATE")
//    private Date modifyDate;
//
//    private CrudType[] crudTypes;
//
//    private String[] entityAttributes;
//
//    public ABaseEntity(Boolean deleted, Date createDate, Date modifyDate, CrudType[] crudTypes, String[] entityAttributes) {
//        this.deleted = deleted;
//        this.createDate = createDate;
//        this.modifyDate = modifyDate;
//        this.crudTypes = crudTypes;
//        this.entityAttributes = entityAttributes;
//    }
//
//    public ABaseEntity() {
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Boolean getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(Boolean deleted) {
//        this.deleted = deleted;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    public Date getModifyDate() {
//        return modifyDate;
//    }
//
//    public void setModifyDate(Date modifyDate) {
//        this.modifyDate = modifyDate;
//    }
//
//    public CrudType[] getCrudTypes() {
//        return crudTypes;
//    }
//
//    public void setCrudTypes(CrudType[] crudTypes) {
//        this.crudTypes = crudTypes;
//    }
//
//    public String[] getEntityAttributes() {
//        return entityAttributes;
//    }
//
//    public void setEntityAttributes(String[] entityAttributes) {
//        this.entityAttributes = entityAttributes;
//    }


}
