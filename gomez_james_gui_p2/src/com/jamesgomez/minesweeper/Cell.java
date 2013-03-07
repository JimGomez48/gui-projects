package com.jamesgomez.minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell implements IDrawable, ActionListener {

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
                drawCovered(g);
                break;
            case UNCOVERED:
                if (mined)
                    drawBombDeath(g);
                else
                    drawAdjacencyCount(g);
                break;
            case MARKED:
                drawMarked(g);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO change state on click
    }

    //TODO implement cell drawings
    private void drawCovered(Graphics g) {
        g.drawImage(ImageManager.COVERED, PIXEL_SIZE * x, PIXEL_SIZE * y, null);
    }

    private void drawMarked(Graphics g) {
        g.drawImage(ImageManager.MARKED, PIXEL_SIZE * x, PIXEL_SIZE * y, null);
    }

    private void drawBombDeath(Graphics g) {
        g.drawImage(ImageManager.BOMB_DEATH, PIXEL_SIZE * x, PIXEL_SIZE * y, null);
    }

    private void drawBombRevealed(Graphics g) {
        g.drawImage(ImageManager.BOMB_REVEALED, PIXEL_SIZE * x, PIXEL_SIZE * y,
                null);
    }

    private void drawAdjacencyCount(Graphics g) {
        g.drawImage(ImageManager.OPEN_1, PIXEL_SIZE * x, PIXEL_SIZE * y, null);
    }

}
