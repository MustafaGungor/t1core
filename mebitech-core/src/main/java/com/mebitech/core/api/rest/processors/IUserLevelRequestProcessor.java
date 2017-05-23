package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IUserLevel;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

import java.util.HashMap;

public interface IUserLevelRequestProcessor {
    IRestResponse list(String hostname, String dn, String uid);
    IRestResponse add(IUserLevel level, HashMap<String, Object> idMap);
    IRestResponse remove(Long id);
    IRestResponse findById(Long userId, Long levelId);
    IRestResponse addGroup(HashMap<String, Object> idMap);
    IRestResponse removeGroup(HashMap<String, Object> idMap);
    IRestResponse getGroups(Long id);
    IRestResponse getGroups(IFilterAndPager filterAndPager);
    IRestResponse getGroups(IFilterAndPager filterAndPager, Class innerClass);
    IRestResponse getLevels(IFilterAndPager filterAndPager);
}
