package com.thingtek.view.panel.data.dataComponent;

import com.thingtek.database.domain.DataBean;
import com.thingtek.database.domain.UnitBean;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class TablePanel extends JPanel {
    private TableUnitPanel tableUnitPanel;

    public void init() {
        setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(center, BorderLayout.NORTH);

        tableUnitPanel = new TableUnitPanel();
        tableUnitPanel.init();

        center.add(tableUnitPanel, BorderLayout.CENTER);


    }

    public void setUnit(UnitBean unitBean) {
        tableUnitPanel.setUnitBean(unitBean);
    }

    public void setFirst(DataBean dataBean) {
        tableUnitPanel.setDataBeanFirst(dataBean);
    }

    public void setNew(DataBean dataBean) {
        tableUnitPanel.setDataBeanNew(dataBean);
    }

    public void clear() {
        tableUnitPanel.clear();
    }

}
