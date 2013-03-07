package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board extends JPanel {

    public enum Dir {N, NW, W, SW, S, SE, E, NE}

    private Cell[][] cells;
    private int numRows, numColumns, numMines, pixelWidth, pixelHeight;

    public Board(LayoutManager manager, int rows, int columns, int numMines) {
        super(manager, true);

        numRows = rows;
        numColumns = columns;
        this.numMines = numMines;

        reset(rows, columns, numMines);

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX() / Cell.PIXEL_SIZE;
                int y = e.getY() / Cell.PIXEL_SIZE;

                if (x < numColumns && y < numRows && cells[y][x] != null)
                    switch (e.getButton()) {
                        case MouseEvent.BUTTON1:
                            cells[y][x].uncover();
                            break;
                        case MouseEvent.BUTTON3:
                            cells[y][x].mark();
                            break;
                        default:
                            e.consume();
                    }

                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumMines() {
        return numMines;
    }

    public int getPixelWidth() {
        return numColumns * Cell.PIXEL_SIZE;
    }

    public int getPixelHeight() {
        return numRows * Cell.PIXEL_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++) {
                if (cells[i][j] != null)
                    cells[i][j].draw(g);
            }
    }

    public void reset(int rows, int columns, int numMines) {
        this.numRows = rows;
        this.numColumns = columns;

        cells = new Cell[rows][columns];

        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++) {
                cells[i][j] = new Cell(j, i, true);
            }


    }
}
