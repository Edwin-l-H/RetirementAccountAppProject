/*
    Project Title: Group 6 Final Project
    Course: CSCI 185
    Contributors: Benjamin Capers,Dylan Louis,Tao Li,Edwin Huang
    Last Updated: 2026

    This interface gives retirement account classes a shared calculate method.
*/

public interface RetirementAccount {

    RetirementResult calculate() throws InvalidInputException;
}
