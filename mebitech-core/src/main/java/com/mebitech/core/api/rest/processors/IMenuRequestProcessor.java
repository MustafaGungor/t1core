package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IMenu;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

public interface IMenuRequestProcessor extends IBaseRequestProcessor {
    IRestResponse list(String hostname, String dn, String uid);

    IRestResponse add(IMenu menu);

    IRestResponse update(IMenu menu);

    IRestResponse delete(Long id);

    IRestResponse getProperties();

    IRestResponse findById(Long id);

    IRestResponse findByFilters(IFilterAndPager filterAndPager);

    IRestResponse getMenuList();
}
