package com.mebitech.persistence.dao;

import com.mebitech.core.api.persistence.dao.IJobMasterDao;
import com.mebitech.persistence.entities.JobMasterImpl;

/**
 * Created by SedaOzcan on 9.02.2017.
 */
public class JobMasterDaoImpl extends ABaseDao<JobMasterImpl> implements IJobMasterDao{
    public JobMasterDaoImpl () { super (JobMasterImpl.class);
    }
}
