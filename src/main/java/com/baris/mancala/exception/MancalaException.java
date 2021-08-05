package com.baris.mancala.exception;

public abstract class MancalaException extends Exception {
    MancalaException(String message) {
        super("MancalaException: " + message);
    }
}
