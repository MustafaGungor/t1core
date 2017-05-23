package com.mebitech.persistence.filter;

import com.mebitech.core.api.persistence.filter.IFilter;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;

import java.util.List;
import java.util.Map;

/**
 * Created by tayipdemircan on 11.11.2016.
 */
public class FilterAndPagerImpl implements IFilterAndPager {

    private List<FilterImpl> filters;

    private Integer page;

    private Integer start;

    private Integer limit;

    private Long levelId;

    private Map<String,String[]> selectionMap;

    private String fullQueryString = "";

    @Override
    public void setFilters(List<? extends IFilter> filters) {
        this.filters = (List<FilterImpl>)filters;
    }

    @Override
    public List<FilterImpl> getFilters() {
        return filters;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getStart() {
        return start;
    }

    @Override
    public void setStart(Integer start) {
        this.start = start;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public Long getLevelId() {
        return levelId;
    }

    @Override
    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Map<String, String[]> getSelectionMap() {
        return selectionMap;
    }

    public void setSelectionMap(Map<String, String[]> selectionMap) {
        this.selectionMap = selectionMap;
    }

    public String getFullQueryString() {
        return fullQueryString;
    }

    public void setFullQueryString(String fullQueryString) {
        this.fullQueryString = fullQueryString;
    }
}
