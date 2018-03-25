package com.jswale.game.mastermind.player;

import com.jswale.game.mastermind.core.Mastermind;
import com.jswale.game.mastermind.exception.GuessGameIsOverException;
import com.jswale.game.mastermind.exception.GuessWrongColorException;
import com.jswale.game.mastermind.exception.GuessWrongSizeException;

public class Example extends AbstractConsolePlayer {

    private void play() {
        this.mastermind.newGame();
        this.mastermind.setSolution(new Character[]{'R', 'O', 'O', 'J'});

        this.guess("NBJV");
        this.guess("JJOR");
        this.guess("RJRO");
        this.guess("ORJO");
        this.guess("ROOJ");

    }

    public static void main(String[] args) {
        new Example().play();
    }
}
