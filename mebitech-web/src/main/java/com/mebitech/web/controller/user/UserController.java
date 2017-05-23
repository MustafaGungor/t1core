package com.mebitech.web.controller.user;

import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IUserRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.persistence.entities.UserImpl;
import com.mebitech.persistence.filter.FilterAndPagerImpl;
import com.mebitech.persistence.filter.FilterImpl;
import com.mebitech.web.controller.utils.AController;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by tayipdemircan on 24.10.2016.
 */
@Controller
@RequestMapping("/resources/user")
public class UserController extends AController {

    @Autowired
    private IResponseFactory responseFactory;

    @Autowired
    private IUserRequestProcessor userRequestProcessor;

    //ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/add" , method = RequestMethod.PUT)
    @ResponseBody
    public IRestResponse addNewUser(@RequestBody String user){
        try {

            UserImpl userc = objectMapper.readValue(user,UserImpl.class);
            userc.setCreateDate(new Date());
            IRestResponse restResponse = userRequestProcessor.add(userc);
            return restResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage(),null);
        }
    }


    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public IRestResponse updateUser(@RequestBody String user){
        try {
            UserImpl userc = objectMapper.readValue(user,UserImpl.class);
            IRestResponse restResponse = userRequestProcessor.update(userc);
            return restResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
    @ResponseBody
    public IRestResponse deleteUser(@RequestBody(required = false) String user, @RequestParam(value = "id", required = false) Long id){

        try {
            if(user == null && id == null)
                return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : Silinecek kayıt bulunamadı",null);
            IRestResponse restResponse = null;
            if(user != null) {
                UserImpl userc = objectMapper.readValue(user, UserImpl.class);
                restResponse = userRequestProcessor.delete(userc);

            } else if(id != null) {
                restResponse = userRequestProcessor.deleteById(id);
            }
            return restResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse findAllUsers(HttpServletRequest request){
        FilterAndPagerImpl filterAndPager = getFilters(request);
        return userRequestProcessor.findByFilters(filterAndPager);
    }

    @RequestMapping(value = "/getById" , method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse findById(@RequestParam(value = "id") Long id) {
        return userRequestProcessor.findById(id);
    }

    @RequestMapping(value = "/getProperties" , method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse getProperties() {
        return userRequestProcessor.getProperties();
    }

}
