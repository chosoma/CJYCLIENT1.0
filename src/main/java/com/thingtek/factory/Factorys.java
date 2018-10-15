package com.thingtek.factory;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public @Data
class Factorys {


    @Resource
    private IconFactory iconFactory;
    @Resource
    private FontFactory fontFactory;

}
