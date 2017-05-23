package com.mebitech.persistence.entities;

import com.mebitech.core.api.persistence.entities.IIlce;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "ILCE")
public class IlceImpl implements IIlce {// extends ABaseEntity
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
    @ManyToOne
    @JoinColumn(name = "IL_ID")
    public IlImpl il;
    @Transient
    public CrudType[] crudTypes;
    @Transient
    public String[] entityAttributes;

    public IlceImpl() {
    }

    public IlceImpl(Long id, Date createDate, Boolean deleted
            , String kodu, String adi, IlImpl il) {
        super();
        this.id = id;
        this.deleted = deleted;
        this.createDate = createDate;
        this.kodu = kodu;
        this.adi = adi;
        this.il = il;
    }

    public IlceImpl(IIlce ilce) {
        this.deleted = ilce.getDeleted();
        this.id = ilce.getId();
        this.createDate = ilce.getCreateDate();
        this.kodu = ilce.getKodu();
        this.adi = ilce.getAdi();
        this.il = (IlImpl) ilce.getIl();
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

    //json'a dönüştürürken sonsuz döngüye giriyordu sadece id ve adını gönderiyoruz
    public Map<String, Object> getIl() {
        Map<String, Object> ret = new HashMap<String, Object>();
        if (il != null) {
            ret.put("id", this.il.getId());
            ret.put("adi", this.il.getAdi());
        }
        return ret;
    }

    //java tarafında kullanabilmek için eklendi
    @JsonIgnore
    public IlImpl getIlObj() {
        return null;
    }

    public void setIl(IlImpl Il) {
        this.il = Il;
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
        return "IlceImpl [id=" + id + "]" +
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