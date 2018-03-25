package com.jswale.game.mastermind.exception;

public class GuessWrongSizeException extends Exception {

    public GuessWrongSizeException() {
        super("Wrong pin size");
    }
}
