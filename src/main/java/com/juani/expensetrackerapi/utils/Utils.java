package com.juani.expensetrackerapi.utils;

import com.juani.expensetrackerapi.entity.ErrorObject;

import java.util.Date;

public class Utils {

    public static ErrorObject generateErrorObject(String message, Integer statusCode){
        ErrorObject errorObject =  new ErrorObject();
        errorObject.setMessage(message);
        errorObject.setStatusCode(statusCode);
        errorObject.setTimestamp(new Date());

        return errorObject;
    }
}
