package com.mebitech.config.menu.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by tayipdemircan on 9.11.2016.
 */
@XmlRootElement(name = "module")
@XmlAccessorType(XmlAccessType.FIELD)
public class Module {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String name;


    @XmlElement(name = "form")
    private Set<Form> formList;

    @JsonIgnore
    private Map<String,Form> forms = new HashMap<String, Form>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Form> getFormList() {
        return formList;
    }

    public void setFormList(Set<Form> formList) {
        this.formList = formList;
    }

    public Map<String, Form> getForms() {
        return forms;
    }

    public void setForms(Map<String, Form> forms) {
        this.forms = forms;
    }
}
