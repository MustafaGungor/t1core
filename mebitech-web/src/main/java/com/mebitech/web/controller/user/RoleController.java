package com.mebitech.web.controller.user;

import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IRoleRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.persistence.entities.RoleImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by tayipdemircan on 26.10.2016.
 */
@Controller
@RequestMapping("/resources/role")
public class RoleController {

    @Autowired
    private IResponseFactory responseFactory;

    @Autowired
    private IRoleRequestProcessor roleRequestProcessor;

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/add" , method = RequestMethod.PUT)
    @ResponseBody
    public IRestResponse addNewRole(@RequestBody String json){
        try {
            RoleImpl role = objectMapper.readValue(json,RoleImpl.class);
            role.setCreateDate(new Date());
            return roleRequestProcessor.add(role);
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage());
        }
    }

    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public IRestResponse updateRole(@RequestBody String json){
        try {
            RoleImpl role = objectMapper.readValue(json,RoleImpl.class);
            return roleRequestProcessor.update(role);
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage());
        }
    }

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse getAllRoles(){
        try {
            return roleRequestProcessor.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage());
        }
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
    @ResponseBody
    public IRestResponse deleteRole(@RequestBody(required = false) String json,
                                    @RequestParam(value = "id", required = false) Long id){
        try {
            if(json != null && !json.equals("")) {
                RoleImpl role = objectMapper.readValue(json, RoleImpl.class);
                return roleRequestProcessor.delete(role);
            }else if(id != null && id.longValue() > 0L){
                return roleRequestProcessor.deleteById(id);
            } else {
                return responseFactory.createResponse(RestResponseStatus.ERROR, "Hata : Kayıt bulunamadı!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage());
        }
    }

}
