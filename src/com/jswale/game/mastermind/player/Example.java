package com.jswale.game.mastermind.player;

import com.jswale.game.mastermind.core.Mastermind;

public class Example {

    private Mastermind mastermind;

    public Example() {
        this.mastermind = new Mastermind();
    }

    public void play() {
        this.mastermind.newGame();
        this.mastermind.setSolution(new Character[]{'R', 'O', 'O', 'J'});
        this.mastermind.guess("NBJV");
        this.mastermind.guess("JJOR");
        this.mastermind.guess("RJRO");
        this.mastermind.guess("ORJO");
        this.mastermind.guess("ROOJ");
    }

    public static void main(String[] args) {
        new Example().play();
    }
}
