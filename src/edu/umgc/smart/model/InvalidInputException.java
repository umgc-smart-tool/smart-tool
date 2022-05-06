package edu.umgc.smart.model;

public class InvalidInputException extends Exception{
    public InvalidInputException(String error){
        super(error);
    }
}
