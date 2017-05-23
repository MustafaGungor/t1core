package com.mebitech.core.api.persistence;

import com.mebitech.core.api.persistence.filter.IFilterAndPager;

import java.util.List;
import java.util.Map;

/**
 * Created by tayipdemircan on 14.11.2016.
 */
public interface IABaseDao {
    Object save(Object o) throws Exception;

    Object update(Object t) throws Exception;

    Object mapUpdate(Map changedMap) throws Exception;

    void delete(Long id) throws Exception;

    void truncate(String tableName) throws Exception;


    //void delete(Long id,Long levelId) throws Exception;

    Object find(Long id);

    //Object find(Long id,Long levelId);

    List<? extends Object> findAll(Integer maxResults);

    List<? extends Object> findByProperty(String propertyName, Object value, Integer maxResults);

    List<? extends Object> findByProperties(Map<String, Object> propertiesMap, List<PropertyOrder> orders, Integer maxResults);

    List getProperties();

    /**
     *
     * @param filterAndPager
     * @return
     */
    Map<String,Object> findByFilters(IFilterAndPager filterAndPager);

    Map<String,Object> findByInnerFilters(IFilterAndPager filterAndPager, Class innerClass);

    Map<String, Object> findByInnerFilters(IFilterAndPager filterAndPager, Class innerClass,String entityFieldName);

    void setLevelId(Long id);

    Long getLevelId();

    void setUserId(Long id);

    Long getUserId();

    void setUserLevelId(Long id);

    Long getUserLevelId();

    void setSelectionMap(Map<String, String[]> selectionMap);

}
