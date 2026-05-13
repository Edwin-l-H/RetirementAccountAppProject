package CSCI185GroupProject.CustomException.RangeExceptions;

public abstract class IncorrectRangeException extends Exception
{
    public IncorrectRangeException()
    { super("The array inside the array of arrays representing something " +
            "either have too much or too little capacity for what it represents.\n " +
            "In other words, the array of arrays representing said thing does not have exactly a certain number of columns"); }

    public IncorrectRangeException(String text)
    { super(text); }
}
