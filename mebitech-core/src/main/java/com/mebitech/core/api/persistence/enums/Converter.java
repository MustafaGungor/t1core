package com.mebitech.core.api.persistence.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tayipdemircan on 14.11.2016.
 */
public class Converter {

    public static Date stringToDate(String format,String dateStr){

        SimpleDateFormat formatter=new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(dateStr);
            return date;
        } catch (ParseException e) {
            //e.printStackTrace();
            return null;
        }
        //return null;
    }

    public static Object tryParseDate(String value){
        Date d = stringToDate("yyyy-MM-dd'T'HH:mm:ss",value);
        if(d == null){
            return value;
        } else{
            return d;
        }
    }

    public static CriteriaOperator convertCriteriaOperator(String name){
        if(name.equals("="))
            return CriteriaOperator.EQ;
        else if(name.equals("!="))
            return  CriteriaOperator.NE;
        else if(name.equals(">"))
            return  CriteriaOperator.GT;
        else if(name.equals("<"))
            return  CriteriaOperator.LT;
        else if(name.equals(">="))
            return  CriteriaOperator.GE;
        else if(name.equals("<="))
            return  CriteriaOperator.LE;
        else if(name.equals("BETWEEN"))
            return  CriteriaOperator.BT;
        else if(name.equals("IS NOT NULL"))
            return  CriteriaOperator.NOT_NULL;
        else if(name.equals("IS NULL"))
            return  CriteriaOperator.NULL;
        else if(name.equals("IN"))
            return  CriteriaOperator.IN;
        else if(name.equals("NOT IN"))
            return  CriteriaOperator.NOT_IN;
        else if(name.equals("LIKE"))
            return  CriteriaOperator.LIKE;
        return CriteriaOperator.EQ;
    }

    /**case EQ:
     return "=";
     case NE:
     return "!=";
     case GT:
     return ">";
     case GE:
     return ">=";
     case LT:
     return "<";
     case LE:
     return "<=";
     case BT:
     return "BETWEEN";
     case NOT_NULL:
     return "IS NOT NULL";
     case NULL:
     return "IS NULL";
     case IN:
     return "IN";
     case NOT_IN:
     return "NOT IN";
     case LIKE:
     return "LIKE";
     default:
     return null;*/
}
