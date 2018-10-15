package com.thingtek.database.dao;


import com.thingtek.database.domain.UnitBean;

import java.util.List;

public interface UnitDAO {

    void save(UnitBean unit) throws Exception;

    void saveInfo(UnitBean unit) throws Exception;

    void saveTask(UnitBean unit) throws Exception;

    List<UnitBean> findInfo() throws Exception;

    List<UnitBean> findTask() throws Exception;

    UnitBean checkUnit(UnitBean unitBean) throws Exception;

    UnitBean findBySn(String snid) throws Exception;

    List<String> findSnids() throws Exception;

}
