package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IIl;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

public interface IIlRequestProcessor extends IBaseRequestProcessor {
IRestResponse list(String hostname, String dn, String uid);
IRestResponse add(IIl Il);

IRestResponse update(Object Il);

IRestResponse delete(Long id);

IRestResponse getProperties();

IRestResponse findById(Long id);

IRestResponse findByFilters(IFilterAndPager filterAndPager);

}
