package com.mebitech.config.menu.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by tayipdemircan on 9.11.2016.
 */
@XmlRootElement(name = "moduleList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Modules {

    @XmlElement(name = "module")
    private List<Module> moduleList;

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }
}
