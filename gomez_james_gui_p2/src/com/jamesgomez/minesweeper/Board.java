package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    public enum Dir {N, NW, W, SW, S, SE, E, NE}

    private Cell[][] cell;
    private int rows, columns, numMines, pixelWidth, pixelHeight;

    public Board(LayoutManager manager, int rows, int columns, int numMines) {
        super(manager, true);

        this.rows = rows;
        this.columns = columns;
        this.numMines = numMines;

        reset(rows, columns, numMines);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getNumMines() {
        return numMines;
    }

    public int getPixelWidth() {
        return columns * Cell.PIXEL_SIZE;
    }

    public int getPixelHeight() {
        return rows * Cell.PIXEL_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (cell[0][0] != null)
            cell[0][0].draw(g);
    }

    public void reset(int rows, int columns, int numMines) {
        this.rows = rows;
        this.columns = columns;

        cell = new Cell[rows][columns];
        cell[0][0] = new Cell(1, 1, true);
    }
}
