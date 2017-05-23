package com.mebitech.rest;

import com.mebitech.core.api.persistence.dao.IGroupDao;
import com.mebitech.core.api.persistence.dao.ILevelDao;
import com.mebitech.core.api.persistence.dao.IUserDao;
import com.mebitech.core.api.persistence.dao.IUserLevelDao;
import com.mebitech.core.api.persistence.entities.IGroup;
import com.mebitech.core.api.persistence.entities.ILevel;
import com.mebitech.core.api.persistence.entities.IUser;
import com.mebitech.core.api.persistence.entities.IUserLevel;
import com.mebitech.core.api.persistence.filter.IFilterAndPager;
import com.mebitech.core.api.rest.enums.RestResponseStatus;
import com.mebitech.core.api.rest.processors.IUserLevelRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLevelRequestProcessorImpl extends BaseRequestProcessor implements IUserLevelRequestProcessor {
    private static Logger logger = LoggerFactory.getLogger(UserLevelRequestProcessorImpl.class);
    private IUserLevelDao userlevelDao;
    private IUserDao userDao;
    private ILevelDao levelDao;
    private IGroupDao groupDao;

    @Override
    public IRestResponse list(String hostname, String dn, String uid) {
        Map<String, Object> propertiesMap = new HashMap<String, Object>();
        if (hostname != null && !hostname.isEmpty()) {
            propertiesMap.put("hostname", hostname);
        }
        if (dn != null && !dn.isEmpty()) {
            propertiesMap.put("dn", dn);
        }
        if (uid != null && !uid.isEmpty()) {
            propertiesMap.put("jid", uid);
        }
        List<? extends IUserLevel> userleveller = (List<? extends IUserLevel>) userlevelDao.findByProperties(propertiesMap, null, null);
        logger.debug("Found UserLevel: {}", userleveller);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("UserLevel", userleveller);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return responseFactory.createResponse(RestResponseStatus.OK, "Records listed.", resultMap);
    }

    @Override
    public IRestResponse add(IUserLevel userLevel, HashMap<String, Object> idMap) {
        Integer userId = (Integer) idMap.get("id");
        IUser user = (IUser) userDao.find(userId.longValue());

        List<Integer> levelIds = (List<Integer>) idMap.get("levels");
        List<IUserLevel> recordList = new ArrayList<IUserLevel>();
        try {
            for (Integer levelId : levelIds) {
                ILevel level = (ILevel) levelDao.find(levelId.longValue());
                userLevel.setUser(user);
                userLevel.setLevel(level);
                if (!hasSameRecord(user, level))
                    recordList.add((IUserLevel) userlevelDao.save(userLevel));
            }
            createResponse(recordList);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    private boolean hasSameRecord(IUser user, ILevel level) {
        Map<String, Object> propertyMap = new HashMap<String, Object>();
        propertyMap.put("user", user);
        propertyMap.put("level", level);
        propertyMap.put("deleted", false);

        List<? extends IUserLevel> list = (List<? extends IUserLevel>) userlevelDao.findByProperties(propertyMap, null, null);

        return list.size() > 0;
    }

    @Override
    public IRestResponse remove(Long id) {

        try {
            userlevelDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }

        return getResponse();
    }

    @Override
    public IRestResponse findById(Long userId, Long levelId) {
        if (userId != null || levelId != null) {
            Map<String, Object> propertyMap = new HashMap<String, Object>();
            propertyMap.put("deleted", false);
            if (userId != null) {
                propertyMap.put("user", userDao.find(userId));
            }
            if (levelId != null) {
                propertyMap.put("level", levelDao.find(levelId));
            }

            List<? extends IUserLevel> userLevels = (List<? extends IUserLevel>) userlevelDao.findByProperties(propertyMap, null, null);
            createResponse(userLevels);
        } else
            Error("Kullanıcı veya Seviye id'si boş olamaz...");

        return getResponse();
    }

    @Override
    public IRestResponse addGroup(HashMap<String, Object> idMap) {
        Integer userLevelId = (Integer) idMap.get("id");
        IUserLevel userLevel = (IUserLevel) userlevelDao.find(userLevelId.longValue());

        List<Integer> groups = (List<Integer>) idMap.get("groups");
        List groupList = userLevel.getGroups();
        if (groupList == null)
            groupList = new ArrayList();
        for (Integer groupId : groups) {
            IGroup group = (IGroup) groupDao.find(groupId.longValue());
            groupList.add(group);
        }

        userLevel.setGroups((List<? extends IGroup>) groupList);
        try {
            createResponse(userlevelDao.update(userLevel));
        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse removeGroup(HashMap<String, Object> idMap) {
        Integer userLevelId = (Integer) idMap.get("id");
        IUserLevel userLevel = (IUserLevel) userlevelDao.find(userLevelId.longValue());
        List obj = userLevel.getGroups();

        List<Integer> groups = (List<Integer>) idMap.get("groups");
        for (int i = 0; i < groups.size(); i++) {
            for (int j = 0; j < obj.size(); j++) {
                if (((IGroup) obj.get(j)).getId() == groups.get(i).longValue()) {
                    obj.remove(j);
                }
            }
        }
        userLevel.setGroups((List<? extends IGroup>) obj);
        try {
            createResponse(userlevelDao.update(userLevel));
        } catch (Exception ex) {
            Error(ex.getMessage());
        }

        return getResponse();
    }

    @Override
    public IRestResponse getGroups(Long id) {
        IUserLevel userLevel = (IUserLevel) userlevelDao.find(id);
        createResponse(userLevel.getGroups());
        return getResponse();
    }

    @Override
    public IRestResponse getGroups(IFilterAndPager filterAndPager) {
        return null;
    }

    @Override
    public IRestResponse getGroups(IFilterAndPager filterAndPager, Class innerClass) {
        Map<String, Object> retMap = userlevelDao.findByInnerFilters(filterAndPager, innerClass, "groups");
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public IRestResponse getLevels(IFilterAndPager filterAndPager) {
        Map<String, Object> retMap = userlevelDao.findByFilters(filterAndPager);
        createResponse(retMap.get("data"), (Long) retMap.get("count"));
        return getResponse();
    }

    public void setUserlevelDao(IUserLevelDao userlevelDao) {
        this.userlevelDao = userlevelDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setLevelDao(ILevelDao levelDao) {
        this.levelDao = levelDao;
    }

    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

}