package com.mebitech.persistence.filter;

import com.mebitech.core.api.persistence.enums.Converter;
import com.mebitech.core.api.persistence.filter.IFilter;

import java.util.Date;

/**
 * Created by tayipdemircan on 11.11.2016.
 */
public class FilterImpl implements IFilter {

    private String property;

    private Object value;

    private String operator;

    private String filterType;

    private String type;

    private boolean isEnum;

    @Override
    public String getProperty() {
        return this.property;
    }

    @Override
    public Object getValue() {
        if(getType() != null && getType().equals("date")){
            Date d=Converter.stringToDate("yyyy-MM-dd'T'HH:mm:ss",value.toString());
            return d;
        }
        return this.value;
    }

    @Override
    public String getOperator() {
        return this.operator;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsEnum() {
        return isEnum;
    }

    public void setIsEnum(boolean anEnum) {
        isEnum = anEnum;
    }
}
