package com.thingtek.factory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class IconFactory {


    public List<String> logoIconTexts;

    public List<Image> getLogoIconTexts() {
        List<Image> images = new ArrayList<>();
        for (String str : logoIconTexts) {
            images.add(new ImageIcon(this.getClass().getClassLoader().getResource(str)).getImage());
        }
        return images;
    }

    private Map<String, String> iconpathMap = new Hashtable<>();


    public void setIconpathMap(Map<String, String> iconpathMap) {
        this.iconpathMap = iconpathMap;
    }


    public ImageIcon getIcon(String string) {
        return new ImageIcon(this.getClass().getClassLoader().getResource(iconpathMap.get(string)));
    }

    public Image getImage(String string) {
        try {
            return ImageIO.read(this.getClass().getClassLoader().getResource(iconpathMap.get(string)));
        } catch (Exception e) {
            return null;
        }
    }

}
