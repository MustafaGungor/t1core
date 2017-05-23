package com.mebitech.persistence.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import org.codehaus.jackson.map.ObjectMapper;
import com.mebitech.core.api.persistence.entities.IMenu;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;

@Entity
@Table(name = "MENU")
public class MenuImpl implements IMenu {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;
    @Column(name = "IS_DELETED")
    private Boolean deleted;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "PATH", nullable = false)
    private String path;

    @Column(name = "INDEX")
    private Integer index;

    @OneToOne
    @JoinColumn(name = "PARENT_ID")
    private MenuImpl parent;

    @Column(name = "MODULE")
    private String module;

    @Column(name = "ICON")
    private String icon;

    @Transient
    private List<IMenu> items;

    @Transient
    private CrudType[] crudTypes;
    @Transient
    private String[] entityAttributes;

    public MenuImpl(Long id, Date createDate, Boolean deleted, String text, String path, Integer index, MenuImpl parent, String module, String icon) {
        this.id = id;
        this.createDate = createDate;
        this.deleted = deleted;
        this.text = text;
        this.path = path;
        this.index = index;
        this.parent = parent;
        this.module = module;
        this.icon = icon;
    }

    @Override
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public IMenu getParent() {
        return parent;
    }

    public void setParent(MenuImpl parent) {
        this.parent = parent;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<IMenu> getItems() {
        return items;
    }

    public void setItems(List<IMenu> items) {
        this.items = items;
    }

    @Override
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "MenuImpl [id=" + id + "]" +
                ", createDate=" + createDate + "]";
    }

    @Override
    public Map<String, String> getProperties() {
//TODO: Return Table Column Name;
        return null;
    }

    @Override
    public void applyEntitySecurity(IEntitySecurity entitySecurity) {
        crudTypes = entitySecurity.getCrudType();
        entityAttributes = entitySecurity.getEntityAttributes();
    }
}