/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This is the parent class for retirement accounts.
*/

public abstract class Account implements RetirementAccount {

    private double startingBalance;
    private int currentAge;
    private int retirementAge;
    private double expectedReturnRate;
    //In this case, expectedReturnRate is the percentage of which the account's value is expected to increase by

    public Account(double startingBalance, int currentAge, int retirementAge, double expectedReturnRate) {
        this.startingBalance = startingBalance;
        this.currentAge = currentAge;
        this.retirementAge = retirementAge;
        this.expectedReturnRate = expectedReturnRate;
    }

    public double getStartingBalance() {
        return startingBalance;
    }

    public int getCurrentAge() {
        return currentAge;
    }

    public int getRetirementAge() {
        return retirementAge;
    }

    public double getExpectedReturnRate() {
        return expectedReturnRate;
    }

    public int getYearsUntilRetirement() {
        return retirementAge - currentAge;
    }

    public void checkBasicInputs() throws InvalidInputException {
        if (startingBalance < 0) {
            throw new InvalidInputException("You need to re-evaluate your current situation if your starting balance is negative.");
        }

        if (currentAge < 0 || retirementAge < 0) {
            throw new InvalidInputException("Its impossible for your ages to be be negative.");
        }

        if (retirementAge <= currentAge) {
            throw new InvalidInputException("Make it make sense, your retirement age must be greater than current age.");
        }

        if (expectedReturnRate < 0) {
            throw new InvalidInputException("Holy fish sticks, your expected return rate cannot be negative.");
        }
    }
}
