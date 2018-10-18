package com.thingtek.view.panel.data.dataComponent;

import javax.swing.*;
import java.awt.*;

public class TableUnitLabel extends JLabel {

    public TableUnitLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    public TableUnitLabel() {
        super();
        init();
    }

    private void init() {
        setBorder(BorderFactory.createLineBorder(new Color(0x7F9DB9),1));
    }

    @Override
    public boolean isOpaque() {
        return true;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(70, 20);
    }
}
