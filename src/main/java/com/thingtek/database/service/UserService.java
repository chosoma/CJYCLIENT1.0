package com.thingtek.database.service;

import com.thingtek.database.dao.UserDAO;
import com.thingtek.database.domain.UserBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService {
    @Resource
    private UserDAO userDAO;

    public List<UserBean> getAllUser() {
        try {
            return userDAO.find();
        } catch (Exception e) {
            log(e);
            return new ArrayList<>();
        }
    }

    public UserBean checkUser(UserBean userBean) {
        try {
            return userDAO.check(userBean);
        } catch (Exception e) {
            log(e);
            return null;
        }
    }


}
