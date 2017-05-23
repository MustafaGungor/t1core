package com.mebitech.persistence.dao.util;

/**
 * Created by tayipdemircan on 3.11.2016.
 */
public class PropertyObj extends Object{

    public PropertyObj(){}

    public PropertyObj(String field,String type){
        setType(type.replace("java.lang.","").replace("java.util.","").toLowerCase());
        setField(field);
    }

    private String field;
    private String type;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
