package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IJobMasterDao;
import com.mebitech.core.api.persistence.entities.IJobMaster;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IJobMasterRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SedaOzcan on 10.02.2017.
 */
public class JobMasterRequestProcessorImpl extends BaseRequestProcessor implements IJobMasterRequestProcessor {

    private static Logger logger = LoggerFactory.getLogger(JobMasterRequestProcessorImpl.class);
    private IJobMasterDao jobMasterDao;


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
        List<? extends IJobMaster> jobMasters = (List<? extends IJobMaster>) jobMasterDao.findByProperties(propertiesMap, null, null);
        logger.debug("Found JobMaster: {}", jobMasters);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("JobMaster", jobMasters);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseFactory.createResponse(RestResponseStatus.OK, "Records listed.", resultMap);
    }

    @Override
    public IRestResponse add(IJobMaster jobMaster) {
        IJobMaster objc = null;
        try {
            objc = (IJobMaster) jobMasterDao.save(jobMaster);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(IJobMaster jobMaster) {
        IJobMaster objc = null;
        try {
            objc = (IJobMaster) jobMasterDao.update(jobMaster);
            createResponse(objc);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            jobMasterDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(jobMasterDao.getProperties());
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long id) {
        createResponse(jobMasterDao.find(id));
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = jobMasterDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public void setJobMasterDao(IJobMasterDao jobMasterDao) {
        this.jobMasterDao = jobMasterDao;
    }
}
