package com.jamesgomez.minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell implements IDrawable, ActionListener {

    public enum CellState {COVERED, UNCOVERED, MARKED}

    private CellState state;
    private boolean mined;

    public Cell(boolean isMined) {
        state = CellState.COVERED;
        mined = isMined;
    }

    @Override
    public void draw(Graphics g) {
        switch (state) {
            case COVERED:
                drawBlank(g);
                break;
            case UNCOVERED:
                if (mined)
                    drawMined(g);
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
    private void drawBlank(Graphics g) {

    }

    private void drawMarked(Graphics g) {

    }

    private void drawMined(Graphics g) {

    }

    private void drawAdjacencyCount(Graphics g) {

    }

}
