package com.thingtek.database.domain;

import com.thingtek.config.AgreementConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

public @Data
class UnitBean extends BaseBean implements Serializable {

    private AgreementConfig agreementConfig;

    private String snid;
    private String pwd;
    private String soft;
    private String hard;
    private Integer deep;
    private Integer ringNum;
    private Integer measurePeriod;
    private Integer motorSpeed;
    private String tcpAddr;
    private Integer tcpPort;
    private String ftpAddr;
    private Integer ftpPort;
    //    private Date date;
    private byte[] reserve = new byte[12];

    public boolean checkInfo(UnitBean o) {
        return this == o || o != null
                && getClass() == o.getClass()
                && (snid != null ? snid.equals(o.snid) : o.snid == null)
                && (pwd != null ? pwd.equals(o.pwd) : o.pwd == null);
    }

    public boolean checkTask(UnitBean o) {
        return this == o || o != null && getClass() == o.getClass()
                && Objects.equals(deep, o.deep)
                && Objects.equals(ringNum, o.ringNum)
                && Objects.equals(measurePeriod, o.measurePeriod)
                && Objects.equals(motorSpeed, o.motorSpeed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UnitBean unitBean = (UnitBean) o;

        return snid != null ? snid.equals(unitBean.snid) : unitBean.snid == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (snid != null ? snid.hashCode() : 0);
        return result;
    }
}
