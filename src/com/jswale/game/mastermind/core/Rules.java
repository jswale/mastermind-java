package com.jswale.game.mastermind.core;

public class Rules {

    private Character[] colors;
    private int maxTries;
    private int noPins;

    public Rules() {
        this(new Character[]{'R', 'J', 'B', 'O', 'V', 'N'}, 10, 4);
    }

    public Rules(Character[] colors, int maxTries, int noPins) {
        this.colors = colors;
        this.maxTries = maxTries;
        this.noPins = noPins;
    }

    /**
     * @return list of the game colors
     */
    public Character[] getColors() {
        return colors;
    }

    /**
     * @param colors the colors for the game (default RJBOVN)
     */
    public void setColors(Character[] colors) {
        this.colors = colors;
    }

    /**
     * @return number of tries before game over
     */
    public int getMaxTries() {
        return maxTries;
    }

    /**
     * @param maxTries the number of tries for the player (default 10)
     */
    public void setMaxTries(int maxTries) {
        this.maxTries = maxTries;
    }

    /**
     * @return board length
     */
    public int getNoPins() {
        return noPins;
    }

    /**
     * @param noPins the board length (default 4)
     */
    public void setNoPins(int noPins) {
        this.noPins = noPins;
    }
}
