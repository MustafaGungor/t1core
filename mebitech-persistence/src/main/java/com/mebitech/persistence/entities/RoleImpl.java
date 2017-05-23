package com.mebitech.persistence.entities;

import com.mebitech.core.api.persistence.entities.IRole;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tayipdemircan on 26.10.2016.
 */
@Entity
@Table(name = "ROLE")
public class RoleImpl implements IRole {

    public RoleImpl(){

    }
    public RoleImpl(IRole role){
        this.id = role.getId();
        this.roleCode = role.getRoleCode();
        this.roleName = role.getRoleName();
        this.createDate = role.getCreateDate();
        this.deleted = role.getDeleted();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ROLE_CODE", nullable = false)
    private String roleCode;


    @Column(name = "ROL_NAME", nullable = false)
    private String roleName;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @Column(name = "IS_DELETED")
    private boolean deleted;

    @Override
    public String getRoleCode() {
        return this.roleCode;
    }

    @Override
    public String getRoleName() {
        return this.roleName;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
