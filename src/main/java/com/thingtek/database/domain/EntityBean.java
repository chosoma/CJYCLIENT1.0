package com.thingtek.database.domain;

import lombok.Data;

public@Data
class EntityBean {
    private String snid;
    private Integer start;
    private Integer end;

    public EntityBean() {
    }

    public EntityBean(String SNID, Integer start, Integer end) {
        this.snid = SNID;
        this.start = start;
        this.end = end;
    }
}
