package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IGroup;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

/**
 * Created by tayipdemircan on 28.10.2016.
 */
public interface IGroupRequestProcessor {
    IRestResponse add(IGroup group);
    IRestResponse update(Object group);
    IRestResponse delete(Long id);
    IRestResponse findAll();
    IRestResponse findUsersByGroupId(Long id);
    IRestResponse findById(Long id);

    IRestResponse getProperties();

    IRestResponse findByFilters(IFilterAndPager filterAndPager);
}
