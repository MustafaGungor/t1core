package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IKimlik;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;

public interface IKimlikRequestProcessor {
IRestResponse list(String hostname, String dn, String uid);
IRestResponse add(IKimlik kimlik);

IRestResponse update(IKimlik kimlik);

IRestResponse delete(Long id);

IRestResponse getProperties();

IRestResponse findById(Long id);

IRestResponse findByFilters(IFilterAndPager filterAndPager);

}
