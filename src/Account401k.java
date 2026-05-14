/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This class calculates estimated 401k savings.
*/

public class Account401k extends Account {

    private static final double BASIC_401K_LIMIT_2026 = 24500;

    private double annualSalary;
    private double contributionPercent;
    private double salaryIncreasePercent;
    private double employerMatchPercent;
    private double matchSalaryLimitPercent;
    private double marginalTaxRate;

    public Account401k(double startingBalance,
                       int currentAge,
                       int retirementAge,
                       double expectedReturnRate,
                       double annualSalary,
                       double contributionPercent,
                       double salaryIncreasePercent,
                       double employerMatchPercent,
                       double matchSalaryLimitPercent,
                       double marginalTaxRate) {

        super(startingBalance, currentAge, retirementAge, expectedReturnRate);

        this.annualSalary = annualSalary;
        this.contributionPercent = contributionPercent;
        this.salaryIncreasePercent = salaryIncreasePercent;
        this.employerMatchPercent = employerMatchPercent;
        this.matchSalaryLimitPercent = matchSalaryLimitPercent;
        this.marginalTaxRate = marginalTaxRate;
    }

    //calculate() and calculateTraditional() are the exact same functions
    public RetirementResult calculate() throws InvalidInputException {
        return calculateTraditional();
    }

    //calculateTradtional() tells you what you would have in your account if you went for a traditional 401k Account
    public RetirementResult calculateTraditional() throws InvalidInputException {
        return calculatePlan("Traditional 401k Estimate", false);
    }

    //calculateRoth() tells you what you would have in your account if you went for a Roth 401k Account
    public RetirementResult calculateRoth() throws InvalidInputException {
        return calculatePlan("Roth 401k Estimate", true);
    }

    /*
    It appears that this function calculates what your 401k Account would have year-by-year, 
    showing both the amount you contributed, the amount in the 401k Account with employer match, 
    and the amount in the 401k Account without employer match
    */
    private RetirementResult calculatePlan(String planName, boolean rothPlan) throws InvalidInputException {

        checkBasicInputs();

        if (annualSalary < 0) {
            throw new InvalidInputException("Your annual salary cannot be negative, that would mean you volunteering at that point");
        }

        if (contributionPercent < 0 || salaryIncreasePercent < 0 || employerMatchPercent < 0 || matchSalaryLimitPercent < 0 || marginalTaxRate < 0) {
            throw new InvalidInputException("Percent values cannot be negative.");
        }

        if (contributionPercent > 100 || employerMatchPercent > 100 || matchSalaryLimitPercent > 100 || marginalTaxRate > 100) {
            throw new InvalidInputException("Your in college correct? You know percent values should not be over 100.");
        }

        int years = getYearsUntilRetirement();

        double balanceWithMatch = getStartingBalance();
        double balanceWithoutMatch = getStartingBalance();
        double salary = annualSalary;
        double totalEmployee = 0;
        double totalEmployer = 0;
        double totalTaxPaid = 0;

        double[] balances = new double[years + 1];
        double[] balancesWithoutMatch = new double[years + 1];
        double[] employeeContributions = new double[years + 1];

        String text = "";

        balances[0] = balanceWithMatch;
        balancesWithoutMatch[0] = balanceWithoutMatch;
        employeeContributions[0] = 0;

        text += "Age " + getCurrentAge() + ": $" + String.format("%.2f", balanceWithMatch) + "\n";

        for (int i = 1; i <= years; i++) {

            int age = getCurrentAge() + i;

            double employeeContribution = salary * (contributionPercent / 100.0);

            if (employeeContribution > BASIC_401K_LIMIT_2026) {
                employeeContribution = BASIC_401K_LIMIT_2026;
            }

            double matchablePercent = Math.min(contributionPercent, matchSalaryLimitPercent);

            double employerContribution = salary *
                    (matchablePercent / 100.0) *
                    (employerMatchPercent / 100.0);

            double taxPaid = 0;

            if (rothPlan) {
                taxPaid = employeeContribution * (marginalTaxRate / 100.0);
            }

            totalEmployee = totalEmployee + employeeContribution;
            totalEmployer = totalEmployer + employerContribution;
            totalTaxPaid = totalTaxPaid + taxPaid;

            balanceWithMatch = balanceWithMatch + employeeContribution + employerContribution;
            balanceWithoutMatch = balanceWithoutMatch + employeeContribution;

            balanceWithMatch = balanceWithMatch * (1 + getExpectedReturnRate() / 100.0);
            balanceWithoutMatch = balanceWithoutMatch * (1 + getExpectedReturnRate() / 100.0);

            salary = salary * (1 + salaryIncreasePercent / 100.0);

            balances[i] = balanceWithMatch;
            balancesWithoutMatch[i] = balanceWithoutMatch;
            employeeContributions[i] = totalEmployee;

            text += "Age " + age + ": $" + String.format("%.2f", balanceWithMatch) + "\n";
        }

        return new RetirementResult(planName,
                balanceWithMatch,
                totalEmployee,
                totalEmployer,
                totalTaxPaid,
                text,
                balances,
                balancesWithoutMatch,
                employeeContributions);
    }
}
