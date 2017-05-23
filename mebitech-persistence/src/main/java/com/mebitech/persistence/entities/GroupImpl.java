package com.mebitech.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mebitech.core.api.persistence.entities.IGroup;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tayipdemircan on 28.10.2016.
 */
@Entity
@Table(name = "GROUPS")
public class GroupImpl implements IGroup {

    public GroupImpl(IGroup group) {
        setGroupCode(group.getGroupCode());
        setGroupName(group.getGroupName());
        setId(group.getId());
        setCreateDate(group.getCreateDate());
    }

    public GroupImpl() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "GROUP_CODE")
    private String groupCode;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "IS_DELETED")
    private boolean deleted;

    @Override
    public String getGroupCode() {
        return this.groupCode;
    }

    @Override
    public String getGroupName() {
        return this.groupName;
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

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonIgnore
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
