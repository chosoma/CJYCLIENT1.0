package com.thingtek.factory;

import java.awt.*;
import java.util.Map;

public class FontFactory {

    private Map<String, Font> fontMap;

    public void setFontMap(Map<String, Font> fontMap) {
        this.fontMap = fontMap;
    }

    public Font getFont(String key) {
        return fontMap.get(key);
    }

}
