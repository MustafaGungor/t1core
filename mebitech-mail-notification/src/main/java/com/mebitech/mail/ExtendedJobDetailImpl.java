package com.mebitech.mail;

import com.mebitech.core.api.configuration.IConfigurationService;
import org.quartz.impl.JobDetailImpl;

import java.util.List;

/**
 * Created by SedaOzcan on 30.12.2016.
 */
public class ExtendedJobDetailImpl extends JobDetailImpl {
    private IConfigurationService configurationService;

    private List<String> mailList;

    private String obj,subj;

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public List<String> getMailList() {
        return mailList;
    }

    public void setMailList(List<String> mailList) {
        this.mailList = mailList;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }
}
