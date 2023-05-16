package com.example.Student.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String s) {
    }

    public RecordNotFoundException() {
        super.getMessage();
    }

}
