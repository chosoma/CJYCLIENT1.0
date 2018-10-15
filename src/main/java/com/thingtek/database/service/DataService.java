package com.thingtek.database.service;

import com.thingtek.config.AgreementConfig;
import com.thingtek.database.dao.DataDAO;
import com.thingtek.database.domain.DataBean;
import com.thingtek.database.domain.EntityBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DataService extends BaseService {

    @Resource
    private DataDAO dataDAO;
    @Resource
    private AgreementConfig agreementConfig;

    public int findFirstDayBySnid(String SNID) {
        try {
            return dataDAO.findFirstDay(SNID);
        } catch (Exception e) {
            log(e);
        }
        return 0;
    }

    public List<DataBean> findBySNID(EntityBean entity) {
        List<DataBean> datas = new ArrayList<>();
        try {
            List<Map<String, Object>> maps = dataDAO.find(entity);
            for (Map<String, Object> one : maps) {
                DataBean dataBean = new DataBean();
                dataBean.setAgreementConfig(agreementConfig);
                dataBean.setOne(one);
                dataBean.resolve();
                datas.add(dataBean);
            }
        } catch (Exception e) {
            log(e);
        }
        return datas;
    }

    public DataBean findFirstDataBySnid(String snid) {
        DataBean dataBean = new DataBean();
        try {
            dataBean.setOne(dataDAO.findFirstData(snid));
            dataBean.resolve();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataBean;
    }

    public DataBean findNewDataBySnid(String snid) {
        DataBean dataBean = new DataBean();
        try {
            dataBean.setOne(dataDAO.findNewData(snid));
            dataBean.resolve();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataBean;
    }


    public List<Integer> findDaysBySNID(EntityBean entity) {
        try {
            return dataDAO.findDays(entity);
        } catch (Exception e) {
            log(e);
            return new ArrayList<>();
        }
    }

    public void save(List<DataBean> datas) {
        System.out.println("save");
        try {
            dataDAO.save(datas);
        } catch (Exception e) {
            log(e);
        }
    }

    public void save(DataBean... datas) {
        save(Arrays.asList(datas));
    }
}
