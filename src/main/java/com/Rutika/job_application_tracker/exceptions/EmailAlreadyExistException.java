package com.Rutika.job_application_tracker.exceptions;

public class EmailAlreadyExistException extends RuntimeException
{
    public EmailAlreadyExistException(String msg)
    {
        super(msg);
    }
}
