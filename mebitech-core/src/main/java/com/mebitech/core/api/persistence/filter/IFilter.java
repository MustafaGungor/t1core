package com.mebitech.core.api.persistence.filter;

/**
 * Created by tayipdemircan on 11.11.2016.
 */
public interface IFilter {

    String getProperty();

    Object getValue();

    String getOperator();

    void setProperty(String property);

    void setValue(Object value);

    void setOperator(String operator);

    void setFilterType(String filterType);

    void setType(String type);

    void setIsEnum(boolean anEnum);

    String getFilterType();

    String getType();

    boolean getIsEnum();

}
