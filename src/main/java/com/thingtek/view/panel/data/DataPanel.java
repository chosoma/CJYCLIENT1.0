package com.thingtek.view.panel.data;

import com.thingtek.database.service.DataService;
import com.thingtek.database.service.UnitService;
import com.thingtek.view.panel.BasePanel;
import com.thingtek.view.panel.data.dataComponent.ChartPanel;
import com.thingtek.view.panel.data.dataComponent.TablePanel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;


@Component
public class DataPanel extends BasePanel {


//    private JPanel toolPanel;

    @Resource
    private TablePanel tablePanel;

    @Resource
    private ChartPanel chartPanel;


    public DataPanel init() {
        setLayout(new BorderLayout());
//        initTool();
        initCenter();
        return this;
    }

   /* private JComboBox<String> jcbSnids;
    @Resource
    private DataService dataService;
    @Resource
    private UnitService unitService;*/

    /*private void initTool() {
        toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolPanel.setBackground(Color.WHITE);
        JTextArea jtaSnid = new JTextArea("设备编号:");
        jtaSnid.setEnabled(false);
        jtaSnid.setOpaque(false);
        toolPanel.add(jtaSnid);
        Vector<String> snid = new Vector<>();
        snid.addAll(unitService.getSnids());
        jcbSnids = new JComboBox<>(snid);
        jcbSnids.setSelectedItem(null);
        toolPanel.add(jcbSnids);
        JButton search = new JButton("查询");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tablePanel.clear();

                String snid = (String) jcbSnids.getSelectedItem();
                if (snid == null) {
                    return;
                }

                UnitBean unitBean = unitService.getUnitBySnid(snid);

                if (unitBean == null) {
                    return;
                }

                tablePanel.setUnit(unitBean);
                DataBean firstData = dataService.findFirstDataBySnid(snid);
                DataBean newData = dataService.findNewDataBySnid(snid);
                tablePanel.setFirst(firstData);
                tablePanel.setNew(newData);
                tablePanel.repaint();

                chartPanel.setUnitBean(unitBean);
                java.util.List<DataBean> dataBeanList = dataService.findBySNID(new EntityBean(snid, null, null));
                chartPanel.changeData(dataBeanList.toArray(new DataBean[0]));
                chartPanel.repaint();
            }
        });
        toolPanel.add(search);
        add(toolPanel, BorderLayout.NORTH);
    }*/

    private void initCenter() {

        /*JSplitPane center = new JSplitPane();

        center.add(tablePanel, JSplitPane.LEFT);
        center.add(chartPanel, JSplitPane.RIGHT);*/

        chartPanel.init();
        tablePanel.init();
        chartPanel.setBackground(Color.WHITE);
        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jSplitPane.setDividerLocation(200);
        jSplitPane.setBackground(Color.WHITE);
        jSplitPane.setLeftComponent(tablePanel);
        jSplitPane.setBottomComponent(chartPanel);
        add(jSplitPane, BorderLayout.CENTER);
    }

}
