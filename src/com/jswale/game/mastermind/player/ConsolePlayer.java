package com.jswale.game.mastermind.player;

import com.jswale.game.mastermind.core.Mastermind;

import java.util.Scanner;

public class ConsolePlayer extends AbstractConsolePlayer {


    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new ConsolePlayer().play();
    }

    private void play() {
        mastermind.newGame();

        displayBoard();

        while (mastermind.getState().equals(Mastermind.State.PLAYING)) {
            System.out.print("\nPlayer > ");
            this.guess(sc.nextLine().toUpperCase());
        }
    }
}
