package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IIlDao;
import com.mebitech.core.api.persistence.entities.IIl;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IIlRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IlRequestProcessorImpl extends BaseRequestProcessor implements IIlRequestProcessor {
    private static Logger logger = LoggerFactory.getLogger(IlRequestProcessorImpl.class);
    private IIlDao IlDao;

    @Override
    public IRestResponse list(String hostname, String dn, String uid) {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        if (hostname != null && !hostname.isEmpty()) {
            propertiesMap.put("hostname", hostname);
        }
        if (dn != null && !dn.isEmpty()) {
            propertiesMap.put("dn", dn);
        }
        if (uid != null && !uid.isEmpty()) {
            propertiesMap.put("jid", uid);
        }
        List<? extends IIl> Iller = (List<? extends IIl>) IlDao.findByProperties(propertiesMap, null, null);
        logger.debug("Found Il: {}", Iller);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("Il", Iller);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseFactory.createResponse(RestResponseStatus.OK, "Records listed.", resultMap);
    }

    @Override
    public IRestResponse add(IIl obj) {
        IIl objc = null;
        try {
            objc = (IIl) IlDao.save(obj);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(Object obj) {
        IIl objc = null;
        try {
            objc = (IIl) IlDao.update(obj);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            IlDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(IlDao.getProperties());
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long id) {
        createResponse(IlDao.find(id));
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = IlDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public void setIlDao(IIlDao IlDao) {
        this.IlDao = IlDao;
    }
}