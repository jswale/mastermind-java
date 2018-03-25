package com.jswale.game.mastermind.core;

import com.jswale.game.mastermind.exception.GuessGameIsOverException;
import com.jswale.game.mastermind.exception.GuessWrongColorException;
import com.jswale.game.mastermind.exception.GuessWrongSizeException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Mastermind {

    private Rules rules;
    private Character[] solution;
    private State state;
    private LinkedList<PlayerGuess> guesses;


    public Mastermind() {
        this.rules = new Rules();
        this.state = State.PLAYING;
        this.guesses = new LinkedList<>();
    }

    public void setSolution(Character[] solution) {
        this.solution = solution;
    }

    public Rules getRules() {
        return rules;
    }

    public Character[] getSolution() {
        return solution;
    }

    public State getState() {
        return state;
    }

    public void newGame() {
        this.guesses.clear();
        this.state=State.PLAYING;
        this.solution = null; //TODO generate solution
    }

    public void guess(String guess) throws GuessWrongColorException, GuessWrongSizeException, GuessGameIsOverException {
        if(!this.state.equals(State.PLAYING)) {
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
        for(int i = 0; i < noPins; i++) {
            Character pin = guess.charAt(i);
            if(solutionAsList.get(i).equals(pin)) {
                noWelledPlaced++;
                matrice[i] = true;
            } else {
                matrice[i] = false;
            }
        }

        // Good color but wrong place
        for(int i = 0; i < noPins; i++) {
            Character pin = guess.charAt(i);
            // Only look at not welled place elements
            if(!solutionAsList.get(i).equals(pin)) {
                // Search for other position
                for(int j = 0; j < noPins; j++) {
                    // Good color but not already tagged
                    if(!matrice[j] && solutionAsList.get(j).equals(pin)) {
                        noGoodColor++;
                        matrice[j]=true;
                        break; // Stop looking further
                    }
                }
            }
        }

        // Saving
        PlayerGuess playerGuess = new PlayerGuess(guess.chars().mapToObj(i -> Character.valueOf((char) i)).toArray(Character[]::new), noWelledPlaced, noGoodColor);
        guesses.add(playerGuess);

        // Look at the game
        if(noWelledPlaced == noPins) {
            this.state = State.VICTORY;
        }
        if(this.guesses.size() == this.rules.getMaxTries()) {
            this.state = State.GAME_OVER;
        }
    }

    /**
     * Check if the user input is valid
     *
     * @param guess the user input
     * @throws GuessWrongSizeException
     * @throws GuessWrongColorException
     */
    private void checkInput(String guess) throws GuessWrongSizeException, GuessWrongColorException {
        if(guess.length() != this.rules.getNoPins()) {
            throw new GuessWrongSizeException(this.rules.getNoPins());
        }

        List<Character> colorsAsList = Arrays.asList(this.rules.getColors());
        if(guess.chars().mapToObj(i->(char)i).anyMatch(c->!colorsAsList.contains(c))) {
            throw new GuessWrongColorException(this.rules.getColors());
        }
    }

    public enum State {
        PLAYING,
        GAME_OVER,
        VICTORY
    }
}
