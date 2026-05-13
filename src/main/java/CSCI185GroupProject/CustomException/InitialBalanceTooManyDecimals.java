package CSCI185GroupProject.CustomException;

public class InitialBalanceTooManyDecimals extends Exception
{
    public InitialBalanceTooManyDecimals()
    { super("The initial balance has too many decimal places in it"); }
}
