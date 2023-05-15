package com.example.Student.exception;

public class RecordNotFoundException extends RuntimeException {
   public void RecordNotFoundException(){
        super.getMessage();
    }

    public String RecordNotFoundException(String message){
        return message;
    }


}
