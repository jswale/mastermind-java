package com.jswale.game.mastermind.core;

import com.jswale.game.mastermind.exception.GuessWrongColor;
import com.jswale.game.mastermind.exception.GuessWrongSize;

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

    public void guess(String guess) throws GuessWrongColor, GuessWrongSize {
        checkInput(guess);
    }

    /**
     * Check if the user input is valid
     *
     * @param guess the user input
     * @throws GuessWrongSize
     * @throws GuessWrongColor
     */
    private void checkInput(String guess) throws GuessWrongSize, GuessWrongColor {
        if(guess.length() != this.rules.getNoPins()) {
            throw new GuessWrongSize(this.rules.getNoPins());
        }

        List<Character> colorsAsList = Arrays.asList(this.rules.getColors());
        if(guess.chars().mapToObj(i->(char)i).anyMatch(c->!colorsAsList.contains(c))) {
            throw new GuessWrongColor(this.rules.getColors());
        }
    }

    public enum State {
        PLAYING,
        GAME_OVER,
        VICTORY
    }
}
