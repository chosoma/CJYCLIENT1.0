package com.thingtek.view.panel.manager;

import com.thingtek.database.domain.DataBean;
import com.thingtek.database.domain.EntityBean;
import com.thingtek.database.domain.UnitBean;
import com.thingtek.database.service.DataService;
import com.thingtek.database.service.UnitService;
import com.thingtek.view.panel.BasePanel;
import com.thingtek.view.panel.manager.dataManagerComponent.DataManageModel;
import com.thingtek.view.panel.manager.dataManagerComponent.TableRenderer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Component
public class DataManagerPanel extends BasePanel {

    private JPanel toolPanel;
    private JComboBox<String> jcbSnids;
    @Resource
    private DataService dataService;
    @Resource
    private UnitService unitService;


    public DataManagerPanel init() {
        setLayout(new BorderLayout());
        initTool();
        initCenter();
        return this;
    }

    private void initTool() {
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
                String snid = (String) jcbSnids.getSelectedItem();
                UnitBean unitBean = unitService.getUnitBySnid(snid);
                model = new DataManageModel(unitBean);
                table.setModel(model);
                java.util.List<Vector<Object>> datas = new ArrayList<>();
                List<DataBean> databeans = dataService.findBySNID(new EntityBean(snid, null, null));
                for (DataBean data : databeans) {
                    datas.add(data.getTableData(unitBean));
                }
                model.addDatas(datas);

            }
        });

        toolPanel.add(search);
        add(toolPanel, BorderLayout.NORTH);
    }

    private JTable table;
    private DataManageModel model;

    private void initCenter() {
        table = new JTable();
        initializeTable();
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane, BorderLayout.CENTER);
    }
    private void initializeTable() {
        TableRenderer tcr = new TableRenderer();
        table.setDefaultRenderer(String.class, tcr);
        table.setDefaultRenderer(Number.class, tcr);
        table.setDefaultRenderer(Float.class, tcr);
        table.setDefaultRenderer(Double.class, tcr);
        table.setDefaultRenderer(Date.class, tcr);

        // 表头设置
        JTableHeader tableHeader = table.getTableHeader();
        DefaultTableCellRenderer dtcr = (DefaultTableCellRenderer) tableHeader.getDefaultRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);// 表头居中
        Dimension dimension = dtcr.getSize();
        dimension.height = 40;
        dtcr.setPreferredSize(dimension);// 设置表头高度
        tableHeader.setDefaultRenderer(dtcr);
        // 表头不可拖动
        tableHeader.setReorderingAllowed(false);
        // 列宽不可修改
        tableHeader.setResizingAllowed(false);

        // 自动排序
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(20);// 设置行高

    }
    @Override
    public Color getBackground() {
        return Color.WHITE;
    }


}
