package com.jamesgomez.minesweeper;

import java.awt.*;
import java.awt.image.BufferedImage;

/** The fundamental game component of the <i>Minesweeper</i> game */
public class Cell implements Drawable {

    /** The width of a {@link Cell} in pixels */
    public static final int WIDTH = 16;
    /** The height of a {@link Cell} in pixels */
    public static final int HEIGHT = 16;

    private final boolean mined;
    private boolean marked, covered, exploded, incorrectlyMarked;
    private int row, column, adjCount;

    public Cell(boolean isMined) {
        this.column = 0;
        this.row = 0;
        mined = isMined;
        marked = false;
        covered = true;
        exploded = false;
        incorrectlyMarked = false;
        adjCount = 0;
    }

    public Cell(int column, int row, boolean isMined) {
        this.column = column;
        this.row = row;
        mined = isMined;
        marked = false;
        covered = true;
        incorrectlyMarked = false;
        adjCount = 0;
    }

    /** @return true if this Cell contains a mine, false otherwise */
    public boolean isMined() {
        return mined;
    }

    /**
     * @return true if this Cell contains is currently marked by the player,
     *         false otherwise
     */
    public boolean isMarked() {
        return marked;
    }

    /** @return true if this Cell is currently covered, false otherwise */
    public boolean isCovered() {
        return covered;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public void setIncorrectlyMarked(boolean incorrectlyMarked){
        if (marked)
            this.incorrectlyMarked = incorrectlyMarked;
    }

    /** The column of this Cell within the game board */
    public int getColumn() {
        return column;
    }

    /** The row of this Cell within the game board */
    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /** @return the number of mines adjacent to this cell */
    public int getAdjCount() {
        return adjCount;
    }

    public void setAdjCount(int adjCount) {
        this.adjCount = adjCount;
    }

    @Override
    public void draw(Graphics g) {
        if (covered) {
            if (incorrectlyMarked){
                g.drawImage(ImageManager.MIS_MARKED, WIDTH * column, HEIGHT * row, null);
            }
            else if (marked && Game.getInstance().getGameBoard().getNumMarkedMines() >= 0)
                g.drawImage(ImageManager.MARKED, WIDTH * column, HEIGHT * row, null);
            else
                g.drawImage(ImageManager.COVERED, WIDTH * column, HEIGHT * row,
                        null);
        }
        else {
            if (mined) {
                if (exploded)
                    g.drawImage(ImageManager.BOMB_DEATH, WIDTH * column,
                            HEIGHT * row, null);
                else
                    g.drawImage(ImageManager.BOMB_REVEALED, WIDTH * column,
                            HEIGHT * row, null);
            }
            else
                drawAdjacencyCount(g);
        }
    }

    /** Uncovers and reveals this Cell. */
    public void uncover() {
        if (!marked)
            covered = false;
    }

    /**
     * Marks this cell. Cells cannot be uncovered while marked. If this Cell is
     * already uncovered, this method call will be ignored.
     */
    public void mark() {
        if (covered)
            marked = !marked;
    }

    /** Draws this Cell's adjacent mine count. */
    private void drawAdjacencyCount(Graphics g) {
        BufferedImage image = null;

        if (!marked){
            switch (adjCount){
                case 0: image = ImageManager.OPEN_0; break;
                case 1: image = ImageManager.OPEN_1; break;
                case 2: image = ImageManager.OPEN_2; break;
                case 3: image = ImageManager.OPEN_3; break;
                case 4: image = ImageManager.OPEN_4; break;
                case 5: image = ImageManager.OPEN_5; break;
                case 6: image = ImageManager.OPEN_6; break;
                case 7: image = ImageManager.OPEN_7; break;
                case 8: image = ImageManager.OPEN_8; break;
                default: image = ImageManager.OPEN_0;
            }
        }

        g.drawImage(image, WIDTH * column, HEIGHT * row, null);
    }

}
