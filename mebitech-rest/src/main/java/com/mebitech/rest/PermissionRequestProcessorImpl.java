package com.mebitech.rest;

import com.mebitech.core.api.EnumUtil;
import com.mebitech.core.api.caching.ICacheService;
import com.mebitech.core.api.configuration.IConfigurationService;
import com.mebitech.core.api.persistence.dao.IGroupDao;
import com.mebitech.core.api.persistence.dao.IPermissionDao;
import com.mebitech.core.api.persistence.dao.IUserDao;
import com.mebitech.core.api.persistence.dao.IUserLevelDao;
import com.mebitech.core.api.persistence.entities.IGroup;
import com.mebitech.core.api.persistence.entities.IPermission;
import com.mebitech.core.api.persistence.entities.IUserLevel;
import com.mebitech.core.api.rest.processors.IPermissionRequestProcessor;
import com.mebitech.core.api.rest.responses.IRestResponse;
import com.mebitech.rest.util.BaseRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PermissionRequestProcessorImpl extends BaseRequestProcessor implements IPermissionRequestProcessor {
    private static Logger logger = LoggerFactory.getLogger(PermissionRequestProcessorImpl.class);
    private IPermissionDao permissionDao;
    private IUserDao userDao;
    private IConfigurationService configService;
    private IUserLevelDao userLevelDao;
    private IGroupDao groupDao;
    private ICacheService cacheService;

    @Override
    public IRestResponse list(String hostname, String dn, String uid) {
        Map map = configService.getMenuList();

        createResponse(map.values());

        return getResponse();
    }

    @Override
    public IRestResponse add(IPermission permission) {
        try {
            cacheService.put("permissions:" + permission.getUserLevel().getId().toString(), null);
            createResponse(permissionDao.save(permission));

        } catch (Exception ex) {
            Error(ex.getMessage());
        }
        return getResponse();
    }

    @Override
    public IRestResponse delete(Long id) {
        try {
            IPermission permission = (IPermission) permissionDao.find(id);
            cacheService.put("permissions:" + permission.getUserLevel().getId().toString(), null);
            permissionDao.delete(id);
            createResponse(id);
        } catch (Exception ex) {
            Error(ex.getMessage());
        }

        return getResponse();
    }

    @Override
    public IRestResponse getAuthorizedMenus(Long id, Long groupId) {
        if (id != null && cacheService.get("permissions:" + id.toString()) != null) {
            createResponse(cacheService.get("permissions:" + id.toString()));
        } else {
            try {
                Map<String, Object> retMap = new HashMap<String, Object>();
                Map<String, Object> propertyMap = new HashMap<String, Object>();
                propertyMap.put("deleted", false);
                List permissions = null;
                IUserLevel userLevel = null;
                if (id != null) {
                    userLevel = (IUserLevel) userLevelDao.find(id);
//                    if (userLevel.getUser().getKullaniciTipi() != null && userLevel.getUser().getKullaniciTipi() == EnumUtil.KullaniciTipi.ADMIN) {
                    if(true){
                        cacheService.put("userLevel:" + userLevel.getId().toString(), userLevel);
                        retMap.put("userLevel", userLevel);
                        retMap.put("user", userLevel.getUser());
                        retMap.put("permissionList", new ArrayList(configService.getMenuList().values()));
                        createResponse(retMap);
                        return getResponse();
                    }
                    propertyMap.put("userLevel", userLevel);
                    propertyMap.put("deleted", false);
                    List permissionList = permissionDao.findByProperties(propertyMap, null, null);
                    permissions = new ArrayList(permissionList);
                    propertyMap.remove("userLevel");
                    List<? extends IGroup> groups = userLevel.getGroups();
                    List newGroups = new ArrayList();
                    for (IGroup groupItem : groups) {
                        newGroups.add(groupItem);
                    }
                    propertyMap.put("group", newGroups);
                    retMap.put("userLevel", userLevel);
                    retMap.put("user", userLevel.getUser());
                }

                if (groupId != null) {
                    IGroup group = (IGroup) groupDao.find(groupId);
                    propertyMap.put("group", group);
                }
                List groupPermissionList = permissionDao.findByProperties(propertyMap, null, null);
                if (permissions != null)
                    permissions.addAll(groupPermissionList);
                else
                    permissions = new ArrayList(groupPermissionList);

                List permissionList = new ArrayList(getPermissionMap(permissions).values());
                retMap.put("permissionList", permissionList);
                createResponse(retMap);
                if (userLevel != null) {
                    cacheService.put("permissions:" + userLevel.getId().toString(), retMap);
                    cacheService.put("userLevel:" + userLevel.getId().toString(), userLevel);
                }
            } catch (Exception ex) {
                Error(ex.getMessage());
            }
        }
        return getResponse();
    }

    private HashMap<String, Object> getPermissionMap(List permissions) {
        HashMap<String, Object> retMap = new HashMap<String, Object>();//return edeceğimiz değer
        for (IPermission obj : (List<? extends IPermission>) permissions) {//veritabanından dönen kayıt kadar dönüyoruz(kullanıcının izinleri)
            try {
                Object module = null;//modül objesi
                Object form;//form objesi
                Object temp = null;//buffer
                //veritabanında kayıtlı olan permission id'nin objesini configservice'den aldık
                Object permissionObj = configService.getPermissionById(obj.getModuleId(), obj.getFormId(), obj.getPermissionId());

                if (permissionObj == null) {
                    continue;
                }

                permissionObj.getClass().getDeclaredMethod("setPermissionId", Long.class).invoke(permissionObj, obj.getId());
                if (obj.getGroup() != null) {
                    permissionObj.getClass().getDeclaredMethod("setGroup", String.class).invoke(permissionObj, obj.getGroup().getGroupName());
                }

                //retmap'ta daha önce aynı modul eklenmişse onu kullanmak için modul objesini dolduruyoruz
                if (retMap.get(obj.getModuleId()) != null) {
                    module = retMap.get(obj.getModuleId());
                    //modülün içindeki form listesini alıyoruz
                    Set tmpList = (Set) module.getClass().getMethod("getFormList").invoke(module);
                    //formlist içinde dönerek ekleyeceğimiz permission'un ait olduğu formu buluyoruz
                    for (Object object : tmpList) {
                        if (object.getClass().getMethod("getId").invoke(object).toString().equals(obj.getFormId())) {
                            temp = object;//temp objesine ekleyeceğimiz permissionun formunu atıyoruz
                            break;//kayıt bulundu çıkabiliriz..
                        }
                    }
                }

                if (temp == null)//temp objesi null ise configService'den formu çağırıyoruz
                    form = configService.getFormById(obj.getModuleId(), obj.getFormId());
                else
                    form = temp;//bu formu daha önceden eklemişiz temp'ten alıyoruz

                if (form == null)
                    continue;

                //forma permission atıyoruz
                ((Set) form.getClass().getMethod("getPermissionList").invoke(form)).add(permissionObj);
                //((Map) form.getClass().getMethod("getPermissions").invoke(form)).put(obj.getPermissionId(), permissionObj);

                //module bilgisi null ise configService'den modülü çekiyoruz
                if (retMap.get(obj.getModuleId()) == null) {
                    module = configService.getModuleById(obj.getModuleId());
                }

                if (module == null)
                    continue;

                //module objesine form atıyoruz (Set olduğu için aynı objeyi eklesek de farketmeyecek)
                ((Set) module.getClass().getMethod("getFormList").invoke(module)).add(form);
                //((Map) module.getClass().getMethod("getFormList").invoke(module)).put(obj.getFormId(), form);

                //oluşturduğumuz modülü return edeceğimiz map'e atıyoruz
                retMap.put(obj.getModuleId(), module);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }

        return retMap;
    }

    public void setPermissionDao(IPermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setConfigService(IConfigurationService configService) {
        this.configService = configService;
    }

    public void setUserlevelDao(IUserLevelDao userLevelDao) {
        this.userLevelDao = userLevelDao;
    }

    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void setCacheService(ICacheService cacheService) {
        this.cacheService = cacheService;
    }
}