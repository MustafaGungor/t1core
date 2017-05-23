package com.mebitech.persistence.dao;


import com.mebitech.core.api.persistence.dao.IIlceDao;
import com.mebitech.persistence.entities.IlceImpl;

@SuppressWarnings("unchecked")
public class IlceDaoImpl extends ABaseDao<IlceImpl> implements IIlceDao {
public IlceDaoImpl (){
super(IlceImpl.class);
}
}
