package CSCI185GroupProject;
import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.xy.*;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args) {
        XYIntervalDataItem test = new XYIntervalDataItem(1,0,10,1,0,10);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100,"Funds","1900");
        JFreeChart chart = ChartFactory.createLineChart(
                "Funds",
                "Year",
                "Money",
                dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setContentPane(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}