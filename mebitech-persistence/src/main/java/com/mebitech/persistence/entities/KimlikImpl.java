package com.mebitech.persistence.entities;

import java.util.Date;
import java.util.Map;
import javax.persistence.*;

import com.mebitech.core.api.persistence.entities.IKimlik;
import org.codehaus.jackson.map.ObjectMapper;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;

@Entity
@Table(name = "KIMLIK")
public class KimlikImpl implements IKimlik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//@GeneratedValue(generator = "SEQ_KIMLIK", strategy = GenerationType.SEQUENCE)
//@SequenceGenerator(name = "SEQ_KIMLIK", sequenceName = "SEQ_KIMLIK",initialValue=1)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OLUSTURMA_TARIHI", nullable = false)
    private Date createDate;
    @Column(name = "SILINDI")
    private Boolean deleted;
    @Column(name = "DURUM", length = 2)
    private int durum;
    @Column(name = "KIMLIK_NO", length = 20)
    private String kimlikNo;
    @Column(name = "AD", length = 30)
    private String ad;
    @Column(name = "SOYAD", length = 30)
    private String soyad;
    @Column(name = "BABA_AD", length = 30)
    private String babaAd;
    @Column(name = "ANA_AD", length = 30)
    private String anaAd;
    @Column(name = "CINSIYET", length = 2)
    private int cinsiyet;
    @Column(name = "DOGUM_YER", length = 30)
    private String dogumYer;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DOGUM_TARIH")
    private Date dogumTarih;
    @Column(name = "KIMLIK_CUZDAN_SERI_NO", length = 20)
    private String kimlikCuzdanSeriNo;
    @Column(name = "KIMLIK_CUZDAN_NO", length = 20)
    private String kimlikCuzdanNo;
    @Column(name = "KIMLIK_MEDENI_HAL", length = 2)
    private int kimlikMedeniHal;
    @Column(name = "KIMLIK_DIN", length = 2)
    private int kimlikDin;
    @Column(name = "KIMLIK_IL_ID")
    private Long kimlikIl;
    @Column(name = "KIMLIK_ILCE_IL")
    private Long kimlikIlce;
    @Column(name = "KIMLIK_MAHALLE_KOY", length = 30)
    private String kimlikMahalleKoy;
    @Column(name = "KIMLIK_CILT_NO", length = 30)
    private String kimlikCiltNo;
    @Column(name = "KIMLIK_AILE_SIRA_NO", length = 30)
    private String kimlikAileSiraNo;
    @Column(name = "KIMLIK_SIRA_NO", length = 30)
    private String kimlikSiraNo;
    @Column(name = "KIMLIK_VERILDIGI_YER", length = 30)
    private String kimlikVerildigiYer;
    @Column(name = "KIMLIK_VERILIS_NEDEN", length = 30)
    private String kimlikVerilisNeden;
    @Column(name = "KIMLIK_KAYIT_NO", length = 30)
    private String kimlikKayitNo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "KIMLIK_VERILIS_TARIH")
    private Date kimlikVerilisTarih;
    @Transient
    private CrudType[] crudTypes;
    @Transient
    private String[] entityAttributes;

    public KimlikImpl() {
    }

    public KimlikImpl(Long id, Date createDate, Boolean deleted
            , int durum, String kimlikNo, String ad, String soyad, String babaAd, String anaAd, int cinsiyet, String dogumYer, Date dogumTarih, String kimlikCuzdanSeriNo, String kimlikCuzdanNo, int kimlikMedeniHal, int kimlikDin, Long kimlikIl, Long kimlikIlce, String kimlikMahalleKoy, String kimlikCiltNo, String kimlikAileSiraNo, String kimlikSiraNo, String kimlikVerildigiYer, String kimlikVerilisNeden, String kimlikKayitNo, Date kimlikVerilisTarih) {
        super();
        this.id = id;
        this.deleted = deleted;
        this.createDate = createDate;
        this.durum = durum;
        this.kimlikNo = kimlikNo;
        this.ad = ad;
        this.soyad = soyad;
        this.babaAd = babaAd;
        this.anaAd = anaAd;
        this.cinsiyet = cinsiyet;
        this.dogumYer = dogumYer;
        this.dogumTarih = dogumTarih;
        this.kimlikCuzdanSeriNo = kimlikCuzdanSeriNo;
        this.kimlikCuzdanNo = kimlikCuzdanNo;
        this.kimlikMedeniHal = kimlikMedeniHal;
        this.kimlikDin = kimlikDin;
        this.kimlikIl = kimlikIl;
        this.kimlikIlce = kimlikIlce;
        this.kimlikMahalleKoy = kimlikMahalleKoy;
        this.kimlikCiltNo = kimlikCiltNo;
        this.kimlikAileSiraNo = kimlikAileSiraNo;
        this.kimlikSiraNo = kimlikSiraNo;
        this.kimlikVerildigiYer = kimlikVerildigiYer;
        this.kimlikVerilisNeden = kimlikVerilisNeden;
        this.kimlikKayitNo = kimlikKayitNo;
        this.kimlikVerilisTarih = kimlikVerilisTarih;
    }

    public KimlikImpl(IKimlik kimlik) {
        this.deleted = kimlik.getDeleted();
        this.id = kimlik.getId();
        this.createDate = kimlik.getCreateDate();
        this.durum = kimlik.getDurum();
        this.kimlikNo = kimlik.getKimlikNo();
        this.ad = kimlik.getAd();
        this.soyad = kimlik.getSoyad();
        this.babaAd = kimlik.getBabaAd();
        this.anaAd = kimlik.getAnaAd();
        this.cinsiyet = kimlik.getCinsiyet();
        this.dogumYer = kimlik.getDogumYer();
        this.dogumTarih = kimlik.getDogumTarih();
        this.kimlikCuzdanSeriNo = kimlik.getKimlikCuzdanSeriNo();
        this.kimlikCuzdanNo = kimlik.getKimlikCuzdanNo();
        this.kimlikMedeniHal = kimlik.getKimlikMedeniHal();
        this.kimlikDin = kimlik.getKimlikDin();
        this.kimlikIl = kimlik.getKimlikIl();
        this.kimlikIlce = kimlik.getKimlikIlce();
        this.kimlikMahalleKoy = kimlik.getKimlikMahalleKoy();
        this.kimlikCiltNo = kimlik.getKimlikCiltNo();
        this.kimlikAileSiraNo = kimlik.getKimlikAileSiraNo();
        this.kimlikSiraNo = kimlik.getKimlikSiraNo();
        this.kimlikVerildigiYer = kimlik.getKimlikVerildigiYer();
        this.kimlikVerilisNeden = kimlik.getKimlikVerilisNeden();
        this.kimlikKayitNo = kimlik.getKimlikKayitNo();
        this.kimlikVerilisTarih = kimlik.getKimlikVerilisTarih();
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

    public int getDurum() {
        return this.durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }

    public String getKimlikNo() {
        return this.kimlikNo;
    }

    public void setKimlikNo(String kimlikNo) {
        this.kimlikNo = kimlikNo;
    }

    public String getAd() {
        return this.ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return this.soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getBabaAd() {
        return this.babaAd;
    }

    public void setBabaAd(String babaAd) {
        this.babaAd = babaAd;
    }

    public String getAnaAd() {
        return this.anaAd;
    }

    public void setAnaAd(String anaAd) {
        this.anaAd = anaAd;
    }

    public int getCinsiyet() {
        return this.cinsiyet;
    }

    public void setCinsiyet(int cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getDogumYer() {
        return this.dogumYer;
    }

    public void setDogumYer(String dogumYer) {
        this.dogumYer = dogumYer;
    }

    public Date getDogumTarih() {
        return this.dogumTarih;
    }

    public void setDogumTarih(Date dogumTarih) {
        this.dogumTarih = dogumTarih;
    }

    public String getKimlikCuzdanSeriNo() {
        return this.kimlikCuzdanSeriNo;
    }

    public void setKimlikCuzdanSeriNo(String kimlikCuzdanSeriNo) {
        this.kimlikCuzdanSeriNo = kimlikCuzdanSeriNo;
    }

    public String getKimlikCuzdanNo() {
        return this.kimlikCuzdanNo;
    }

    public void setKimlikCuzdanNo(String kimlikCuzdanNo) {
        this.kimlikCuzdanNo = kimlikCuzdanNo;
    }

    public int getKimlikMedeniHal() {
        return this.kimlikMedeniHal;
    }

    public void setKimlikMedeniHal(int kimlikMedeniHal) {
        this.kimlikMedeniHal = kimlikMedeniHal;
    }

    public int getKimlikDin() {
        return this.kimlikDin;
    }

    public void setKimlikDin(int kimlikDin) {
        this.kimlikDin = kimlikDin;
    }

    public Long getKimlikIl() {
        return this.kimlikIl;
    }

    public void setKimlikIl(Long kimlikIl) {
        this.kimlikIl = kimlikIl;
    }

    public Long getKimlikIlce() {
        return this.kimlikIlce;
    }

    public void setKimlikIlce(Long kimlikIlce) {
        this.kimlikIlce = kimlikIlce;
    }

    public String getKimlikMahalleKoy() {
        return this.kimlikMahalleKoy;
    }

    public void setKimlikMahalleKoy(String kimlikMahalleKoy) {
        this.kimlikMahalleKoy = kimlikMahalleKoy;
    }

    public String getKimlikCiltNo() {
        return this.kimlikCiltNo;
    }

    public void setKimlikCiltNo(String kimlikCiltNo) {
        this.kimlikCiltNo = kimlikCiltNo;
    }

    public String getKimlikAileSiraNo() {
        return this.kimlikAileSiraNo;
    }

    public void setKimlikAileSiraNo(String kimlikAileSiraNo) {
        this.kimlikAileSiraNo = kimlikAileSiraNo;
    }

    public String getKimlikSiraNo() {
        return this.kimlikSiraNo;
    }

    public void setKimlikSiraNo(String kimlikSiraNo) {
        this.kimlikSiraNo = kimlikSiraNo;
    }

    public String getKimlikVerildigiYer() {
        return this.kimlikVerildigiYer;
    }

    public void setKimlikVerildigiYer(String kimlikVerildigiYer) {
        this.kimlikVerildigiYer = kimlikVerildigiYer;
    }

    public String getKimlikVerilisNeden() {
        return this.kimlikVerilisNeden;
    }

    public void setKimlikVerilisNeden(String kimlikVerilisNeden) {
        this.kimlikVerilisNeden = kimlikVerilisNeden;
    }

    public String getKimlikKayitNo() {
        return this.kimlikKayitNo;
    }

    public void setKimlikKayitNo(String kimlikKayitNo) {
        this.kimlikKayitNo = kimlikKayitNo;
    }

    public Date getKimlikVerilisTarih() {
        return this.kimlikVerilisTarih;
    }

    public void setKimlikVerilisTarih(Date kimlikVerilisTarih) {
        this.kimlikVerilisTarih = kimlikVerilisTarih;
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
        return "KimlikImpl [id=" + id + "]" +
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