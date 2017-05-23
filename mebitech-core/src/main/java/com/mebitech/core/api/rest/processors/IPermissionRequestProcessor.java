package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IPermission;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

import java.util.HashMap;

public interface IPermissionRequestProcessor {
    IRestResponse list(String hostname, String dn, String uid);
    IRestResponse getAuthorizedMenus(Long id, Long groupId);
    IRestResponse add(IPermission permission);
    IRestResponse delete(Long id);
}
