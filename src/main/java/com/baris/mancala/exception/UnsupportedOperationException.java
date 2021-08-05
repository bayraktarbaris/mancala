package com.baris.mancala.exception;

public class UnsupportedOperationException extends MancalaException {
    public UnsupportedOperationException(String message) {
        super("Unsupported operation: " + message);
    }
}
