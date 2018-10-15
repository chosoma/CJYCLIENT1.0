package com.thingtek.view.panel.set.setComponent;

import com.thingtek.config.AgreementConfig;
import com.thingtek.database.domain.UserBean;
import com.thingtek.database.service.UserService;
import com.thingtek.factory.Factorys;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;

@Component
public class UserPanel extends BaseSetPanel {

    @Resource
    private Factorys factorys;

    @Resource
    private AgreementConfig agreementConfig;

    @Resource
    private UserService userService;

    @Override
    protected void initTitle() {
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("用户设置"));
    }

    @Override
    protected void initCenter() {

        java.util.List<UserBean> users = userService.getAllUser();
        for (UserBean user : users) {
            centerPanel.add(new UserUnit(user));
        }

    }

    @Override
    protected void initTool() {

    }
}
