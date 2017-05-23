package com.mebitech.rest.util;

import com.mebitech.core.api.persistence.entities.ILevel;
import com.mebitech.core.api.persistence.entities.IUser;
import com.mebitech.core.api.persistence.entities.IUserLevel;
import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.responses.IRestResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tayipdemircan on 27.10.2016.
 */
public abstract class BaseRequestProcessor {

    protected IResponseFactory responseFactory;

    private IRestResponse response;

    private IUserLevel userLevel;

    private  IUser user;

    private ILevel level;

    private boolean errorState = false;

    public void setResponseFactory(IResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public IRestResponse getResponse() {
        return response;
    }

    public void setResponse(IRestResponse response) {
        this.response = response;
    }

    public boolean isErrorState() {
        return errorState;
    }

    public void setErrorState(boolean errorState) {
        this.errorState = errorState;
    }

    protected void Error(String message){
        setErrorState(true);
        response = responseFactory.createResponse(RestResponseStatus.ERROR,message);
    }

    protected void createResponse(Object data){
        Integer count = 0;
        if(data instanceof List)
            count = ((List)data).size();
        createResponse(data,count.longValue());
    }

    protected void createResponse(Object data, Long count){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data",data);
        response = responseFactory.createResponse(RestResponseStatus.OK, "Success", resultMap, count.intValue());
    }

    public IUserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(IUserLevel userLevel) {
        this.userLevel = userLevel;
        setLevel(this.userLevel.getLevel());
        setUser(this.userLevel.getUser());
    }

    public IUser getUser() {
        if(this.user != null)
            return this.user;
        return null;
    }

    public ILevel getLevel() {
        if(this.level != null)
            return this.level;
        return null;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public void setLevel(ILevel level) {
        this.level = level;
    }

}
