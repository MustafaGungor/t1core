package com.mebitech.core.api.persistence.filter;

import java.util.List;
import java.util.Map;

/**
 * Created by tayipdemircan on 11.11.2016.
 */
public interface IFilterAndPager {

    List<? extends IFilter> getFilters();

    Integer getPage();

    Integer getStart();

    Integer getLimit();

    Long getLevelId();

    void setFilters(List<? extends IFilter> filters);

    void setPage(Integer page);

    void setStart(Integer start);

    void setLimit(Integer limit);

    void setLevelId(Long levelId);

    Map<String, String[]> getSelectionMap();

    void setSelectionMap(Map<String, String[]> selectionMap);

    String getFullQueryString();

    void setFullQueryString(String fullQueryString);
}
