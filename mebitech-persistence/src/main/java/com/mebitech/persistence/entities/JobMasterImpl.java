package com.mebitech.persistence.entities;

import com.mebitech.core.api.persistence.entities.IJobMaster;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by SedaOzcan on 9.02.2017.
 */
@Entity
@Table(name= "JOB_MASTER")
public class JobMasterImpl implements IJobMaster{
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OLUSTURMA_TARIHI", nullable = false)
    private Date createDate;
    @Column(name = "SILINDI")
    private Boolean deleted;
    @Column(name = "GUN",length = 50)
    private String gun;
    @Column(name = "SAAT", length = 50)
    private String saat;
    @Column(name = "DAKIKA", length = 50)
    private String dakika;

    @Transient
    private CrudType[] crudTypes;
    @Transient
    private String[] entityAttributes;

    public JobMasterImpl() {
    }

    public JobMasterImpl(Long id, Date createDate, Boolean deleted, String gun, String saat, String dakika) {
        this.id=id;
        this.createDate = createDate;
        this.deleted = deleted;
        this.gun = gun;
        this.saat = saat;
        this.dakika = dakika;
    }

    public JobMasterImpl(IJobMaster jobMaster) {
        this.id=jobMaster.getId();
        this.createDate = jobMaster.getCreateDate();
        this.deleted = jobMaster.getDeleted();
        this.gun = jobMaster.getGun();
        this.saat = jobMaster.getSaat();
        this.dakika = jobMaster.getDakika();
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

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Boolean getDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getGun() {
        return gun;
    }

    public void setGun(String gun) {
        this.gun = gun;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getDakika() {
        return dakika;
    }

    public void setDakika(String dakika) {
        this.dakika = dakika;
    }

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
        return "JobMasterImpl [id=" + id + "]" +
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
