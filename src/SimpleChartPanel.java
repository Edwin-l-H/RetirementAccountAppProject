/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This our class that draws a simple line chart using Java graphics. I had to revert to my MATLabs notes for this.
*/

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class SimpleChartPanel extends JPanel {

    private double[] line1;
    private double[] line2;
    private double[] line3;
    private String title;
    private String line1Name;
    private String line2Name;
    private String line3Name;
    private int startAge;

    public SimpleChartPanel(String title) {
        this.title = title;
        this.line1 = new double[0];
        this.line2 = null;
        this.line3 = null;
        this.line1Name = "Balance";
        this.line2Name = "";
        this.line3Name = "Contributions";
        this.startAge = 0;
    }

    public void setData(double[] line1,
                        double[] line2,
                        double[] line3,
                        int startAge,
                        String line1Name,
                        String line2Name,
                        String line3Name) {

        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.startAge = startAge;
        this.line1Name = line1Name;
        this.line2Name = line2Name;
        this.line3Name = line3Name;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawString(title, 20, 20);

        if (line1 == null || line1.length < 2) {
            g.drawString("A Chart will show after calculations.", 20, 45);
            return;
        }

        int leftMargin = 95;
        int rightMargin = 35;
        int topMargin = 55;
        int bottomMargin = 90;

        int chartWidth = getWidth() - leftMargin - rightMargin;
        int chartHeight = getHeight() - topMargin - bottomMargin;

        int startX = leftMargin;
        int startY = getHeight() - bottomMargin;

        double max = getMaxValue();

        g.setColor(Color.BLACK);
        g.drawLine(startX, topMargin, startX, startY);
        g.drawLine(startX, startY, startX + chartWidth, startY);

        int yTicks = 5;

        for (int i = 0; i <= yTicks; i++) {
            int y = startY - (i * chartHeight / yTicks);
            double value = max * i / yTicks;

            g.drawLine(startX - 5, y, startX, y);
            g.drawString(formatMoney(value), 10, y + 5);
        }

        int xTicks = 5;

        for (int i = 0; i <= xTicks; i++) {
            int index = i * (line1.length - 1) / xTicks;
            int x = startX + (index * chartWidth / (line1.length - 1));
            int age = startAge + index;

            g.drawLine(x, startY, x, startY + 5);
            g.drawString("" + age, x - 8, startY + 20);
        }

        g.drawString("Age", startX + chartWidth / 2, getHeight() - 45);
        g.drawString("Amount", 20, topMargin - 15);

        g.setColor(Color.BLACK);
        drawLine(g, line1, max, startX, startY, chartWidth, chartHeight);

        if (line2 != null) {
            g.setColor(Color.GRAY);
            drawLine(g, line2, max, startX, startY, chartWidth, chartHeight);
        }

        if (line3 != null) {
            g.setColor(Color.LIGHT_GRAY);
            drawLine(g, line3, max, startX, startY, chartWidth, chartHeight);
        }

        drawLegend(g, startX);
    }

    private void drawLegend(Graphics g, int startX) {
        int y1 = getHeight() - 30;
        int y2 = getHeight() - 15;

        g.setColor(Color.BLACK);
        g.drawString("Black = " + line1Name, startX, y1);

        if (line2 != null && line3 != null) {
            g.setColor(Color.GRAY);
            g.drawString("Gray = " + line2Name, startX, y2);

            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Light Gray = " + line3Name, startX + 150, y2);
        }
        else if (line2 != null) {
            g.setColor(Color.GRAY);
            g.drawString("Gray = " + line2Name, startX, y2);
        }
        else if (line3 != null) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawString("Light Gray = " + line3Name, startX, y2);
        }

        g.setColor(Color.BLACK);
    }

    private void drawLine(Graphics g,
                          double[] values,
                          double max,
                          int startX,
                          int startY,
                          int chartWidth,
                          int chartHeight) {

        for (int i = 0; i < values.length - 1; i++) {
            int x1 = startX + (i * chartWidth / (values.length - 1));
            int x2 = startX + ((i + 1) * chartWidth / (values.length - 1));

            int y1 = startY - (int) ((values[i] / max) * chartHeight);
            int y2 = startY - (int) ((values[i + 1] / max) * chartHeight);

            g.drawLine(x1, y1, x2, y2);
        }
    }

    private double getMaxValue() {

        double max = 1;

        max = checkMax(line1, max);
        max = checkMax(line2, max);
        max = checkMax(line3, max);

        return max;
    }

    private double checkMax(double[] values, double currentMax) {

        if (values == null) {
            return currentMax;
        }

        for (int i = 0; i < values.length; i++) {
            if (values[i] > currentMax) {
                currentMax = values[i];
            }
        }

        return currentMax;
    }

    private String formatMoney(double value) {
        if (value >= 1000000) {
            return "$" + String.format("%.1fM", value / 1000000.0);
        }
        if (value >= 1000) {
            return "$" + String.format("%.0fK", value / 1000.0);
        }
        return "$" + String.format("%.0f", value);
    }
}
