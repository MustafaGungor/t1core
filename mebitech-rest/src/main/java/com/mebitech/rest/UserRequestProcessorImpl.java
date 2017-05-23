package com.mebitech.rest;

import com.mebitech.core.api.configuration.IConfigurationService;
import com.mebitech.core.api.persistence.dao.*;
import com.mebitech.core.api.persistence.entities.IUser;
import com.mebitech.core.api.persistence.entities.IUserLevel;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.processors.IUserRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.dto.UserDto;
import com.mebitech.rest.util.BaseRequestProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tayipdemircan on 24.10.2016.
 */
public class UserRequestProcessorImpl extends BaseRequestProcessor implements IUserRequestProcessor {
    private IUserDao userDao;
    private IGroupDao groupDao;
    private IPermissionDao permissionDao;
    private IConfigurationService configService;
    private IUserLevelDao userLevelDao;
    private ILevelDao levelDao;

    @Override
    public IRestResponse add(IUser json) {
        IUser user = null;
        try {
            user = (IUser) userDao.save(json);
            return findById(user.getId());
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse update(Object user) {
        IUser userc = null;

        try {
            userc = (IUser) userDao.update(user);
            return findById(userc.getId());
        } catch (Exception ex) {
            Error(ex.getMessage());
        }

        return getResponse();
    }

    @Override
    public IRestResponse delete(IUser user) {
        return deleteById(user.getId());
    }

    @Override
    public IRestResponse deleteById(Long id) {
        try {
            userDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse findAll() {
        try {
            List<? extends IUser> userList = (List<? extends IUser>) userDao.findAll(0);
            createResponse(convertToDto(userList));
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse findByUserNameAndPassword(String userName, String password, Long leveId) {
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("userName", userName);
        propertyMap.put("password", password);
        List<? extends IUser> users = (List<? extends IUser>) userDao.findByProperties(propertyMap, null, 0);
        if (users != null && users.size() == 1) {
            propertyMap = new HashMap<String, Object>();
            propertyMap.put("user", users.get(0));
            propertyMap.put("deleted", false);
            propertyMap.put("level", levelDao.find(leveId));
            List<? extends IUserLevel> userLevelList = (List<? extends IUserLevel>) userLevelDao.findByProperties(propertyMap, null, null);
            if (userLevelList != null && userLevelList.size() == 1) {
                createResponse(userLevelList.get(0).getId());
            } else {
                Error("Yetkisiz Erisim.");
            }
        } else {
            Error("Kullanıcı Adı veya Şifre Hatalı");
        }
        return getResponse();
    }

    @Override
    public IRestResponse findById(Long userId) {
        IUser user = (IUser) userDao.find(userId);
        UserDto dto = new UserDto(user);
        createResponse(dto);
        return getResponse();
    }

    @Override
    public IRestResponse findByFilters(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = userDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    @Override
    public IRestResponse getProperties() {
        createResponse(userDao.getProperties());
        return getResponse();
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void setPermissionDao(IPermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    public void setConfigService(IConfigurationService configService) {
        this.configService = configService;
    }

    public void setUserlevelDao(IUserLevelDao userLevelDao) {
        this.userLevelDao = userLevelDao;
    }

    public void setLevelDao(ILevelDao levelDao) {
        this.levelDao = levelDao;
    }

    private List<UserDto> convertToDto(List<? extends IUser> userList) {
        List<UserDto> retList = new ArrayList<UserDto>();
        for (int i = 0; i < userList.size(); i++) {
            UserDto dto = new UserDto(userList.get(i));
            retList.add(dto);
        }
        return retList;
    }
}
