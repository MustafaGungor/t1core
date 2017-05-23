package com.mebitech.persistence.dao;


import com.mebitech.core.api.persistence.dao.IIlDao;
import com.mebitech.persistence.entities.IlImpl;

@SuppressWarnings("unchecked")
public class IlDaoImpl extends ABaseDao<IlImpl> implements IIlDao {
public IlDaoImpl (){
super(IlImpl.class);
}
}
