package com.jswale.game.mastermind.exception;

public class GuessWrongColorException extends Exception {

    public GuessWrongColorException(Character[] colors) {
        super("Wrong color detected");
    }
}
