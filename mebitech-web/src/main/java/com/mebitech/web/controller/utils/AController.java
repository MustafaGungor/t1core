package com.mebitech.web.controller.utils;

import com.mebitech.persistence.filter.FilterAndPagerImpl;
import com.mebitech.persistence.filter.FilterImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by tayipdemircan on 16.11.2016.
 */
public abstract class AController {
    protected ObjectMapper objectMapper = new ObjectMapper();

    protected FilterAndPagerImpl getFilters(HttpServletRequest request){
        String filter = request.getParameter("filter");
        String page = request.getParameter("page") != null ? request.getParameter("page") : "0";
        String start = request.getParameter("start") != null ? request.getParameter("start") : "0";
        String limit = request.getParameter("limit") != null ? request.getParameter("limit") : "0";

        return getFilters(filter, Integer.valueOf(page), Integer.valueOf(start), Integer.valueOf(limit));
    }

    protected FilterAndPagerImpl getFilters(String filter, Integer page, Integer start, Integer limit){
        FilterAndPagerImpl filterAndPager = new FilterAndPagerImpl();
        if(start != null)
            filterAndPager.setStart(start);
        if(limit != null)
            filterAndPager.setLimit(limit);
        if(page != null)
            filterAndPager.setPage(page);
        try {
            if(filter != null) {
                List<FilterImpl> myObjects = objectMapper.readValue(filter, new TypeReference<List<FilterImpl>>() {});
                filterAndPager.setFilters(myObjects);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filterAndPager;
    }
}
