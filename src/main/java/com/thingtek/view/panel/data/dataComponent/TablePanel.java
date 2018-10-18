package com.thingtek.view.panel.data.dataComponent;

import com.thingtek.database.domain.DataBean;
import com.thingtek.database.domain.EntityBean;
import com.thingtek.database.domain.UnitBean;
import com.thingtek.database.service.DataService;
import com.thingtek.database.service.UnitService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class TablePanel extends JPanel {
    private TableUnitPanel tableUnitPanel;

    @Resource
    private UnitService unitService;

    @Resource
    private DataService dataService;

    @Resource
    private ChartPanel chartPanel;

    public void init() {
        setLayout(new BorderLayout());

        JPanel center = new JPanel();
        JScrollPane jScrollPane = new JScrollPane(center);
        center.setBackground(Color.WHITE);
        add(jScrollPane, BorderLayout.CENTER);

        java.util.List<UnitBean> unitBeans = unitService.getAll();
        center.setLayout(new GridLayout(unitBeans.size(), 1,5,5));

        for (final UnitBean unitBean : unitBeans) {
            TableUnitPanel tableUnitPanel = new TableUnitPanel();
            tableUnitPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    chartPanel.setUnitBean(unitBean);
                    java.util.List<DataBean> dataBeanList = dataService.findBySNID(new EntityBean(unitBean.getSnid(), null, null));
                    chartPanel.changeData(dataBeanList.toArray(new DataBean[0]));
                    chartPanel.repaint();
                }
            });
            tableUnitPanel.init();
            tableUnitPanel.setUnitBean(unitBean);
            tableUnitPanel.setDataBeanFirst(dataService.findFirstDataBySnid(unitBean.getSnid()));
            tableUnitPanel.setDataBeanNew(dataService.findNewDataBySnid(unitBean.getSnid()));
            tableUnitPanel.repaint();
            center.add(tableUnitPanel);
        }
       /* tableUnitPanel = new TableUnitPanel();
        tableUnitPanel.init();

        center.add(tableUnitPanel, BorderLayout.CENTER);*/

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

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(0,200);
    }
}
