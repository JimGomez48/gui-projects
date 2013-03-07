package com.jamesgomez.minesweeper;

import java.awt.*;

public class Cell implements IDrawable {

    public enum CellState {COVERED, UNCOVERED, MARKED}

    public static final int PIXEL_SIZE = 16;
    private CellState state;
    private boolean mined;
    private int x, y;

    public Cell(int x, int y, boolean isMined) {
        this.x = x;
        this.y = y;
        state = CellState.COVERED;
        mined = isMined;
    }

    public CellState getState() {
        return state;
    }

    public boolean isMined() {
        return mined;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void draw(Graphics g) {
        switch (state) {
            case COVERED:
                g.drawImage(ImageManager.COVERED, PIXEL_SIZE * x, PIXEL_SIZE * y,
                        null);
                break;
            case UNCOVERED:
                if (mined)
                    g.drawImage(ImageManager.BOMB_DEATH, PIXEL_SIZE * x,
                            PIXEL_SIZE * y, null);
                else
                    drawAdjacencyCount(g);
                break;
            case MARKED:
                g.drawImage(ImageManager.MARKED, PIXEL_SIZE * x, PIXEL_SIZE * y,
                        null);
                break;
        }
    }

    //TODO implement cell drawings
    public void cover() {
        state = CellState.COVERED;
    }

    public void uncover() {
        state = CellState.UNCOVERED;
    }


    public void mark() {
        state = CellState.MARKED;
    }

    private void drawAdjacencyCount(Graphics g) {
        g.drawImage(ImageManager.OPEN_1, PIXEL_SIZE * x, PIXEL_SIZE * y, null);
    }

}
