package com.mebitech.persistence.entities;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import com.mebitech.core.api.persistence.entities.IGroup;
import com.mebitech.core.api.persistence.entities.ILevel;
import com.mebitech.core.api.persistence.entities.IUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import com.mebitech.core.api.persistence.entities.IUserLevel;
import com.mebitech.core.api.persistence.entities.security.IEntitySecurity;
import com.mebitech.core.api.persistence.enums.CrudType;

@Entity
@Table(name = "C_UserLevel")
public class UserLevelImpl implements IUserLevel {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;
    @Column(name = "IS_DELETED")
    private Boolean deleted;
    @Transient
    private CrudType[] crudTypes;
    @Transient
    private String[] entityAttributes;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserImpl user;

    @ManyToOne
    @JoinColumn(name = "LEVEL_ID")
    private LevelImpl level;

    @OneToMany(fetch = FetchType.EAGER)
    private List<GroupImpl> groups;

    public UserLevelImpl() {
    }

    public UserLevelImpl(Long id, Date createDate, Boolean deleted, IUser user, ILevel level, List<GroupImpl> groups
    ) {
        super();
        this.id = id;
        this.deleted = deleted;
        this.createDate = createDate;
        this.user = (UserImpl) user;
        this.level = (LevelImpl) level;
        this.groups = groups;
    }

    public UserLevelImpl(IUserLevel userlevel) {
        this.deleted = userlevel.getDeleted();
        this.id = userlevel.getId();
        this.createDate = userlevel.getCreateDate();
        this.user = (UserImpl) userlevel.getUser();
        this.level = (LevelImpl) userlevel.getLevel();
        this.groups = (List<GroupImpl>) userlevel.getGroups();
    }

    @JsonIgnore
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

    public UserImpl getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = (UserImpl) user;
    }

    public LevelImpl getLevel() {
        return level;
    }

    public void setLevel(ILevel level) {
        this.level = (LevelImpl) level;
    }

    public List<GroupImpl> getGroups() {
        return groups;
    }

    public void setGroups(List<? extends IGroup> groups){
        this.groups = (List<GroupImpl>) groups;
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
        return "UserLevelImpl [id=" + id + "]" +
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