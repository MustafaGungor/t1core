package com.mebitech.config.menu;

import com.mebitech.config.menu.model.Form;
import com.mebitech.config.menu.model.Module;
import com.mebitech.config.menu.model.Modules;
import com.mebitech.config.menu.model.Permission;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by tayipdemircan on 4.11.2016.
 */
public class MenuUtil {

    public static Map<String,Module> getMenuList(){
        Map<String,Module> menuList ;
        //if(menuList == null) {
            menuList = new HashMap<String, Module>();
            JAXBContext jaxbContext = null;
            try {
                jaxbContext = JAXBContext.newInstance(Modules.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                File file = new File("etc/menu.xml");
               //InputStream stream = MenuUtil.class.getClass().getResourceAsStream("etc/menu.xml");

                Modules list = (Modules) jaxbUnmarshaller.unmarshal(file);
                for(Module m : list.getModuleList()){
                    for (Form form : m.getFormList()){
                        for (Permission permission: form.getPermissionList())
                            form.getPermissions().put(permission.getId(),permission);
                        m.getForms().put(form.getId(),form);
                    }
                    menuList.put(m.getId(),m);
                }
            } catch (JAXBException e ) {
                e.printStackTrace();
            }

        //}

        return menuList;
    }

//    public static Map<String,Module> getMenuListAll(){
//        if(menuList == null)
//            return getMenuList();
//        for(String key : menuList.keySet()){
//            Module module = menuList.get(key);
//            module.getFormList().clear();
//            module.getFormList().addAll(module.getForms().values());
//            for(Form form : module.getFormList()){
//                form.getPermissionList().clear();
//                form.getPermissionList().addAll(form.getPermissions().values());
//                for(Permission permission:form.getPermissionList()){
//                    permission.setPermissionId(null);
//                    permission.setGroup("");
//                }
//            }
//        }
//
//        return menuList;
//    }
}
