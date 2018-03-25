package com.jswale.game.mastermind.player;

import com.jswale.game.mastermind.core.Mastermind;

public class FakePlayer extends AbstractConsolePlayer {

    public static void main(String[] args) {
        new FakePlayer().play();
    }

    private void play() {
        mastermind.getRules().setMaxTries(12);
        mastermind.getRules().setNoPins(3);
        mastermind.newGame();
        this.displayBoard();
        while (mastermind.getState().equals(Mastermind.State.PLAYING)) {
            this.guess(this.charArrayToString(mastermind.generate()));
        }
    }
}
