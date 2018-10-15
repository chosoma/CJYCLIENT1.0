package com.thingtek.database.service;

import org.slf4j.LoggerFactory;

public abstract class BaseService {

    protected void log(Exception e) {
        LoggerFactory.getLogger(this.getClass()).error(e.toString());
        e.printStackTrace();
    }

    protected void logInfo(String object) {
//        System.out.println(object);
        LoggerFactory.getLogger(this.getClass()).info(object);
    }

}
