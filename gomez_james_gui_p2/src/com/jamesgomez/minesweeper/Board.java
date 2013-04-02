package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {

    public enum Direction {N, NW, W, SW, S, SE, E, NE}

    private Cell[][] cells;
    private int numRows, numColumns, numMines;

    public Board(int rows, int columns, int numMines) {
        super(true);
        setBackground(Color.LIGHT_GRAY);

        numRows = rows;
        numColumns = columns;
        this.numMines = numMines;

        reset(rows, columns, numMines);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX() / Cell.WIDTH;
                int y = e.getY() / Cell.WIDTH;

                if (x < numColumns && y < numRows && cells[y][x] != null)
                    switch (e.getButton()) {
                        case MouseEvent.BUTTON1:
                            //cells[y][x].uncover();
                            uncoverCell(cells[y][x]);
                            break;
                        case MouseEvent.BUTTON3:
                            cells[y][x].mark();
                            break;
                        default:
                            e.consume();
                    }

                repaint();
            }
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
        return numColumns * Cell.WIDTH;
    }

    public int getPixelHeight() {
        return numRows * Cell.WIDTH;
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

    public Cell getAdjacent(Cell c, Direction d) {
        //TODO implement
        return new Cell(1, 1, true);
    }


    public void uncoverCell(Cell c) {
        if (c.isMined() && !c.isMarked()) {
            for (int i = 0; i < numRows; i++)
                for (int j = 0; j < numColumns; j++) {
                    if (cells[i][j] != null)
                        cells[i][j].uncover();
                }
        }
        else
            uncoverAdjacent(c);
    }

    public void uncoverAdjacent(Cell c) {
        //TODO implement
    }

    public void reset(int rows, int columns, int numMines) {
        this.numRows = rows;
        this.numColumns = columns;
        this.numMines = numMines;

        cells = new Cell[rows][columns];

        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++) {
                cells[i][j] = new Cell(j, i, true);
            }

        repaint();
    }
}
