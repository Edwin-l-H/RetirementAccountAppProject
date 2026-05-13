/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This class stores the results from a retirement calculation.
*/

public class RetirementResult {

    private String planName;
    private double endingBalance;
    private double totalEmployeeContribution;
    private double totalEmployerContribution;
    private double totalEstimatedTaxPaid;
    private String yearByYearText;
    private double[] balances;
    private double[] balancesWithoutEmployerMatch;
    private double[] employeeContributions;

    public RetirementResult(String planName,
                            double endingBalance,
                            double totalEmployeeContribution,
                            double totalEmployerContribution,
                            double totalEstimatedTaxPaid,
                            String yearByYearText,
                            double[] balances,
                            double[] balancesWithoutEmployerMatch,
                            double[] employeeContributions) {

        this.planName = planName;
        this.endingBalance = endingBalance;
        this.totalEmployeeContribution = totalEmployeeContribution;
        this.totalEmployerContribution = totalEmployerContribution;
        this.totalEstimatedTaxPaid = totalEstimatedTaxPaid;
        this.yearByYearText = yearByYearText;
        this.balances = balances;
        this.balancesWithoutEmployerMatch = balancesWithoutEmployerMatch;
        this.employeeContributions = employeeContributions;
    }

    public String getPlanName() {
        return planName;
    }

    public double getEndingBalance() {
        return endingBalance;
    }

    public double getTotalEmployeeContribution() {
        return totalEmployeeContribution;
    }

    public double getTotalEmployerContribution() {
        return totalEmployerContribution;
    }

    public double getTotalEstimatedTaxPaid() {
        return totalEstimatedTaxPaid;
    }

    public String getYearByYearText() {
        return yearByYearText;
    }

    public double[] getBalances() {
        return balances;
    }

    public double[] getBalancesWithoutEmployerMatch() {
        return balancesWithoutEmployerMatch;
    }

    public double[] getEmployeeContributions() {
        return employeeContributions;
    }

    public String toString() {
        return planName + "\n" +
                "Ending Balance: $" + String.format("%.2f", endingBalance) + "\n" +
                "Total Employee Contributions: $" + String.format("%.2f", totalEmployeeContribution) + "\n" +
                "Total Employer Contributions: $" + String.format("%.2f", totalEmployerContribution) + "\n" +
                "Estimated Tax Paid Now: $" + String.format("%.2f", totalEstimatedTaxPaid) + "\n\n" +
                yearByYearText;
    }
}
