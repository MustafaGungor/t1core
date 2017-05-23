package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IGroupDao;
import com.mebitech.core.api.persistence.entities.IGroup;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.processors.IGroupRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;

import java.util.List;
import java.util.Map;

/**
 * Created by tayipdemircan on 28.10.2016.
 */
public class GroupRequestProcessorImpl extends BaseRequestProcessor implements IGroupRequestProcessor {
    private IGroupDao groupDao;

    @Override
    public IRestResponse add(IGroup group) {
        IGroup groupc = null;

        try {
            groupc = (IGroup) groupDao.save(group);
            createResponse(groupc);
        } catch (Exception e) {
            Error(e.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(Object group) {
        IGroup groupc = null;

        try {
            groupc = (IGroup) groupDao.update(group);
            createResponse(groupc);
        } catch (Exception e) {
            Error(e.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            groupDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }

        return getResponse();
    }

    @Override
    public IRestResponse findAll() {
        List groups = groupDao.findAll(0);
        createResponse(groups);
        return getResponse();
    }

    @Override
    public IRestResponse findUsersByGroupId(Long id) {
        return null;
    }

    @Override
    public IRestResponse findById(Long id) {
        IGroup group = (IGroup) groupDao.find(id);
        createResponse(group);
        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(groupDao.getProperties());
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = groupDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public IGroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }
}
