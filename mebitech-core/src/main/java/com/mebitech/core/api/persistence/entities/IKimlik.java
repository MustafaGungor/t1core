package com.mebitech.core.api.persistence.entities;

import java.util.Date;
import java.util.Map;

import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;

public interface IKimlik extends IEntity<IEntitySecurity> {
    String toJson();

    Map<String, String> getProperties();

    void applyEntitySecurity(IEntitySecurity entitySecurity);

    Boolean getDeleted();

    int getDurum();

    String getKimlikNo();

    String getAd();

    String getSoyad();

    String getBabaAd();

    String getAnaAd();

    int getCinsiyet();

    String getDogumYer();

    Date getDogumTarih();

    String getKimlikCuzdanSeriNo();

    String getKimlikCuzdanNo();

    int getKimlikMedeniHal();

    int getKimlikDin();

    Long getKimlikIl();

    Long getKimlikIlce();

    String getKimlikMahalleKoy();

    String getKimlikCiltNo();

    String getKimlikAileSiraNo();

    String getKimlikSiraNo();

    String getKimlikVerildigiYer();

    String getKimlikVerilisNeden();

    String getKimlikKayitNo();

    Date getKimlikVerilisTarih();
}