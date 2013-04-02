package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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

    /**
     * @return the neighboring Cell relative to Cell c in the specified direction.
     *         If the resultant neighboring cell is out of the bounds of the game
     *         board,
     *         null is returned.
     */
    public Cell getAdjacentCell(Cell c, Direction d) {
        int row = -1, column = -1;

        switch (d) {
            case N:
                row = c.getRow() - 1;
                column = c.getColumn();
                break;
            case NE:
                row = c.getRow() - 1;
                column = c.getColumn() + 1;
                break;
            case E:
                row = c.getRow();
                column = c.getColumn() + 1;
                break;
            case SE:
                row = c.getRow() + 1;
                column = c.getColumn() + 1;
                break;
            case S:
                row = c.getRow() + 1;
                column = c.getColumn();
                break;
            case SW:
                row = c.getRow() + 1;
                column = c.getColumn() - 1;
                break;
            case W:
                row = c.getRow();
                column = c.getColumn() - 1;
                break;
            case NW:
                row = c.getRow() - 1;
                column = c.getColumn() - 1;
                break;
        }

        if (row < 0 || row >= numRows || column < 0 || column >= numColumns)
            return null;

        return cells[row][column];
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
            uncoverAdjacentCells(c);
    }

    private void uncoverAdjacentCells(Cell c) {
        //TODO implement
    }

    /**
     * Resets the game board with the specified amount of rows, columns,
     * and mines. The cells are then randomly shuffled within the game board.
     */
    public void reset(int rows, int columns, int numMines) {
        this.numRows = rows;
        this.numColumns = columns;
        this.numMines = numMines;

        //fill temporary cell list with the correct amount of mined cells
        ArrayList tempCells = new ArrayList<Cell>();
        int count = 0;
        for (int i = 0; i < rows * columns; i++) {
            if (count < numMines)
                tempCells.add(new Cell(true));
            else
                tempCells.add(new Cell(false));

            count++;
        }

        //shuffle temp cell list and distribute contents within the cell array
        Collections.shuffle(tempCells);
        cells = new Cell[rows][columns];
        Iterator<Cell> iterator = tempCells.iterator();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell c = iterator.next();
                c.setLocation(i, j);
                cells[i][j] = c;
            }
        }

        repaint();
    }
}
