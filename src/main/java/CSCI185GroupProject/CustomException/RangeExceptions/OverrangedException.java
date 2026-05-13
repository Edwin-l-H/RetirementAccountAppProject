package CSCI185GroupProject.CustomException.RangeExceptions;

public class OverrangedException extends IncorrectRangeException
{
    public OverrangedException(String arrayName)
    { super("There are too many columns within the array of arrays representing " + arrayName); }
}
