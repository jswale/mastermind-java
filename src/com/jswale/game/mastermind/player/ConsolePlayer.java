package com.jswale.game.mastermind.player;

import com.jswale.game.mastermind.core.Mastermind;
import com.jswale.game.mastermind.core.PlayerGuess;
import com.jswale.game.mastermind.core.Rules;
import com.jswale.game.mastermind.exception.GuessGameIsOverException;
import com.jswale.game.mastermind.exception.GuessWrongColorException;
import com.jswale.game.mastermind.exception.GuessWrongSizeException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsolePlayer {

    private Scanner sc = new Scanner(System.in);

    private Mastermind mastermind;

    private String repeatString(String s, int count) {
        return Collections.nCopies(count, s).stream().collect(Collectors.joining(""));
    }

    private String charArrayToString(Character[] chars) {
        return Arrays.stream(chars)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    public ConsolePlayer() {
        this.mastermind = new Mastermind();
    }

    private void displayBoard() {
        Rules rules = this.mastermind.getRules();
        int maxTries = rules.getMaxTries();
        int noPins = rules.getNoPins();

        // Display rules
        if(0 == mastermind.getGuesses().size()) {
            System.out.println("Game rules :");
            System.out.println("* board size :" + rules.getNoPins());
            System.out.println("* tries :" + rules.getMaxTries());
            System.out.println("* colors :" + this.charArrayToString(mastermind.getRules().getColors()));
            System.out.println("");
            System.out.println("Good luck :)");
            System.out.println("");
        }

        int displayTriesLength = Integer.toString(maxTries, 10).length();
        int boardDisplayLength = noPins*2+displayTriesLength*2+7;
        String emptyLine = "|" + this.repeatString("-", boardDisplayLength)  + "|";

        System.out.println(emptyLine);

        int noTry = 0;
        // Display tries
        for(PlayerGuess playerGuess : mastermind.getGuesses()) {
            System.out.printf("|%s| %-" + noPins + "s | %" + displayTriesLength + "d/%" + displayTriesLength + "d |%n", this.charArrayToString(playerGuess.getColors()), this.repeatString("#", playerGuess.getNoWelledPlaced()) + this.repeatString("O", playerGuess.getNoGoodColors()), ++noTry, maxTries);
        }
        // Empty line
        if(noTry < maxTries) {
            System.out.printf("|%s| %-" + noPins + "s | %" + displayTriesLength + "d/%" + displayTriesLength + "d |%n", this.repeatString(".", noPins), this.repeatString(".", noPins), (noTry + 1), maxTries);
        }

        System.out.println(emptyLine);

        switch (mastermind.getState()) {
            case GAME_OVER:
                System.out.println("Sorry, you lose :/ The solution was " + this.charArrayToString(mastermind.getSolution()));
                break;
            case VICTORY:
                System.out.println("Victory ! You win in " + mastermind.getGuesses().size() + " rounds !");
                break;
            case PLAYING:
            default:
        }
    }


    public static void main(String[] args) {
        new ConsolePlayer().play();
    }

    public void play() {
        mastermind.newGame();

        displayBoard();

        while (mastermind.getState().equals(Mastermind.State.PLAYING)) {
            System.out.print("\nPlayer > ");
            try {
                mastermind.guess(sc.nextLine().toUpperCase());
                displayBoard();
            } catch (GuessWrongColorException guessWrongColorException) {
                System.out.println("Please enter valid colors, only " + this.charArrayToString(mastermind.getRules().getColors()));
            } catch (GuessWrongSizeException guessWrongSizeException) {
                System.out.println("Please enter the right number of pins, " + mastermind.getRules().getNoPins() + " required");
            } catch (GuessGameIsOverException gameIsOverException) {
                System.out.println("Game is over.");
            }
        }
    }
}
