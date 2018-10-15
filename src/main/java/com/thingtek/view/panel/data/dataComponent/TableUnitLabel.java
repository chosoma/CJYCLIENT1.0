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
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,20);
    }
}
