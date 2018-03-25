package com.jswale.game.mastermind.exception;

public class GuessWrongColor extends Exception {

    public GuessWrongColor(Character[] colors) {
        super("Wrong color detected");
    }
}
