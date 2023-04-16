package ru.vsu.csd.datatransferservice.exception;

public class IncorrectArrayLengthException extends RuntimeException {
    public IncorrectArrayLengthException() {
        super("Incorrect number of elements in the array");
    }
}