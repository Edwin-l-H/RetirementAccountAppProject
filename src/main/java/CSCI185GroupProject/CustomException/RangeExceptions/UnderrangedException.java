package CSCI185GroupProject.CustomException.RangeExceptions;

public class UnderrangedException extends IncorrectRangeException
{
    public UnderrangedException(String arrayName)
    { super("There are not enough columns within the array of arrays representing the " arrayName); }
}
