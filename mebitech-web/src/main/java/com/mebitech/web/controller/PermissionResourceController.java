package com.mebitech.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.persistence.entities.PermissionImpl;
import com.mebitech.persistence.filter.FilterAndPagerImpl;
import com.mebitech.persistence.filter.FilterImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mebitech.core.api.log.IOperationLogService;
import com.mebitech.core.api.persistence.enums.CrudType;
import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.processors.IPermissionRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;

@Controller
@RequestMapping("/resources/permission")
public class PermissionResourceController {
    private static Logger logger = LoggerFactory.getLogger(PermissionResourceController.class);
    @Autowired
    private IResponseFactory responseFactory;
    @Autowired
    private IPermissionRequestProcessor permissionProcessor;
    @Autowired
    private IOperationLogService logService;

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public IRestResponse listPermission(@RequestParam(value = "hostname", required = false) String hostname,
                                        @RequestParam(value = "dn", required = false) String dn,
                                        @RequestParam(value = "uid", required = false) String uid, HttpServletRequest request)
            throws UnsupportedEncodingException {
        try {
            logService.saveLog(uid, CrudType.READ, "Get Permission List", null, hostname);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        logger.info("Request received. URL: '/resources/permission'", new Object[]{hostname, dn, uid});
        IRestResponse restResponse = permissionProcessor.list(hostname, dn, uid);
        logger.debug("Completed processing request, returning result: {}", restResponse.toJson());
        return restResponse;
    }

    @RequestMapping(value = "/add", method = {RequestMethod.PUT})
    @ResponseBody
    public IRestResponse addPermission(@RequestBody String data){
//        HashMap<String,Object> map = (HashMap<String, Object>) data;

        try {
            PermissionImpl permission = objectMapper.readValue(data,PermissionImpl.class);
            permission.setCreateDate(new Date());
            permission.setDeleted(false);
            return permissionProcessor.add(permission);
        } catch (IOException e) {
            return responseFactory.createResponse(RestResponseStatus.ERROR,e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    @ResponseBody
    public IRestResponse removePermission(@RequestParam(value = "id") Long id){
        return permissionProcessor.delete(id);
    }

    @RequestMapping(value = "/getAuthorizedMenus", method = {RequestMethod.GET})
    @ResponseBody
    public IRestResponse getAuthorizedMenus(@RequestParam(value = "userId", required = false) Long id,
                                            @RequestParam(value = "groupId", required = false) Long groupId){
        if(id == null && groupId == null)
            id = 402L;
        if(id != null)
            groupId = null;

        return permissionProcessor.getAuthorizedMenus(id,groupId);
    }
}