package com.thingtek.database.dao;

import com.thingtek.database.domain.DataBean;
import com.thingtek.database.domain.EntityBean;

import java.util.List;
import java.util.Map;

public interface DataDAO {

    void save(List<DataBean> datas) throws Exception;

    Integer findFirstDay(String SNID) throws Exception;

    List<Map<String, Object>> find(EntityBean entity) throws Exception;

    List<Integer> findDays(EntityBean entity) throws Exception;

    Map<String, Object> findFirstData(String snid) throws Exception;

    Map<String, Object> findNewData(String snid) throws Exception;
}
