package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Board extends JPanel {

    public enum Dir {N, NW, W, SW, S, SE, E, NE}

    private Cell[][] cells;
    private int numRows, numColumns, numMines, numMarkedMines;
    private boolean gameOver;

    public Board() {
        super(true);

        setBackground(Color.LIGHT_GRAY);
        gameOver = false;

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!gameOver)
                    Game.getInstance().getDisplayBar().setFaceClicked(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (gameOver){
                    e.consume();
                    return;
                }

                Game.getInstance().getDisplayBar().setFaceClicked(false);

                int x = e.getX() / Cell.WIDTH;
                int y = e.getY() / Cell.WIDTH;

                if (x < numColumns && y < numRows && cells[y][x] != null){

                    if (!Game.getInstance().getDisplayBar().isTimerStarted())
                        Game.getInstance().getDisplayBar().startTimer();

                    switch (e.getButton()) {
                        case MouseEvent.BUTTON1:
                            uncoverCell(cells[y][x]);
                            break;
                        case MouseEvent.BUTTON3:
                            MarkMine(cells[y][x]);
                            break;
                        default:
                            e.consume();
                    }
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

    public int getNumMarkedMines() {
        return numMarkedMines;
    }

    /** Toggles the mark flag on Cell {@code c}. This method clamps the number of
     * marked Cells to the number of mined Cells, so that at any point during the
     * game the total number of marked Cells cannot exceed the total number of
     * mined Cells. */
    public void MarkMine(Cell c) {
        c.mark();
        if (c.isMarked()) {
            if (numMarkedMines < numMines) {
                numMarkedMines++;
                Game.getInstance().getDisplayBar().decrementMineRead();
                /*if (allMinesMarked()){
                    Game.getInstance().getDisplayBar().setWon();
                    gameOver = true;
                }*/
            }
            else
                c.mark();
        }
        else if (numMarkedMines > 0) {
            numMarkedMines--;
            Game.getInstance().getDisplayBar().incrementMineRead();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++) {
                if (cells[i][j] != null)
                    cells[i][j].draw(g);
            }

        repaint();
    }

    /** @return true if all mined cells are marked, false otherwise. */
    private boolean allMinesMarked(){
        for (int i = 0; i < numRows; i++){
            for (int j =0; j< numColumns; j++){
                if (cells[i][j].isMined())
                    if (!cells[i][j].isMarked())
                        return false;
            }
        }

        return true;
    }

    /** Marks all remaining mines on the board with red flags. */
    private void setAllMinesMarked(){
        for (int i = 0; i < numRows; i++){
            for (int j =0; j< numColumns; j++){
                if (cells[i][j].isMined())
                    if (!cells[i][j].isMarked())
                        cells[i][j].mark();
            }
        }
    }

    /**
     * @return the neighboring Cell relative to {@code c} in the specified
     *         direction {@code d}. If the resultant neighboring Cell is out of
     *         the bounds of the game board, {@code null} is returned.
     */
    public Cell getAdjacentCell(Cell c, Dir d) {
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

    /**
     * @return an {@link ArrayList} of Cells that are immediately adjacent to
     *         {@code cell}.
     */
    public ArrayList<Cell> getAdjacentCells(Cell cell) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();

        Cell N = getAdjacentCell(cell, Dir.N);
        Cell NE = getAdjacentCell(cell, Dir.NE);
        Cell E = getAdjacentCell(cell, Dir.E);
        Cell SE = getAdjacentCell(cell, Dir.SE);
        Cell S = getAdjacentCell(cell, Dir.S);
        Cell SW = getAdjacentCell(cell, Dir.SW);
        Cell W = getAdjacentCell(cell, Dir.W);
        Cell NW = getAdjacentCell(cell, Dir.NW);

        if (N != null)
            neighbors.add(N);
        if (NE != null)
            neighbors.add(NE);
        if (E != null)
            neighbors.add(E);
        if (SE != null)
            neighbors.add(SE);
        if (S != null)
            neighbors.add(S);
        if (SW != null)
            neighbors.add(SW);
        if (W != null)
            neighbors.add(W);
        if (NW != null)
            neighbors.add(NW);

        return neighbors;
    }

    /**
     * Uncovers this Cell and, depending on whether the cell is mined or not,
     * either uncovers all cells or uncovers just this cell and any neighboring
     * cells with adjacency count of 0. If the cell is marked,
     * this method does nothing.
     */
    public void uncoverCell(Cell cell) {
        if (!cell.isMarked()) {
            if (cell.isMined()) {
                cell.setExploded(true);
                uncoverAllMines();
                Game.getInstance().getDisplayBar().setLost();
                gameOver = true;
            }
            else{
                uncoverAdjacentCells(cell);
                if (allNonMinesUncovered()){
                    setAllMinesMarked();
                    Game.getInstance().getDisplayBar().setWon();
                    gameOver = true;
                }
            }
        }
    }

    /** @return true if all non-mined cells on the board have been uncovered,
     * false otherwise. */
    private boolean allNonMinesUncovered(){
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++) {
                if (!cells[i][j].isMined() && cells[i][j].isCovered())
                    return  false;
            }

        return true;
    }

    /** Uncovers all Cells on the game board */
    private void uncoverAllCells() {
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++) {
                if (cells[i][j] != null)
                    cells[i][j].uncover();
            }
    }

    /** Uncovers all mined cells */
    private void uncoverAllMines() {
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++) {
                if (/*cells[i][j] != null &&*/ cells[i][j].isMined())
                    cells[i][j].uncover();
                else{
                    if (cells[i][j].isMarked())
                        cells[i][j].setIncorrectlyMarked(true);
                }
            }
    }

    /**
     * Recursively uncovers all adjacent cells until it reaches cells with
     * non-zero adjacency counts, or reaches the edge of the board
     */
    private void uncoverAdjacentCells(Cell cell) {
        if (cell == null)
            return;
        else if (!cell.isCovered())
            return;
        else {
            cell.uncover();
            if (cell.getAdjCount() == 0) {
                ArrayList<Cell> neighbors = getAdjacentCells(cell);
                for (Cell c : neighbors) {
                    uncoverAdjacentCells(c);
                }
            }
        }
    }

    /**
     * Resets the game board with the specified amount of rows, columns,
     * and mines. The cells are randomly shuffled within the game board,
     * and mine adjacency counts are calculated and assigned to each cell.
     * <p>Running time is {@code &theta(n^3)}, where {@code n} is the number of
     * cells on the board.</p>
     */
    public void reset(int rows, int columns, int numMines) {
        this.numRows = rows;
        this.numColumns = columns;
        this.numMines = numMines;
        numMarkedMines = 0;

        //fill temporary cell list with the correct amount of mined and unmined cells
        ArrayList tempCells = new ArrayList<Cell>();
        int count = 0;
        for (int i = 0; i < rows * columns; i++) {
            if (count++ < numMines)
                tempCells.add(new Cell(true));
            else
                tempCells.add(new Cell(false));
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

        //set adjacency counts for all cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell currentCell = cells[i][j];

                if (currentCell.isMined())
                    currentCell.setAdjCount(-1);
                else {
                    int mineCount = 0;
                    ArrayList<Cell> neighbors = getAdjacentCells(currentCell);

                    for (Cell c : neighbors) {
                        if (c.isMined())
                            mineCount++;
                    }
                    currentCell.setAdjCount(mineCount);
                }
            }
        }

        gameOver = false;
        setPreferredSize(new Dimension(numColumns * Cell.WIDTH,
                numRows * Cell.HEIGHT));
        repaint();
    }

}
