package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IUlke;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

public interface IUlkeRequestProcessor extends IBaseRequestProcessor {
IRestResponse list(String hostname, String dn, String uid);
IRestResponse add(IUlke Ulke);

IRestResponse update(Object Ulke);

IRestResponse delete(Long id);

IRestResponse getProperties();

IRestResponse findById(Long id);

IRestResponse findByFilters(IFilterAndPager filterAndPager);

}
