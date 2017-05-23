package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IRole;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

/**
 * Created by tayipdemircan on 26.10.2016.
 */
public interface IRoleRequestProcessor {
    IRestResponse add(IRole role);
    IRestResponse update(Object role);
    IRestResponse delete(IRole role);
    IRestResponse deleteById(Long id);
    IRestResponse findAll();
    IRestResponse findByFilters(IFilterAndPager filterAndPager);
    IRestResponse findById(Long id);
}
