package com.mebitech.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mebitech.core.api.persistence.entities.IUser;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tayipdemircan on 24.10.2016.
 */
@Entity
@Table(name = "USER")
public class UserImpl implements IUser {

    public UserImpl() {

    }

    public UserImpl(IUser user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.getActive();
        this.createDate = user.getCreateDate();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
//        this.kullaniciTipi = user.getKullaniciTipi().getId();
//        this.kullaniciTipi = user.getKullaniciTipi();
//        this.role = (RoleImpl) user.getRole();
//        this.groups = (List<GroupImpl>)user.getGroups();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

//    @Column(name = "KULLANICI_TIPI")
//    private String kullaniciTipi;

//    @OneToOne
//    @JoinColumn(name = "ROL_ID")
//    private RoleImpl role;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<GroupImpl> groups;

    @Column(name = "IS_DELETED")
    private boolean deleted;

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public IRole getRole() {
//        return role;
//    }

//    public void setRole(RoleImpl role) {
//        this.role = role;
//    }


//    public EnumUtil.KullaniciTipi getKullaniciTipi() {
//        return EnumUtil.KullaniciTipi.parse(kullaniciTipi);
//    }
//
//    public void setKullaniciTipi(String kullaniciTipi) {
//        this.kullaniciTipi = kullaniciTipi;
//    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createdDate) {
        this.createDate = createdDate;
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Override
//    public List<GroupImpl> getGroups() {
//        return groups;
//    }

    //    public void setGroups(List<GroupImpl> groups) {
//        this.groups = groups;
//    }
    @JsonIgnore
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

//    @Override
//    public void setGroups(List<? extends IGroup> groups) {
//        this.groups = (List<GroupImpl>) groups;
//    }

}
