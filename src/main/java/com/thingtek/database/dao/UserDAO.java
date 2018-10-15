package com.thingtek.database.dao;

import com.thingtek.database.domain.UserBean;

import java.util.List;

public interface UserDAO {

    void save(UserBean user) throws Exception;

    List<UserBean> find() throws Exception;

    UserBean check(UserBean user) throws Exception;

}
