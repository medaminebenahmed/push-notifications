package com.proxym.notification.exceptions;

/**
 * Created by med-amine.ben-ahmed on 16/07/2019.
 */
public class FirebaseException extends RuntimeException {

    private String errorMsg;

    public FirebaseException(String errorMsg, Throwable exception) {
        super(exception);
        this.errorMsg = errorMsg;
    }


}
