package com.jswale.game.mastermind.exception;

public class GuessWrongSizeException extends Exception {

    public GuessWrongSizeException(int allowedSize) {
        super("Only " + allowedSize + " colors allowed");
    }
}
