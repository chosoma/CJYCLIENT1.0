package com.thingtek.database.domain;


import lombok.Data;

import java.io.Serializable;

public @Data
class UserBean extends BaseBean implements Serializable {

    private String username;
    private String password;
    private boolean authority;
    private String company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserBean userBean = (UserBean) o;

        if (username != null ? !username.equals(userBean.username) : userBean.username != null) return false;
        return password != null ? password.equals(userBean.password) : userBean.password == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
