package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IUlkeDao;
import com.mebitech.core.api.persistence.entities.IUlke;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IUlkeRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UlkeRequestProcessorImpl extends BaseRequestProcessor implements IUlkeRequestProcessor {
    private static Logger logger = LoggerFactory.getLogger(UlkeRequestProcessorImpl.class);
    private IUlkeDao ulkeDao;

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
        List<? extends IUlke> ulkeler = (List<? extends IUlke>) ulkeDao.findByProperties(propertiesMap, null, null);
        logger.debug("Found Ulke: {}", ulkeler);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("Ulke", ulkeler);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseFactory.createResponse(RestResponseStatus.OK, "Records listed.", resultMap);
    }

    @Override
    public IRestResponse add(IUlke obj) {
        IUlke objc = null;
        try {
            objc = (IUlke) ulkeDao.save(obj);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(Object obj) {
        IUlke objc = null;
        try {
            objc = (IUlke) ulkeDao.update(obj);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            ulkeDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(ulkeDao.getProperties());
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long id) {
        createResponse(ulkeDao.find(id));
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = ulkeDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public void setUlkeDao(IUlkeDao ulkeDao) {
        this.ulkeDao = ulkeDao;
    }
}