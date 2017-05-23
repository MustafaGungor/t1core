package com.mebitech.persistence.dao;


import com.mebitech.core.api.persistence.dao.IKimlikDao;
import com.mebitech.persistence.entities.KimlikImpl;

@SuppressWarnings("unchecked")
public class KimlikDaoImpl extends ABaseDao<KimlikImpl> implements IKimlikDao {
public KimlikDaoImpl (){
super(KimlikImpl.class);
}
}
