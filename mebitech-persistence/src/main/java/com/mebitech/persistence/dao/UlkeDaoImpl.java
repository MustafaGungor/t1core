package com.mebitech.persistence.dao;


import com.mebitech.core.api.persistence.dao.IUlkeDao;
import com.mebitech.persistence.entities.UlkeImpl;

@SuppressWarnings("unchecked")
public class UlkeDaoImpl extends ABaseDao<UlkeImpl> implements IUlkeDao {
public UlkeDaoImpl (){
super(UlkeImpl.class);
}
}
