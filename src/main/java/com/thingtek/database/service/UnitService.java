package com.thingtek.database.service;

import com.thingtek.config.AgreementConfig;
import com.thingtek.database.dao.UnitDAO;
import com.thingtek.database.domain.UnitBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService extends BaseService {
    @Resource
    private UnitDAO unitDAO;


    @Resource
    private AgreementConfig agreementConfig;

    public UnitBean checkUnit(UnitBean unitBean) {
        try {
            UnitBean unit = unitDAO.findBySn(unitBean.getSnid());
            unit.setAgreementConfig(agreementConfig);
            return unitBean.checkInfo(unit) ? unit : null;
        } catch (Exception e) {
            log(e);
        }
        return null;
    }

    public void saveTask(UnitBean unitBean) {
    }

    public UnitBean getUnitBySnid(String snid) {
        try {
            return unitDAO.findBySn(snid);
        } catch (Exception e) {
            log(e);
        }
        return null;
    }

    public List<String> getSnids() {
        try {
            return unitDAO.findSnids();
        } catch (Exception e) {
            log(e);
            return new ArrayList<>();
        }
    }
}
