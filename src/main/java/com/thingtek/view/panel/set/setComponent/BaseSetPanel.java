package com.thingtek.view.panel.set.setComponent;

import javax.swing.*;

public abstract class BaseSetPanel extends JPanel {

    protected JPanel titlePanel;

    protected JPanel centerPanel;

    protected JPanel toolPanel;

    public BaseSetPanel init() {

        titlePanel = new JPanel();
        centerPanel = new JPanel();
        toolPanel = new JPanel();
        initTitle();
        initCenter();
        initTool();
        return this;
    }

    protected abstract void initTitle();

    protected abstract void initCenter();

    protected abstract void initTool();

}
