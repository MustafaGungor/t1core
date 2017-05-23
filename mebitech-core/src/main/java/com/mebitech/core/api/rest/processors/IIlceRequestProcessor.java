package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IIlce;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

public interface IIlceRequestProcessor extends IBaseRequestProcessor {
IRestResponse list(String hostname, String dn, String uid);
IRestResponse add(IIlce Ilce);

IRestResponse update(Object Ilce);

IRestResponse delete(Long id);

IRestResponse getProperties();

IRestResponse findById(Long id);

IRestResponse findByFilters(IFilterAndPager filterAndPager);

}
