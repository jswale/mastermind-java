package com.jswale.game.mastermind.core;

import java.util.LinkedList;

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

    public void guess(String guess) {
    }

    public enum State {
        PLAYING,
        GAME_OVER,
        VICTORY
    }
}
