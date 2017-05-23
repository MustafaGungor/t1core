package com.mebitech.core.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sedaozcan on 16.12.2016.
 */
public class EnumUtil {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum KullaniciTipi  {
        ADMIN("1","Admin"),
        KURUM_ADMIN("2","Kurum Admin"),
        USER("3","User");

        private final String id;
        private final String name;

        private KullaniciTipi(String id, String name){
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static KullaniciTipi parse(String id) {
            for (KullaniciTipi kullaniciTipi : KullaniciTipi.values()) {
                if (kullaniciTipi.getId().equals(id)) {
                    return kullaniciTipi;
                }
            }
            return null;
        }
    }

//    public static Map<String, Object> getEnums(){
//        Map<String, Object> enumMap = new HashMap<String, Object>();
//        for(Class cls : EnumUtil.class.getClasses()){
//            String className = cls.getName();
//            enumMap.put(className.substring(className.indexOf("$")+1),cls.getEnumConstants());
//        }
//        return enumMap;
//    }
}
