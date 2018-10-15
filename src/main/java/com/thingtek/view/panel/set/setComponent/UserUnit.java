package com.thingtek.view.panel.set.setComponent;

import com.thingtek.database.domain.UserBean;

import javax.swing.*;
import java.awt.*;

public class UserUnit extends JLabel {

    private UserBean userBean;

    public UserUnit(UserBean userBean) {
        this.userBean = userBean;
        setText(userBean.getUsername());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    @Override
    public int getVerticalTextPosition() {
        return SwingConstants.BOTTOM;
    }

    @Override
    public int getVerticalAlignment() {
        return SwingConstants.CENTER;
    }

    @Override
    public int getHorizontalTextPosition() {
        return SwingConstants.CENTER;
    }

    @Override
    public int getHorizontalAlignment() {
        return SwingConstants.CENTER;
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

}
