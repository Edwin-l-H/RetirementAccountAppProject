/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This class calculates estimated IRA savings.
*/

public class IRAAccount extends Account {

    private static final double IRA_LIMIT_2026 = 7500;

    private double annualContribution;
    private double marginalTaxRate;

    public IRAAccount(double startingBalance,
                      int currentAge,
                      int retirementAge,
                      double expectedReturnRate,
                      double annualContribution,
                      double marginalTaxRate) {

        super(startingBalance, currentAge, retirementAge, expectedReturnRate);

        this.annualContribution = annualContribution;
        this.marginalTaxRate = marginalTaxRate;
    }

    public RetirementResult calculate() throws InvalidInputException {
        return calculateTraditional();
    }

    public RetirementResult calculateTraditional() throws InvalidInputException {
        return calculatePlan("Traditional IRA Estimate", false);
    }

    public RetirementResult calculateRoth() throws InvalidInputException {
        return calculatePlan("Roth IRA Estimate", true);
    }

    private RetirementResult calculatePlan(String planName, boolean rothPlan) throws InvalidInputException {

        checkBasicInputs();

        if (annualContribution < 0) {
            throw new InvalidInputException("Come on the annual contribution cannot be negative.");
        }

        if (annualContribution > IRA_LIMIT_2026) {
            throw new InvalidInputException("You know that the IRA annual contribution cannot be over the 2026 limit of $7,500.");
        }

        if (marginalTaxRate < 0) {
            throw new InvalidInputException("Negative marginal tax rate = No Go.");
        }

        if (marginalTaxRate > 100) {
            throw new InvalidInputException("Marginal tax rate should not be over 100.");
        }

        int years = getYearsUntilRetirement();

        double balance = getStartingBalance();
        double totalEmployee = 0;
        double totalTaxPaid = 0;

        double[] balances = new double[years + 1];
        double[] employeeContributions = new double[years + 1];

        String text = "";

        balances[0] = balance;
        employeeContributions[0] = 0;

        text += "Age " + getCurrentAge() + ": $" + String.format("%.2f", balance) + "\n";

        for (int i = 1; i <= years; i++) {

            int age = getCurrentAge() + i;

            double taxPaid = 0;

            if (rothPlan) {
                taxPaid = annualContribution * (marginalTaxRate / 100.0);
            }

            totalTaxPaid = totalTaxPaid + taxPaid;
            totalEmployee = totalEmployee + annualContribution;

            balance = balance + annualContribution;
            balance = balance * (1 + getExpectedReturnRate() / 100.0);

            balances[i] = balance;
            employeeContributions[i] = totalEmployee;

            text += "Age " + age + ": $" + String.format("%.2f", balance) + "\n";
        }

        return new RetirementResult(planName,
                balance,
                totalEmployee,
                0,
                totalTaxPaid,
                text,
                balances,
                null,
                employeeContributions);
    }
}
