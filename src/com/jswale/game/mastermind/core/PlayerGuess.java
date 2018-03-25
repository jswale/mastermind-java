package com.jswale.game.mastermind.core;

public class PlayerGuess {
    private Character[] colors;
    private int noWelledPlaced;
    private int noGoodColors;

    public PlayerGuess(Character[] colors, int noWelledPlaced, int noGoodColors) {
        this.colors = colors;
        this.noWelledPlaced = noWelledPlaced;
        this.noGoodColors = noGoodColors;
    }

    /**
     * @return colors of the player guess
     */
    public Character[] getColors() {
        return colors;
    }

    /**
     * @return count of welled placed pins
     */
    public int getNoWelledPlaced() {
        return noWelledPlaced;
    }

    /**
     * @return count of pins not welled placed but of the right color
     */
    public int getNoGoodColors() {
        return noGoodColors;
    }
}
