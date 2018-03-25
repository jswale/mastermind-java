package com.jswale.game.mastermind.core;

import com.jswale.game.mastermind.exception.GuessGameIsOverException;
import com.jswale.game.mastermind.exception.GuessWrongColorException;
import com.jswale.game.mastermind.exception.GuessWrongSizeException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Character.*;

public class Mastermind {

    private final Rules rules;
    private Character[] solution;
    private State state;
    private final LinkedList<PlayerGuess> guesses;

    public Mastermind() {
        this.rules = new Rules();
        this.state = State.PLAYING;
        this.guesses = new LinkedList<>();
    }

    /**
     * Force the game solution.
     *
     * @param solution the game solution
     */
    public void setSolution(Character[] solution) {
        this.solution = solution;
    }

    /**
     * @return the game rules
     */
    public Rules getRules() {
        return rules;
    }

    /**
     * @return the game solution
     */
    public Character[] getSolution() {
        return solution;
    }

    /**
     * @return the game state
     */
    public State getState() {
        return state;
    }

    /**
     * @return the player guesses
     */
    public LinkedList<PlayerGuess> getGuesses() {
        return guesses;
    }

    public void newGame() {
        this.guesses.clear();
        this.state = State.PLAYING;
        this.solution = generate();
    }

    /**
     * Generate a valid answer of the game
     * @return valid answer
     */
    public Character[] generate() {
        return new Random().ints(0, this.rules.getColors().length).mapToObj((i) -> this.rules.getColors()[i]).limit(this.rules.getNoPins()).toArray(Character[]::new);
    }

    /**
     * Analyze the user input
     *
     * @param guess the user input
     * @throws GuessWrongColorException if the input contains unauthorized colors
     * @throws GuessWrongSizeException if input length missmatch board pin length
     * @throws GuessGameIsOverException if the player tries to play on an ended game
     */
    public void guess(String guess) throws GuessWrongColorException, GuessWrongSizeException, GuessGameIsOverException {
        if (!this.state.equals(State.PLAYING)) {
            throw new GuessGameIsOverException();
        }
        checkInput(guess);

        // Let's analyse
        int noPins = this.rules.getNoPins();

        int noWelledPlaced = 0;
        int noGoodColor = 0;
        boolean[] matrice = new boolean[noPins];
        List<Character> solutionAsList = Arrays.asList(this.solution);

        // Welled placed elements
        for (int i = 0; i < noPins; i++) {
            Character pin = guess.charAt(i);
            if (solutionAsList.get(i).equals(pin)) {
                noWelledPlaced++;
                matrice[i] = true;
            } else {
                matrice[i] = false;
            }
        }

        // Good color but wrong place
        for (int i = 0; i < noPins; i++) {
            Character pin = guess.charAt(i);
            // Only look at not welled place elements
            if (!solutionAsList.get(i).equals(pin)) {
                // Search for other position
                for (int j = 0; j < noPins; j++) {
                    // Good color but not already tagged
                    if (!matrice[j] && solutionAsList.get(j).equals(pin)) {
                        noGoodColor++;
                        matrice[j] = true;
                        break; // Stop looking further
                    }
                }
            }
        }

        // Saving
        PlayerGuess playerGuess = new PlayerGuess(guess.chars().mapToObj(i -> valueOf((char) i)).toArray(Character[]::new), noWelledPlaced, noGoodColor);
        guesses.add(playerGuess);

        // Look at the game
        if (noWelledPlaced == noPins) {
            this.state = State.VICTORY;
        }
        if (this.guesses.size() == this.rules.getMaxTries()) {
            this.state = State.GAME_OVER;
        }
    }

    /**
     * Check if the user input is valid
     *
     * @param guess the user input
     * @throws GuessWrongColorException if the input contains unauthorized colors
     * @throws GuessWrongSizeException if input length missmatch board pin length
     */
    private void checkInput(String guess) throws GuessWrongSizeException, GuessWrongColorException {
        if (guess.length() != this.rules.getNoPins()) {
            throw new GuessWrongSizeException();
        }

        List<Character> colorsAsList = Arrays.asList(this.rules.getColors());
        if (guess.chars().mapToObj(i -> (char) i).anyMatch(c -> !colorsAsList.contains(c))) {
            throw new GuessWrongColorException();
        }
    }

    /**
     * Game available states
     */
    public enum State {
        PLAYING,
        GAME_OVER,
        VICTORY
    }
}
