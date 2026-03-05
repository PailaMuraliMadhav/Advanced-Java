package com.example.BookStoreApplication.exceptions;

public class UserNotFoundException extends  RuntimeException{
    public  UserNotFoundException(String message){
        super(message);
    }
}
