package com.mebitech.core.api.persistence.order;


import com.mebitech.core.api.persistence.enums.OrderType;

/**
 * Created by tayipdemircan on 10.02.2017.
 */
public interface IPropertyOrder {

    void setPropertyName(String propertyName);

    String getPropertyName();

    void setOrderType(OrderType orderType);

    OrderType getOrderType();

}
