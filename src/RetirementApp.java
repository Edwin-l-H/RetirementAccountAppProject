/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This is our main GUI class for the funny retirement calculator.
*/

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RetirementApp {

    private JFrame frame;

    private JTextField salaryField;
    private JTextField contributionPercentField;
    private JTextField salaryIncreaseField;
    private JTextField currentAgeField;
    private JTextField retirementAgeField;
    private JTextField returnRateField;
    private JTextField balanceField;
    private JTextField employerMatchField;
    private JTextField matchLimitField;
    private JTextField taxRateField;

    private JTextArea outputArea401k;
    private SimpleChartPanel traditional401kChart;
    private SimpleChartPanel roth401kChart;

    private JTextArea outputAreaIRA;
    private SimpleChartPanel traditionalIRAChart;
    private SimpleChartPanel rothIRAChart;

    private RetirementResult last401kTraditional;
    private RetirementResult last401kRoth;
    private RetirementResult lastIRATraditional;
    private RetirementResult lastIRARoth;

    public RetirementApp() {

        frame = new JFrame("Funny Retirement Account Calculator");
        frame.setSize(1100, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Information", createInfoPanel());
        tabs.add("401k Calculator", create401kPanel());
        tabs.add("IRA Calculator", createIRAPanel());

        frame.add(tabs);
        frame.setVisible(true);
    }

    private JPanel createInfoPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);

        info.setText(
                "*****FYI Just So You Are Aware*****\n\n" +

                        "401k Plans\n" +
                        "Traditional 401k: Contributions are usually made before taxes. Taxes are normally paid later when money is withdrawn.\n" +
                        "Roth 401k: Contributions are made after taxes. This calculator shows estimated tax paid now based on the marginal tax rate entered.\n" +
                        "2026 basic employee contribution limit: $24,500. Catch-up contributions are not included in this class project version.\n" +
                        "Employer match: This depends on the employer plan. The calculator estimates the match using the match percent and the salary limit percent entered by the user.\n" +
                        "Withdrawals: Rules depend on account type, age, and plan rules.\n" +
                        "Required minimum distributions may apply based on IRS rules.\n\n" +

                        "IRA Plans\n" +
                        "Traditional IRA: Contributions may be tax deductible depending on income, filing status, and whether the person is covered by a workplace plan.\n" +
                        "Roth IRA: Contributions are made after taxes. Roth IRA eligibility can be limited based on income.\n" +
                        "2026 IRA contribution limit: $7,500. Catch-up contributions are not included in this class project version.\n" +
                        "Employer match: IRA accounts do not normally have an employer match.\n" +
                        "Withdrawals: Rules depend on account type and age.\n" +
                        "Required minimum distributions generally apply to traditional IRAs, but not Roth IRAs during the original owner's lifetime.\n\n" +

                        "Calculation Assumptions\n" +
                        "Our program uses yearly compounding. Each year it adds the contribution first and then applies the expected return rate.\n" +
                        "For 401k calculations, employee contributions are capped at the 2026 basic limit of $24,500. Huge come up from the past. See link below\n" +
                        "For IRA calculations, annual contributions are capped at the 2026 basic limit of $7,500. See link below\n" +
                        "For Roth calculations, the account balance uses the contribution amount entered, while estimated tax paid now is shown separately.\n" +
                        "This calculator is for this class project only. It does not provide financial, tax, or investment advice whatsoever, we are not accountants.\n" +
                        "YOU HAVE BEEN WARNED.\n\n" +

                        "Websites Used To Formulate This Project\n" +
                        "IRS 2026 Retirement Plan Limits:\n" +
                        "https://www.irs.gov/newsroom/401k-limit-increases-to-24500-for-2026-ira-limit-increases-to-7500\n\n" +

                        "IRS IRA Contribution Limits:\n" +
                        "https://www.irs.gov/retirement-plans/plan-participant-employee/retirement-topics-ira-contribution-limits\n\n" +

                        "IRS Required Minimum Distributions FAQs:\n" +
                        "https://www.irs.gov/retirement-plans/retirement-plan-and-ira-required-minimum-distributions-faqs\n\n" +
                        "FOLLOW US ON FACEBOOK @ ......................., just playing\n" +
                        "Hope you enjoy our app"
        );

        panel.add(new JScrollPane(info), BorderLayout.CENTER);

        return panel;
    }

    private JPanel create401kPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(11, 2));

        salaryField = new JTextField("60000");
        contributionPercentField = new JTextField("5");
        salaryIncreaseField = new JTextField("2");
        currentAgeField = new JTextField("25");
        retirementAgeField = new JTextField("65");
        returnRateField = new JTextField("6");
        balanceField = new JTextField("0");
        employerMatchField = new JTextField("50");
        matchLimitField = new JTextField("6");
        taxRateField = new JTextField("22");

        inputPanel.add(new JLabel("Your Annual Salary:"));
        inputPanel.add(salaryField);

        inputPanel.add(new JLabel("Your Contribution Percentage:"));
        inputPanel.add(contributionPercentField);

        inputPanel.add(new JLabel("If any, Your Salary Increase Percent:"));
        inputPanel.add(salaryIncreaseField);

        inputPanel.add(new JLabel("Current Age, Not how old your gonna be next month:"));
        inputPanel.add(currentAgeField);

        inputPanel.add(new JLabel("Realistic Retirement Age, please:"));
        inputPanel.add(retirementAgeField);

        inputPanel.add(new JLabel("Expected Return Rate:"));
        inputPanel.add(returnRateField);

        inputPanel.add(new JLabel("Your Current 401k Balance:"));
        inputPanel.add(balanceField);

        inputPanel.add(new JLabel("Employer Match Percent:"));
        inputPanel.add(employerMatchField);

        inputPanel.add(new JLabel("Salary Limit Percent for Match:"));
        inputPanel.add(matchLimitField);

        inputPanel.add(new JLabel("Marginal Tax Rate for Roth:"));
        inputPanel.add(taxRateField);

        JButton calculateButton = new JButton("Calculate 401k");
        JButton clearButton = new JButton("Clear");

        inputPanel.add(calculateButton);
        inputPanel.add(clearButton);

        outputArea401k = new JTextArea();
        outputArea401k.setEditable(false);

        traditional401kChart = new SimpleChartPanel("Traditional 401k Estimate");
        roth401kChart = new SimpleChartPanel("Roth 401k Estimate");

        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new GridLayout(1, 2));
        chartPanel.add(traditional401kChart);
        chartPanel.add(roth401kChart);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(outputArea401k));
        centerPanel.add(chartPanel);

        JButton saveButton = new JButton("Save 401k Results");

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);

        calculateButton.addActionListener(e -> calculate401k());
        clearButton.addActionListener(e -> clear401k());
        saveButton.addActionListener(e -> save401kResults());

        return panel;
    }

    private JPanel createIRAPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2));

        JTextField iraBalanceField = new JTextField("0");
        JTextField iraCurrentAgeField = new JTextField("25");
        JTextField iraRetirementAgeField = new JTextField("65");
        JTextField iraReturnRateField = new JTextField("6");
        JTextField iraContributionField = new JTextField("7500");
        JTextField iraTaxRateField = new JTextField("22");
        JCheckBox maxContributionBox = new JCheckBox("Use max IRA contribution allowed be law");

        inputPanel.add(new JLabel("Your Starting Balance:"));
        inputPanel.add(iraBalanceField);

        inputPanel.add(new JLabel("Current Age:"));
        inputPanel.add(iraCurrentAgeField);

        inputPanel.add(new JLabel("Retirement Age:"));
        inputPanel.add(iraRetirementAgeField);

        inputPanel.add(new JLabel("Expected Return Rate:"));
        inputPanel.add(iraReturnRateField);

        inputPanel.add(new JLabel("Annual Contribution:"));
        inputPanel.add(iraContributionField);

        inputPanel.add(new JLabel("Marginal Tax Rate for Roth:"));
        inputPanel.add(iraTaxRateField);

        inputPanel.add(new JLabel("Max Contribution Option:"));
        inputPanel.add(maxContributionBox);

        JButton calculateButton = new JButton("Calculate IRA");
        JButton clearButton = new JButton("Clear");

        inputPanel.add(calculateButton);
        inputPanel.add(clearButton);

        outputAreaIRA = new JTextArea();
        outputAreaIRA.setEditable(false);

        traditionalIRAChart = new SimpleChartPanel("Traditional IRA Estimate, key word ESTIMATE");
        rothIRAChart = new SimpleChartPanel("Roth IRA Estimate");

        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new GridLayout(1, 2));
        chartPanel.add(traditionalIRAChart);
        chartPanel.add(rothIRAChart);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(new JScrollPane(outputAreaIRA));
        centerPanel.add(chartPanel);

        JButton saveButton = new JButton("Save IRA Results");

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);

        calculateButton.addActionListener(e -> {

            try {
                double balance = InputHelper.getDouble(iraBalanceField, "Starting Balance");
                int currentAge = InputHelper.getInt(iraCurrentAgeField, "Current Age");
                int retirementAge = InputHelper.getInt(iraRetirementAgeField, "Retirement Age");
                double returnRate = InputHelper.getDouble(iraReturnRateField, "Return Rate");
                double contribution = InputHelper.getDouble(iraContributionField, "Annual Contribution");
                double taxRate = InputHelper.getDouble(iraTaxRateField, "Tax Rate");

                if (maxContributionBox.isSelected()) {
                    contribution = 7500;
                    iraContributionField.setText("7500");
                }

                IRAAccount account = new IRAAccount(balance, currentAge, retirementAge, returnRate, contribution, taxRate);

                lastIRATraditional = account.calculateTraditional();
                lastIRARoth = account.calculateRoth();

                outputAreaIRA.setText(lastIRATraditional.toString() + "\n\n" + lastIRARoth.toString());

                outputAreaIRA.setCaretPosition(0);

                traditionalIRAChart.setData(lastIRATraditional.getBalances(),
                        null,
                        lastIRATraditional.getEmployeeContributions(),
                        currentAge,
                        "Balance",
                        "",
                        "Employee Contributions");

                rothIRAChart.setData(lastIRARoth.getBalances(),
                        null,
                        lastIRARoth.getEmployeeContributions(),
                        currentAge,
                        "Balance",
                        "",
                        "Employee Contributions");
            }
            catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        clearButton.addActionListener(e -> {
            iraBalanceField.setText("");
            iraCurrentAgeField.setText("");
            iraRetirementAgeField.setText("");
            iraReturnRateField.setText("");
            iraContributionField.setText("");
            iraTaxRateField.setText("");
            maxContributionBox.setSelected(false);
            outputAreaIRA.setText("");
        });

        saveButton.addActionListener(e -> saveIRAResults());

        return panel;
    }

    private void calculate401k() {

        try {
            double salary = InputHelper.getDouble(salaryField, "Annual Salary");
            double contributionPercent = InputHelper.getDouble(contributionPercentField, "Contribution Percent");
            double salaryIncrease = InputHelper.getDouble(salaryIncreaseField, "Salary Increase Percent");
            int currentAge = InputHelper.getInt(currentAgeField, "Current Age");
            int retirementAge = InputHelper.getInt(retirementAgeField, "Retirement Age");
            double returnRate = InputHelper.getDouble(returnRateField, "Return Rate");
            double balance = InputHelper.getDouble(balanceField, "Current Balance");
            double employerMatch = InputHelper.getDouble(employerMatchField, "Employer Match");
            double matchLimit = InputHelper.getDouble(matchLimitField, "Match Limit");
            double taxRate = InputHelper.getDouble(taxRateField, "Tax Rate");

            Account401k account = new Account401k(balance,
                    currentAge,
                    retirementAge,
                    returnRate,
                    salary,
                    contributionPercent,
                    salaryIncrease,
                    employerMatch,
                    matchLimit,
                    taxRate);

            last401kTraditional = account.calculateTraditional();
            last401kRoth = account.calculateRoth();

            outputArea401k.setText(last401kTraditional.toString() + "\n\n" + last401kRoth.toString());

            outputArea401k.setCaretPosition(0);

            traditional401kChart.setData(last401kTraditional.getBalances(),
                    last401kTraditional.getBalancesWithoutEmployerMatch(),
                    last401kTraditional.getEmployeeContributions(),
                    currentAge,
                    "With Match",
                    "Without Match",
                    "Employee Contributions");

            roth401kChart.setData(last401kRoth.getBalances(),
                    last401kRoth.getBalancesWithoutEmployerMatch(),
                    last401kRoth.getEmployeeContributions(),
                    currentAge,
                    "With Match",
                    "Without Match",
                    "Employee Contributions");
        }
        catch (InvalidInputException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    private void clear401k() {
        salaryField.setText("");
        contributionPercentField.setText("");
        salaryIncreaseField.setText("");
        currentAgeField.setText("");
        retirementAgeField.setText("");
        returnRateField.setText("");
        balanceField.setText("");
        employerMatchField.setText("");
        matchLimitField.setText("");
        taxRateField.setText("");
        outputArea401k.setText("");
    }

    private void save401kResults() {

        try {
            FileManager fileManager = new FileManager("401k_results.txt");
            fileManager.saveText(outputArea401k.getText());

            FileManager.saveComponentImage(traditional401kChart, "traditional_401k_chart.png");
            FileManager.saveComponentImage(roth401kChart, "roth_401k_chart.png");

            JOptionPane.showMessageDialog(frame, "401k results and charts saved.");
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Could not save 401k results.");
        }
    }

    private void saveIRAResults() {

        try {
            FileManager fileManager = new FileManager("ira_results.txt");
            fileManager.saveText(outputAreaIRA.getText());

            FileManager.saveComponentImage(traditionalIRAChart, "traditional_ira_chart.png");
            FileManager.saveComponentImage(rothIRAChart, "roth_ira_chart.png");

            JOptionPane.showMessageDialog(frame, "IRA results and charts saved.");
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Could not save IRA results.");
        }
    }

    public static void main(String[] args) {
        new RetirementApp();
    }
}
