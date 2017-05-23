package com.mebitech.persistence.entities;

import java.util.Date;
import java.util.Map;
import javax.persistence.*;

import com.mebitech.core.api.persistence.entities.IUser;
import org.codehaus.jackson.map.ObjectMapper;
import com.mebitech.core.api.persistence.entities.IPermission;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;

@Entity
@Table(name = "PERMISSION")
public class PermissionImpl implements IPermission {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;
    @Column(name = "IS_DELETED")
    private Boolean deleted;
//    @Column(name = "MENU_ID")
//    private String menuId;

    @Column(name = "MODULE_ID")
    private String moduleId;

    @Column(name = "FORM_ID")
    private String formId;

    @Column(name = "PERMISSION_ID")
    private String permissionId;

    @ManyToOne
    @JoinColumn(name = "USERLEVEL_ID")
    private UserLevelImpl userLevel;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private GroupImpl group;

    @Transient
    private CrudType[] crudTypes;
    @Transient
    private String[] entityAttributes;

    public PermissionImpl() {
    }

    public PermissionImpl(Long id, Date createDate, Boolean deleted
            , String menuId, String moduleId, String formId, String permissionId, UserLevelImpl userLevel, GroupImpl group) {
        super();
        this.id = id;
        this.deleted = deleted;
        this.createDate = createDate;
//        this.menuId = menuId;
//        this.user = user;
        this.moduleId = moduleId;
        this.formId = formId;
        this.permissionId = permissionId;
        this.userLevel = userLevel;
        this.group = group;
    }

    public PermissionImpl(IPermission permission) {
        this.deleted = permission.getDeleted();
        this.id = permission.getId();
        this.createDate = permission.getCreateDate();
//        this.menuId = permission.getMenuId();
//        this.user = (UserImpl) permission.getUser();
        this.moduleId = permission.getModuleId();
        this.formId = permission.getFormId();
        this.permissionId = permission.getPermissionId();
        this.userLevel = (UserLevelImpl) permission.getUserLevel();
        this.group = (GroupImpl) permission.getGroup();
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

    public UserLevelImpl getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevelImpl userLevel) {
        this.userLevel = userLevel;
    }

    public GroupImpl getGroup() {
        return group;
    }

    public void setGroup(GroupImpl group) {
        this.group = group;
    }

//    public String getMenuId() {
//        return this.menuId;
//    }

//    public void setMenuId(String menuId) {
//        this.menuId = menuId;
//    }

//    public UserImpl getUser() {
//        return user;
//    }
//
//    public void setUser(IUser user) {
//        this.user = (UserImpl) user;
//    }

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

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "PermissionImpl [id=" + id + "]" +
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