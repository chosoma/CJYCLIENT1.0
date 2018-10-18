package com.thingtek.view.panel.data.dataComponent;

import com.thingtek.database.domain.DataBean;
import com.thingtek.database.domain.UnitBean;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        Color titleColor1 = new Color(0x8ABFED);
        Color titleColor2 = new Color(0xB6D8F5);
        Color dataColor = new Color(0xffffff);

        jlsnid = new TableUnitLabel("", JLabel.CENTER);
        jlsnid.setBackground(dataColor);
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
            jltitle.setBackground(titleColor1);
            ringsPanel.add(jltitle);
            gbc.gridx++;
        }
        gbc.gridy = 1;
        gbc.gridx = 0;
        TableUnitLabel jltitleFirst = new TableUnitLabel("安装记录", JLabel.CENTER);
        jltitleFirst.setBackground(titleColor2);
        gbl.setConstraints(jltitleFirst, gbc);
        ringsPanel.add(jltitleFirst);
        gbc.gridy++;
        TableUnitLabel jltitleNew = new TableUnitLabel("测量数据", JLabel.CENTER);
        jltitleNew.setBackground(titleColor2);
        gbl.setConstraints(jltitleNew, gbc);
        ringsPanel.add(jltitleNew);
        gbc.gridy++;
        TableUnitLabel jltitleSub = new TableUnitLabel("沉降值", JLabel.CENTER);
        jltitleSub.setBackground(titleColor2);
        gbl.setConstraints(jltitleSub, gbc);
        ringsPanel.add(jltitleSub);
        gbc.gridy++;

        jlringsFirst = new TableUnitLabel[10];
        jlringsNew = new TableUnitLabel[10];
        jlringsSub = new TableUnitLabel[10];

        for (int i = 0; i < 10; i++) {
            gbc.gridx++;
            gbc.gridy = 1;
            jlringsFirst[i] = new TableUnitLabel("", JLabel.CENTER);
            jlringsFirst[i].setBackground(dataColor);
            gbl.setConstraints(jlringsFirst[i], gbc);
            ringsPanel.add(jlringsFirst[i]);
            gbc.gridy++;
            jlringsNew[i] = new TableUnitLabel("", JLabel.CENTER);
            jlringsNew[i].setBackground(dataColor);
            gbl.setConstraints(jlringsNew[i], gbc);
            ringsPanel.add(jlringsNew[i]);
            gbc.gridy++;
            jlringsSub[i] = new TableUnitLabel("", JLabel.CENTER);
            jlringsSub[i].setBackground(dataColor);
            gbl.setConstraints(jlringsSub[i], gbc);
            ringsPanel.add(jlringsSub[i]);
        }

        gbc.gridx++;
        gbc.gridy = 1;
        jlbatlvFirst = new TableUnitLabel("", JLabel.CENTER);
        jlbatlvFirst.setBackground(dataColor);
        gbl.setConstraints(jlbatlvFirst, gbc);
        ringsPanel.add(jlbatlvFirst);
        gbc.gridy++;
        jlbatlvNew = new TableUnitLabel("", JLabel.CENTER);
        jlbatlvNew.setBackground(dataColor);
        gbl.setConstraints(jlbatlvNew, gbc);
        ringsPanel.add(jlbatlvNew);
        gbc.gridy++;
        TableUnitLabel nulllabel1 = new TableUnitLabel();
        nulllabel1.setBackground(dataColor);
        gbl.setConstraints(nulllabel1, gbc);
        ringsPanel.add(nulllabel1);
        gbc.gridx++;
        gbc.gridy = 1;
        jldayFirst = new TableUnitLabel("", JLabel.CENTER);
        jldayFirst.setBackground(dataColor);
        gbl.setConstraints(jldayFirst, gbc);
        ringsPanel.add(jldayFirst);
        gbc.gridy++;
        jldayNew = new TableUnitLabel("", JLabel.CENTER);
        jldayNew.setBackground(dataColor);
        gbl.setConstraints(jldayNew, gbc);
        ringsPanel.add(jldayNew);
        gbc.gridy++;
        TableUnitLabel nulllabel2 = new TableUnitLabel();
        nulllabel2.setBackground(dataColor);
        gbl.setConstraints(nulllabel2, gbc);
        ringsPanel.add(nulllabel2);
    }


    private void initFirst() {
        Object day = dataBeanFirst.get("day");
        jldayFirst.setText(day instanceof Integer ? intday2date((Integer) day) : String.valueOf(day));
//        jlbatlvFirst.setText(String.valueOf(dataBeanFirst.get("voltage")));
        for (int i = 0; unitBean.getRingNum() != null && i < unitBean.getRingNum(); i++) {
            jlringsFirst[i].setText(String.valueOf(dataBeanFirst.get("ring" + i)));
        }
    }

    private void initNew() {
        Object day = dataBeanNew.get("day");
        jldayNew.setText(day instanceof Integer ? intday2date((Integer) day) : String.valueOf(day));
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

    private String intday2date(int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, day / 10000 + 2000);
        c.set(Calendar.MONTH, day % 10000 / 100 - 1);
        c.set(Calendar.DAY_OF_MONTH, day % 100);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

/*    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 280);
    }*/
}
