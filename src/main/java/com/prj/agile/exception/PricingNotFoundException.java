package com.prj.agile.exception;

public class PricingNotFoundException extends RuntimeException{
    public PricingNotFoundException(String message){
        super(message);
    }
}
