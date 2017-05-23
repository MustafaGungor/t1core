package com.mebitech.web.controller;

import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IPermissionRequestProcessor;
import com.mebitech.core.api.rest.processors.IUserRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;

import com.mebitech.persistence.entities.UserImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by tayipdemircan on 24.10.2016.
 */
@Controller
@RequestMapping("/resources/login")
public class LoginController {

    static HashMap<String,UserDto> sessionList = new HashMap<String, UserDto>();

    @Autowired
    private IUserRequestProcessor userRequestProcessor;

    @Autowired
    private IPermissionRequestProcessor permissionRequestProcessor;

    @Autowired
    private IResponseFactory responseFactory;

    @RequestMapping(value = "/dologin" , method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public IRestResponse login(@RequestBody UserDto body){
        IRestResponse response = userRequestProcessor.findByUserNameAndPassword(body.getUserName(),body.getPassword(), body.getLevelId());
        Long id = (Long) response.getResultMap().get("data");
        return permissionRequestProcessor.getAuthorizedMenus(id,null);
    }

    @RequestMapping(value = "/logout" , method = {RequestMethod.GET})
    @ResponseBody
    public IRestResponse logout(){
        SecurityUtils.getSubject().logout();
        return responseFactory.createResponse(RestResponseStatus.OK, "Success");

        //return responseFactory.createResponse(RestResponseStatus.OK,"Login basarili",map);
    }

}
