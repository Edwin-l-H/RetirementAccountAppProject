package CSCI185GroupProject;

import CSCI185GroupProject.CustomException.InitialBalanceTooManyDecimals;
import CSCI185GroupProject.CustomException.RangeExceptions.IncorrectRangeException;
import CSCI185GroupProject.CustomException.RangeExceptions.OverrangedException;
import CSCI185GroupProject.CustomException.RangeExceptions.UnderrangedException;

public class Calculator401k
{
    /*
    This calculator assumes a number of things,
    but the most important one is that the 401k contributions are monthly

    Also, floating point errors can occur with this,
    as the data types used here all use floating point to store its value
    */
    private double currentBalance;

    private final double intialBalance;
    private double employeeSalary;
    private double employeeContributionPercentage;
    private double capForEmployerMatch;
    private double[] employerMatchPercentage;
    private double[][] marginalTaxRate;
    private int contributionLimit;

    public Calculator401k(double intialBalance,double employeeSalary, double employeeContributionPercentage,
                          double capForEmployerMatch, double[] employerMatchPercentage, double[][] marginalTaxRate,
                          int contributionLimit)
            throws IncorrectRangeException, InitialBalanceTooManyDecimals
    {
        if ((intialBalance * 100) != Math.floor(intialBalance * 100)) throw new InitialBalanceTooManyDecimals();

        if (marginalTaxRate[0].length < 2) throw new UnderrangedException("marginal tax rate");
        else if (marginalTaxRate[0].length > 2) throw new OverrangedException("marginal tax rate");

        this.intialBalance = intialBalance;
        this.employeeSalary = employeeSalary;
        this.employeeContributionPercentage = employeeContributionPercentage;
        this.capForEmployerMatch = capForEmployerMatch;
        this.employerMatchPercentage = employerMatchPercentage;
        this.marginalTaxRate = marginalTaxRate;
        this.contributionLimit = contributionLimit;

        currentBalance = intialBalance;
    }

    public void progressNext()
    {
        double employeeContribution = employeeSalary * 0.01 * employeeContributionPercentage;
        currentBalance += employeeContribution;

    }
}
