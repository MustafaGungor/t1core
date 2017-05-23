package com.mebitech.core.api.rest.processors;

import com.mebitech.core.api.persistence.entities.ILevel;
import com.mebitech.core.api.persistence.entities.IUser;
import com.mebitech.core.api.persistence.entities.IUserLevel;

/**
 * Created by tayipdemircan on 18.12.2016.
 */
public interface IBaseRequestProcessor {

    IUserLevel getUserLevel();

    void setUserLevel(IUserLevel userLevel);

    IUser getUser();

    void setUser(IUser user);

    ILevel getLevel();

    void setLevel(ILevel level);

}
