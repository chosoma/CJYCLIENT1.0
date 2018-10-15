package com.thingtek.database.domain;


import com.thingtek.config.AgreementConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
public @Data
class DataBean extends BaseBean implements Serializable {

    private Map<String, Object> one;

    private String snid;
    private float[] rings = new float[10];
    private float voltage;
    private int day;
    private Date date;
    private AgreementConfig agreementConfig;

    public void nullData() {
        for (int i = 0; i < rings.length; i++) {
            rings[i] = -0.1f;
        }
    }

    public DataBean resolve() {
        /*if (one == null) {
            return this;
        }
        for (int i = 0; i < rings.length; i++) {
            rings[i] = one.get("ring" + i) == null ? -0.1f : (float) (double) one.get("ring" + i);
        }
        voltage = one.get("voltage") == null ? -0.1f : (float) (double) one.get("voltage");
        day = (int) one.get("day");*/

        return this;
    }

    public Object get(String key) {
        return one == null ? "" : one.get(key);
    }


}
