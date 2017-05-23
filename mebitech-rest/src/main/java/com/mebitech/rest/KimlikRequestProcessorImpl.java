package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IKimlikDao;
import com.mebitech.core.api.persistence.entities.IKimlik;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IKimlikRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KimlikRequestProcessorImpl extends BaseRequestProcessor implements IKimlikRequestProcessor {
    private static Logger logger = LoggerFactory.getLogger(KimlikRequestProcessorImpl.class);
    private IKimlikDao kimlikDao;

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
        List<? extends IKimlik> kimlikler = (List<? extends IKimlik>) kimlikDao.findByProperties(propertiesMap, null, null);
        logger.debug("Found Kimlik: {}", kimlikler);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("Kimlik", kimlikler);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseFactory.createResponse(RestResponseStatus.OK, "Records listed.", resultMap);
    }

    @Override
    public IRestResponse add(IKimlik obj) {
        IKimlik objc = null;
        try {
            objc = (IKimlik) kimlikDao.save(obj);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(IKimlik obj) {
        IKimlik objc = null;
        try {
            objc = (IKimlik) kimlikDao.update(obj);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            kimlikDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(kimlikDao.getProperties());
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long id) {
        createResponse(kimlikDao.find(id));
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = kimlikDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public void setKimlikDao(IKimlikDao kimlikDao) {
        this.kimlikDao = kimlikDao;
    }
}