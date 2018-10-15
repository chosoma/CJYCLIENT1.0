package com.thingtek.tools;

import javax.swing.*;
import java.awt.*;

public class TitleButton extends JButton {

    public TitleButton(String text, Icon icon) {
        super(text, icon);
    }

    public TitleButton(String text) {
        super(text);
    }

    public Dimension getPreferredSize() {
        return new Dimension(64, 74);
    }

    public boolean isFocusable() {
        return false;
    }

    public boolean isFocusPainted() {
        return false;
    }

    public boolean isBorderPainted() {
        return false;
    }

    public int getVerticalTextPosition() {
        return SwingConstants.BOTTOM;
    }

    public int getHorizontalTextPosition() {
        return SwingConstants.CENTER;
    }

    public Color getForeground() {
        return Color.BLACK;
    }

    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

}
