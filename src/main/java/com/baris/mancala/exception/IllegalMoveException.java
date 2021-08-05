package com.baris.mancala.exception;

public class IllegalMoveException extends MancalaException {
    public IllegalMoveException(String message) {
        super("Illegal move: " + message);
    }
}
