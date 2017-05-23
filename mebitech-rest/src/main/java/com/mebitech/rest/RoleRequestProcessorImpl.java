package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IRoleDao;
import com.mebitech.core.api.persistence.entities.IRole;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.processors.IRoleRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;

import java.util.List;
import java.util.Map;

/**
 * Created by tayipdemircan on 26.10.2016.
 */
public class RoleRequestProcessorImpl extends BaseRequestProcessor implements IRoleRequestProcessor {

    private IRoleDao roleDao;

    @Override
    public IRestResponse add(IRole role) {
        IRole rolec = null;
        try {
            rolec = (IRole) roleDao.save(role);
            createResponse(rolec);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(Object role) {
        IRole rolec = null;
        try {
            rolec = (IRole) roleDao.update(role);
            createResponse(rolec);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(IRole role) {
        return deleteById(role.getId());
    }

    @Override
    public IRestResponse deleteById(Long id) {
        try {
            roleDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse findAll() {
        List roles = roleDao.findAll(0);
        createResponse(roles);
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = roleDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long id) {
        createResponse(roleDao.find(id));
        return getResponse();
    }

    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
