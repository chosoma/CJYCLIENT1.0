package com.thingtek.view.panel.data.dataComponent;

import com.thingtek.database.domain.DataBean;
import com.thingtek.database.domain.UnitBean;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

@Component
public class ChartPanel extends JPanel {

    public void init() {
        setLayout(new BorderLayout());
        dataset = new TimeSeriesCollection();
        initchart();
    }

    private UnitBean unitBean;

    public void setUnitBean(UnitBean unitBean) {
        this.unitBean = unitBean;
    }

    private TimeSeriesCollection dataset;

    private void initchart() {
        Font font = new Font("微软雅黑", Font.PLAIN, 12);

        DateAxis timeAxis = new DateAxis();
        timeAxis.setLowerMargin(0.02);  // reduce the default margins
        timeAxis.setUpperMargin(0.02);
        timeAxis.setDateFormatOverride(new SimpleDateFormat("YYYY-MM-dd"));
        timeAxis.setLabelFont(font);
        timeAxis.setTickLabelFont(font);

        NumberAxis valueAxis = new NumberAxis();
        valueAxis.setAutoRangeIncludesZero(false);  // override default
        valueAxis.setLabelFont(font);
        valueAxis.setTickLabelFont(font);
//        valueAxis.setNumberFormatOverride(new DecimalFormat("#0.00"));

        XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));//图片区与坐标轴的距离
        plot.setOutlinePaint(null);
        plot.setInsets(new RectangleInsets(0, 0, 0, 0));//坐标轴与最外延的距离
        XYToolTipGenerator toolTipGenerator = StandardXYToolTipGenerator.getTimeSeriesInstance();
        XYURLGenerator urlGenerator = new StandardXYURLGenerator();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setBaseToolTipGenerator(toolTipGenerator);
        renderer.setURLGenerator(urlGenerator);
//        plot.setRenderer(renderer);
        /*设置颜色*/
        renderer.setSeriesPaint(0, new Color(236, 236, 0));//A
        renderer.setSeriesPaint(1, new Color(50, 153, 102));//B
        renderer.setSeriesPaint(2, new Color(236, 0, 0));//C
        renderer.setSeriesPaint(3, new Color(0, 0, 0));//C
        renderer.setBaseShapesVisible(false);
//        xylineandshaperenderer.setBaseShapesVisible(true);
        renderer.setBaseItemLabelsVisible(false);//设置值显示 true 显示 false 不显示
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10, TextAnchor.BASELINE_LEFT));
        renderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
//        xyitem.setBaseItemLabelFont(new Font("Dialog", Font.BOLD, 14));
        plot.setRenderer(renderer);
        JFreeChart chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        chart.setTextAntiAlias(false);
//        chart.setTitle(new TextTitle("", new Font(null, Font.PLAIN, 15)));
        chart.setAntiAlias(true);//设置抗锯齿
//        currentTheme.apply(chart);
        chart.setPadding(new RectangleInsets(5, 5, 5, 5));
        chart.setNotify(true);
        org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    public void changeData(DataBean... dataBeans) {
        dataset.removeAllSeries();
        for (int i = 0; unitBean.getRingNum() != null && i < unitBean.getRingNum(); i++) {
            TimeSeries timeSeries = new TimeSeries("磁环" + (i + 1));
            for (DataBean data : dataBeans) {
                timeSeries.add(int2Date((int) data.get("day")), (Double) data.get("ring" + i));
            }
            dataset.addSeries(timeSeries);
        }
    }

    private Day int2Date(int day) {
        return new Day(day % 100, day % 10000 / 100, day / 10000 + 2000);
    }


}