package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.ILevel;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

import java.util.Map;

public interface ILevelRequestProcessor {
    IRestResponse list(String hostname, String dn, String uid);

    IRestResponse add(ILevel level);

    IRestResponse update(Object level);

    IRestResponse delete(Long id);

    IRestResponse getProperties();

    IRestResponse findById(Long id);

    IRestResponse findByFilters(IFilterAndPager filterAndPager);


}
