package com.mebitech.persistence.entities;

import com.mebitech.core.api.persistence.entities.IUlke;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "ULKE")
public class UlkeImpl implements IUlke {// extends ABaseEntity
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
    @OneToMany(mappedBy = "ulke", fetch = FetchType.EAGER)
    public List<IlImpl> ilList;
    @Transient
    public CrudType[] crudTypes;
    @Transient
    public String[] entityAttributes;

    public UlkeImpl() {
    }

    public UlkeImpl(Long id, Date createDate, Boolean deleted
            , String kodu, String adi, List<IlImpl> ilList) {
        super();
        this.id = id;
        this.deleted = deleted;
        this.createDate = createDate;
        this.kodu = kodu;
        this.adi = adi;
        this.ilList = ilList;
    }

    public UlkeImpl(IUlke ulke) {
        this.deleted = ulke.getDeleted();
        this.id = ulke.getId();
        this.createDate = ulke.getCreateDate();
        this.kodu = ulke.getKodu();
        this.adi = ulke.getAdi();
        this.ilList = (List<IlImpl>) ulke.getIlList();
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

    public List<IlImpl> getIlList() {
        return ilList;
    }

    public void setIlList(List<IlImpl> ilList) {
        this.ilList = ilList;
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
        return "UlkeImpl [id=" + id + "]" +
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