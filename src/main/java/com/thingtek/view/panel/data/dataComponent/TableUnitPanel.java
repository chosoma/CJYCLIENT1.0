package com.thingtek.view.panel.data.dataComponent;

import com.thingtek.database.domain.DataBean;
import com.thingtek.database.domain.UnitBean;

import javax.swing.*;
import java.awt.*;

public class TableUnitPanel extends JPanel {

    public TableUnitPanel() {
        setLayout(new BorderLayout());
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        ringsPanel = new JPanel(gbl);
    }

    private GridBagLayout gbl;
    private GridBagConstraints gbc;

    private JPanel ringsPanel;

    private UnitBean unitBean;

    private DataBean dataBeanFirst;

    private DataBean dataBeanNew;

    private TableUnitLabel[] jlringsFirst;
    private TableUnitLabel jlbatlvFirst;
    private TableUnitLabel[] jlringsNew;
    private TableUnitLabel jlbatlvNew;
    private TableUnitLabel[] jlringsSub;
    private TableUnitLabel jldayFirst;
    private TableUnitLabel jldayNew;

    private TableUnitLabel jlsnid;

    public void init() {

        jlsnid = new TableUnitLabel("", JLabel.CENTER);
        this.add(jlsnid, BorderLayout.NORTH);
        this.add(ringsPanel, BorderLayout.CENTER);
        TableUnitLabel[] jltitles = new TableUnitLabel[13];
        jltitles[0] = new TableUnitLabel();
        for (int i = 1; i <= 10; i++) {
            jltitles[i] = new TableUnitLabel("磁环" + i, JLabel.CENTER);
        }
        jltitles[jltitles.length - 2] = new TableUnitLabel("电压", JLabel.CENTER);
        jltitles[jltitles.length - 1] = new TableUnitLabel("日期", JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (TableUnitLabel jltitle : jltitles) {
            gbl.setConstraints(jltitle, gbc);
            ringsPanel.add(jltitle);
            gbc.gridy++;
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
        TableUnitLabel jltitleFirst = new TableUnitLabel("安装记录", JLabel.CENTER);
        gbl.setConstraints(jltitleFirst, gbc);
        ringsPanel.add(jltitleFirst);
        gbc.gridx++;
        TableUnitLabel jltitleNew = new TableUnitLabel("测量数据", JLabel.CENTER);
        gbl.setConstraints(jltitleNew, gbc);
        ringsPanel.add(jltitleNew);
        gbc.gridx++;
        TableUnitLabel jltitleSub = new TableUnitLabel("沉降值", JLabel.CENTER);
        gbl.setConstraints(jltitleSub, gbc);
        ringsPanel.add(jltitleSub);
        gbc.gridx++;

        jlringsFirst = new TableUnitLabel[10];
        jlringsNew = new TableUnitLabel[10];
        jlringsSub = new TableUnitLabel[10];

        for (int i = 0; i < 10; i++) {
            gbc.gridy++;
            gbc.gridx = 1;
            jlringsFirst[i] = new TableUnitLabel("", JLabel.CENTER);
            gbl.setConstraints(jlringsFirst[i], gbc);
            ringsPanel.add(jlringsFirst[i]);
            gbc.gridx++;
            jlringsNew[i] = new TableUnitLabel("", JLabel.CENTER);
            gbl.setConstraints(jlringsNew[i], gbc);
            ringsPanel.add(jlringsNew[i]);
            gbc.gridx++;
            jlringsSub[i] = new TableUnitLabel("", JLabel.CENTER);
            gbl.setConstraints(jlringsSub[i], gbc);
            ringsPanel.add(jlringsSub[i]);
        }

        gbc.gridy++;
        gbc.gridx = 1;
        jlbatlvFirst = new TableUnitLabel("", JLabel.CENTER);
        gbl.setConstraints(jlbatlvFirst, gbc);
        ringsPanel.add(jlbatlvFirst);
        gbc.gridx++;
        jlbatlvNew = new TableUnitLabel("", JLabel.CENTER);
        gbl.setConstraints(jlbatlvNew, gbc);
        ringsPanel.add(jlbatlvNew);
        gbc.gridx++;
        TableUnitLabel nulllabel1 = new TableUnitLabel();
        gbl.setConstraints(nulllabel1, gbc);
        ringsPanel.add(nulllabel1);
        gbc.gridy++;
        gbc.gridx = 1;
        jldayFirst = new TableUnitLabel("", JLabel.CENTER);
        gbl.setConstraints(jldayFirst, gbc);
        ringsPanel.add(jldayFirst);
        gbc.gridx++;
        jldayNew = new TableUnitLabel("", JLabel.CENTER);
        gbl.setConstraints(jldayNew, gbc);
        ringsPanel.add(jldayNew);
        gbc.gridx++;
        TableUnitLabel nulllabel2 = new TableUnitLabel();
        gbl.setConstraints(nulllabel2, gbc);
        ringsPanel.add(nulllabel2);
    }


    private void initFirst() {
        jldayFirst.setText(String.valueOf(dataBeanFirst.get("day")));
        jlbatlvFirst.setText(String.valueOf(dataBeanFirst.get("voltage")));
        for (int i = 0; unitBean.getRingNum() != null && i < unitBean.getRingNum(); i++) {
            jlringsFirst[i].setText(String.valueOf(dataBeanFirst.get("ring" + i)));
        }
    }

    private void initNew() {
        jldayNew.setText(String.valueOf(dataBeanNew.get("day")));
        jlbatlvNew.setText(String.valueOf(dataBeanNew.get("voltage")));
        for (int i = 0; unitBean.getRingNum() != null && i < unitBean.getRingNum(); i++) {
            jlringsNew[i].setText(String.valueOf(dataBeanNew.get("ring" + i)));
        }
        initSub();
    }

    public void initSub() {
        for (int i = 0; unitBean.getRingNum() != null && i < unitBean.getRingNum(); i++) {
            double newRing = dataBeanNew.get("ring" + i).equals("") ? 0 : (double) dataBeanNew.get("ring" + i);
            double firstRing = dataBeanFirst.get("ring" + i).equals("") ? 0 : (double) dataBeanFirst.get("ring" + i);
            double ring = newRing - firstRing;
            jlringsSub[i].setText(String.valueOf(ring));
        }
    }

    public void clear() {
        for (JLabel j : jlringsFirst) {
            j.setText("");
        }
        jlbatlvFirst.setText("");
        jldayFirst.setText("");
        for (JLabel j : jlringsNew) {
            j.setText("");
        }
        jlbatlvNew.setText("");
        jldayNew.setText("");
        for (JLabel j : jlringsSub) {
            j.setText("");
        }


    }


    public void setUnitBean(UnitBean unitBean) {
        this.unitBean = unitBean;
        jlsnid.setText(unitBean.getSnid());
    }

    public void setDataBeanFirst(DataBean dataBeanFirst) {
        this.dataBeanFirst = dataBeanFirst;
        initFirst();
    }

    public void setDataBeanNew(DataBean dataBeanNew) {
        this.dataBeanNew = dataBeanNew;
        initNew();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 280);
    }
}
