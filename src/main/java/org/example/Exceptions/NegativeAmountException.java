package org.example.Exceptions;

public class NegativeAmountException extends Exception {

    public NegativeAmountException(String errorMsg){
        super(errorMsg);
    }

}
