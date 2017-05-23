package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.IUser;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.responses.IRestResponse;

import java.util.HashMap;

/**
 * Created by tayipdemircan on 24.10.2016.
 */
public interface IUserRequestProcessor {
    IRestResponse add(IUser user);
    IRestResponse update(Object user);
    IRestResponse delete(IUser user);
    IRestResponse deleteById(Long id);
    IRestResponse findAll();
    IRestResponse findByUserNameAndPassword(String userName,String password, Long levelId);
//    IRestResponse findAuthorizedMenusByUserId(Long userId);
    IRestResponse findById(Long userId);
//    IRestResponse addGroup(HashMap<String, Object> idMap);
//    IRestResponse removeGroup(HashMap<String, Object> idMap);
    IRestResponse getProperties();

//    void setCurrentUser(IUser user);

    IRestResponse findByFilters(IFilterAndPager filterAndPager);
}
