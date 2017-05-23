package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IIlceDao;
import com.mebitech.core.api.persistence.entities.IIlce;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IIlceRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IlceRequestProcessorImpl extends BaseRequestProcessor implements IIlceRequestProcessor {
    private static Logger logger = LoggerFactory.getLogger(IlceRequestProcessorImpl.class);
    private IIlceDao IlceDao;

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
        List<? extends IIlce> Ilceler = (List<? extends IIlce>) IlceDao.findByProperties(propertiesMap, null, null);
        logger.debug("Found Ilce: {}", Ilceler);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("Ilce", Ilceler);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseFactory.createResponse(RestResponseStatus.OK, "Records listed.", resultMap);
    }

    @Override
    public IRestResponse add(IIlce obj) {
        IIlce objc = null;
        try {
            objc = (IIlce) IlceDao.save(obj);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(Object obj) {
        IIlce objc = null;
        try {
            objc = (IIlce) IlceDao.update(obj);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            IlceDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(IlceDao.getProperties());
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long id) {
        createResponse(IlceDao.find(id));
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = IlceDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public void setIlceDao(IIlceDao IlceDao) {
        this.IlceDao = IlceDao;
    }
}