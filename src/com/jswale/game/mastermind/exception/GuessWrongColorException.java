package com.jswale.game.mastermind.exception;

public class GuessWrongColorException extends Exception {

    public GuessWrongColorException() {
        super("Wrong color detected");
    }
}
