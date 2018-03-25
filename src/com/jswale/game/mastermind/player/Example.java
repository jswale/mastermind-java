package com.jswale.game.mastermind.player;

import com.jswale.game.mastermind.core.Mastermind;
import com.jswale.game.mastermind.exception.GuessGameIsOverException;
import com.jswale.game.mastermind.exception.GuessWrongColorException;
import com.jswale.game.mastermind.exception.GuessWrongSizeException;

public class Example {

    private Mastermind mastermind;

    public Example() {
        this.mastermind = new Mastermind();
    }

    public void play() {
        this.mastermind.newGame();
        this.mastermind.setSolution(new Character[]{'R', 'O', 'O', 'J'});

        try {
            this.mastermind.guess("NBJV");
            this.mastermind.guess("JJOR");
            this.mastermind.guess("RJRO");
            this.mastermind.guess("ORJO");
            this.mastermind.guess("ROOJ");

        } catch (GuessWrongColorException guessWrongColorException) {
            guessWrongColorException.printStackTrace();
        } catch (GuessWrongSizeException guessWrongSizeException) {
            guessWrongSizeException.printStackTrace();
        } catch (GuessGameIsOverException gameIsOverException) {
            gameIsOverException.printStackTrace();
        }

        System.out.println(this.mastermind.getState());

    }

    public static void main(String[] args) {
        new Example().play();
    }
}
