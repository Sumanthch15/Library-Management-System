package com.example.library.exception;

public class BookIdAlreadyExistsException extends Exception{
    public BookIdAlreadyExistsException(String message){
        super(message);
    }
}
