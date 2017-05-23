package com.mebitech.web.controller.user;

import com.mebitech.core.api.rest.IResponseFactory;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IGroupRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.persistence.entities.GroupImpl;
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
 * Created by tayipdemircan on 28.10.2016.
 */
@Controller
@RequestMapping("/resources/groups")
public class GroupController extends AController {

    @Autowired
    private IResponseFactory responseFactory;

    @Autowired
    private IGroupRequestProcessor groupRequestProcessor;

    //ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/add" , method = RequestMethod.PUT)
    @ResponseBody
    public IRestResponse addNewGroup(@RequestBody String json){
        try{
            GroupImpl group = objectMapper.readValue(json,GroupImpl.class);
            group.setCreateDate(new Date());
            return groupRequestProcessor.add(group);
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage());
        }
    }

    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    @ResponseBody
    public IRestResponse updateGroup(@RequestBody String json){
        try{
            GroupImpl group = objectMapper.readValue(json,GroupImpl.class);
            return groupRequestProcessor.update(group);
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage());
        }
    }

    @RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
    @ResponseBody
    public IRestResponse deleteGroup(@RequestBody(required = false) String json, @RequestParam(value = "id",required = false) Long id){
        try{
            Long idValue = 0L;
            if(json != null && !json.equals("")) {
                GroupImpl group = objectMapper.readValue(json, GroupImpl.class);
                idValue = group.getId();
            }else if(id != null && id.longValue() > 0L){
                idValue = id;
            }
            if(idValue > 0L)
                return groupRequestProcessor.delete(idValue);
            else
                return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : Silinecek kayıt bulunamadı");
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.createResponse(RestResponseStatus.ERROR,"Hata : " + e.getMessage());
        }
    }

    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse getAllGroups(HttpServletRequest request){
        FilterAndPagerImpl filterAndPager = getFilters(request);
        return groupRequestProcessor.findAll(); //findByFilters(filterAndPager);
    }

    @RequestMapping(value = "/getById" , method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse findById(@RequestParam(value = "id") Long id) {
        return groupRequestProcessor.findById(id);
    }

    @RequestMapping(value = "/getProperties" , method = RequestMethod.GET)
    @ResponseBody
    public IRestResponse getProperties(){
        return groupRequestProcessor.getProperties();
    }


//    private FilterAndPagerImpl getFilters(String filter, Integer page, Integer start, Integer limit){
//        FilterAndPagerImpl filterAndPager = new FilterAndPagerImpl();
//        if(start != null)
//            filterAndPager.setStart(start);
//        if(limit != null)
//            filterAndPager.setLimit(limit);
//        if(page != null)
//            filterAndPager.setPage(page);
//        try {
//            if(filter != null) {
//                List<FilterImpl> myObjects = objectMapper.readValue(filter, new TypeReference<List<FilterImpl>>() {});
//                filterAndPager.setFilters(myObjects);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return filterAndPager;
//    }
}
