package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IJobMaster;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

/**
 * Created by SedaOzcan on 9.02.2017.
 */
public interface IJobMasterRequestProcessor  {

    IRestResponse list(String hostname, String dn, String uid);
    IRestResponse add(IJobMaster jobMaster);

    IRestResponse update(IJobMaster jobMaster);

    IRestResponse delete(Long id);

    IRestResponse getProperties();

    IRestResponse findById(Long id);

    IRestResponse findByFilters(IFilterAndPager filterAndPager);

}
