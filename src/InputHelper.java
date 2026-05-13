/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This class helps convert text field input into numbers.
*/

import javax.swing.JTextField;

public class InputHelper {

    public static double getDouble(JTextField field, String fieldName) throws InvalidInputException {

        try {
            return Double.parseDouble(field.getText());
        }
        catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a number.");
        }
    }

    public static int getInt(JTextField field, String fieldName) throws InvalidInputException {

        try {
            return Integer.parseInt(field.getText());
        }
        catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a whole number.");
        }
    }
}
