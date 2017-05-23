package com.mebitech.persistence.entities;

import com.mebitech.core.api.persistence.entities.IIl;
import com.mebitech.core.api.persistence.entities.IUlke;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "IL")
public class IlImpl implements IIl {//extends ABaseEntity
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    public Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OLUSTURMA_TARIHI", nullable = false)
    public Date createDate;
    @Column(name = "SILINDI")
    public Boolean deleted;
    @Column(name = "KODU")
    public String kodu;
    @Column(name = "ADI")
    public String adi;
    @OneToMany(mappedBy = "il", fetch = FetchType.EAGER)
    public List<IlceImpl> ilceList;
    @ManyToOne
    @JoinColumn(name = "ULKE_ID")
    public UlkeImpl ulke;
    @Transient
    public CrudType[] crudTypes;
    @Transient
    public String[] entityAttributes;

    public IlImpl() {
    }

    public IlImpl(Long id, Date createDate, Boolean deleted
            , String kodu, String adi, List<IlceImpl> ilceList, UlkeImpl ulke) {
        super();
        this.id = id;
        this.deleted = deleted;
        this.createDate = createDate;
        this.ilceList = ilceList;
        this.kodu = kodu;
        this.adi = adi;
        this.ulke = ulke;
    }

    public IlImpl(IIl il) {
        this.deleted = il.getDeleted();
        this.id = il.getId();
        this.createDate = il.getCreateDate();
        this.kodu = il.getKodu();
        this.adi = il.getAdi();
        this.ilceList =  (List<IlceImpl>)il.getIlceList();
        this.ulke = (UlkeImpl) il.getUlke();
    }

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

    public List<IlceImpl> getIlceList() {
        return ilceList;
    }

    public void setIlceList(List<IlceImpl> ilceList) {
        this.ilceList = ilceList;
    }

    public Map<String, Object> getUlke() {
        Map<String, Object> ret = new HashMap<String, Object>();
        if (ulke != null) {
            ret.put("id", ulke.getId());
            ret.put("adi", ulke.getAdi());
        }
        return ret;
    }

    @JsonIgnore
    public IUlke getUlkeObj() {
        return null;
    }

    public void setUlke(UlkeImpl ulke) {
        this.ulke = ulke;
    }

    public String getKodu() {
        return this.kodu;
    }

    public void setKodu(String kodu) {
        this.kodu = kodu;
    }

    public String getAdi() {
        return this.adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
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
        return "IlImpl [id=" + id + "]" +
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