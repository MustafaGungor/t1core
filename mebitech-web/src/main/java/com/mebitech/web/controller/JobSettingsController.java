package com.mebitech.web.controller;

import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IJobMasterRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.mail.ExtendedJobDetailImpl;
import com.mebitech.persistence.entities.JobMasterImpl;
import com.mebitech.persistence.filter.FilterAndPagerImpl;
import com.mebitech.persistence.filter.FilterImpl;
import com.mebitech.web.controller.utils.AController;
import org.apache.commons.collections.map.HashedMap;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SedaOzcan on 10.02.2017.
 */
@Controller
@RequestMapping("/resources/jobSetting")
public class JobSettingsController extends AController {

    private static Logger logger = LoggerFactory.getLogger(JobSettingsController.class);
    @Autowired
    private IResponseFactory responseFactory;

    @Autowired
    private IJobMasterRequestProcessor jobMasterRequestProcessor;

    @RequestMapping(value = "/createJob", method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse createJob(@RequestParam(value = "id") Long id) {

        try {
            FilterAndPagerImpl filterAndPager = new FilterAndPagerImpl();
            JobMasterImpl jobDetay = (JobMasterImpl) jobMasterRequestProcessor.findById(id).getResultMap().get("data");
            List<String> mailList = new ArrayList<String>();
            FilterImpl filter = new FilterImpl();
            filter.setProperty("jobDetay");
            filter.setOperator("=");
            filter.setType("Long");
            filter.setValue(jobDetay);
            List<FilterImpl> list = new ArrayList<FilterImpl>();
            list.add(filter);
            filterAndPager.setFilters(list);

            ExtendedJobDetailImpl job = new ExtendedJobDetailImpl();
//            job.setName("triggerName3");
//            job.setJobClass(MailServiceImpl.class);
//            job.setConfigurationService(configurationService);
//            job.setMailList(mailList);
//            job.setObj("abc");
//            job.setSubj("sdf");

            CronTriggerImpl trigger = new CronTriggerImpl();
            trigger.setName("triggerName3");
            trigger.setCronExpression("0 " + jobDetay.getDakika() + " " + jobDetay.getSaat() + " ? * " + jobDetay.getGun());

            //schedule it
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);

            return responseFactory.createResponse(RestResponseStatus.OK, "Gönderildi : ");
        } catch (Exception ex) {
            return responseFactory.createResponse(RestResponseStatus.ERROR, "Hata : " + ex.getMessage());
        }
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse findById(@RequestParam(value = "id") Long id) {

        FilterAndPagerImpl  filterAndPagerJobDetay = new FilterAndPagerImpl();
        FilterImpl filterJobDetay = new FilterImpl();
        //filterJobDetay.setProperty("jobId");      oluşturulan job Id sine göre sorgu yapılır
        filterJobDetay.setValue(id);
        filterJobDetay.setOperator("=");
        filterJobDetay.setType("Long");
        List<FilterImpl> listJobDetay=new ArrayList<FilterImpl>();
        listJobDetay.add(filterJobDetay);
        filterAndPagerJobDetay.setFilters(listJobDetay);

        Map<String,Object> map=new HashedMap();
        map.put("data",((List<Object>) jobMasterRequestProcessor.findByFilters(filterAndPagerJobDetay).getResultMap().get("data")).get(0));
        return responseFactory.createResponse(RestResponseStatus.OK, "Success",map );
    }

}
