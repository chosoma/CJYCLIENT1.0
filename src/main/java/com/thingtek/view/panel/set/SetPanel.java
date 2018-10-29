package com.thingtek.view.panel.set;

import com.thingtek.view.panel.BasePanel;
import com.thingtek.view.panel.set.setComponent.BaseSetPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Set;

public class SetPanel extends BasePanel {

    private Map<String, BaseSetPanel> setPanels;

    public void setSetPanels(Map<String, BaseSetPanel> setPanels) {
        this.setPanels = setPanels;
    }

    private JPanel leftPanel;
    private JPanel rightPanel;
    private CardLayout card;

    public SetPanel init() {
        setLayout(new BorderLayout());
        JSplitPane jSplitPane = new JSplitPane();
        add(jSplitPane, BorderLayout.CENTER);
        leftPanel = new JPanel(new GridLayout(setPanels.size(),1));
        jSplitPane.setLeftComponent(leftPanel);
        card = new CardLayout();
        rightPanel = new JPanel(card);
        jSplitPane.setRightComponent(rightPanel);
        jSplitPane.setDividerLocation(120);
        jSplitPane.setEnabled(false);
        initCenter();
        return this;
    }

    private void initCenter() {
        Set<Map.Entry<String, BaseSetPanel>> entries = setPanels.entrySet();

        for (final Map.Entry<String, BaseSetPanel> entry : entries) {
            JLabel jLabel = new JLabel(entry.getKey(), JLabel.CENTER);
            jLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    card.show(rightPanel, entry.getKey());
                }
            });
            leftPanel.add(jLabel);
            rightPanel.add(entry.getValue(), entry.getKey());

        }

    }


}
