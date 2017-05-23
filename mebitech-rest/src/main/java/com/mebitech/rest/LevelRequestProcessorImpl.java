package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.ILevelDao;
import com.mebitech.core.api.persistence.entities.ILevel;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.processors.ILevelRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelRequestProcessorImpl extends BaseRequestProcessor implements ILevelRequestProcessor {
    private static Logger logger = LoggerFactory.getLogger(LevelRequestProcessorImpl.class);
    private ILevelDao levelDao;

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
        propertiesMap.put("deleted", false);
        List leveller = levelDao.findByProperties(propertiesMap, null, null);
        logger.debug("Found Level: {}", leveller);
        try {
            createResponse(leveller);
        } catch (Exception e) {
            Error(e.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse add(ILevel level) {
        ILevel levelc = null;
        try {
            levelc = (ILevel) levelDao.save(level);
            createResponse(levelc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }

        return getResponse();
    }

    @Override
    public IRestResponse update(Object level) {
        ILevel levelc = null;
        try {
            levelc = (ILevel) levelDao.update(level);
            createResponse(levelc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }

        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            levelDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }

        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(levelDao.getProperties());
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long id) {
        createResponse(levelDao.find(id));
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = levelDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public void setLevelDao(ILevelDao levelDao) {
        this.levelDao = levelDao;
    }

//    public void setResponseFactory(IResponseFactory responseFactory) {
//        this.responseFactory = responseFactory;
//    }
}