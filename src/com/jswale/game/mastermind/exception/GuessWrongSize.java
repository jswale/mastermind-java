package com.jswale.game.mastermind.exception;

public class GuessWrongSize extends Exception {

    public GuessWrongSize(int allowedSize) {
        super("Only " + allowedSize + " colors allowed");
    }
}
