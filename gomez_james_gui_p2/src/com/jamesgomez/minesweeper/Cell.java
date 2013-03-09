package com.jamesgomez.minesweeper;

import java.awt.*;

public class Cell implements IDrawable {

    public static final int PIXEL_SIZE = 16;
    private boolean mined, marked, covered;
    private int x, y, adjCount;

    public Cell(int x, int y, boolean isMined) {
        this.x = x;
        this.y = y;
        mined = isMined;
        marked = false;
        covered = true;
        adjCount = 0;
    }

    public boolean isMined() {
        return mined;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isCovered() {
        return covered;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAdjCount() {
        return adjCount;
    }

    @Override
    public void draw(Graphics g) {
        if (covered) {
            if (marked)
                g.drawImage(ImageManager.MARKED, PIXEL_SIZE * x, PIXEL_SIZE * y,
                        null);
            else
                g.drawImage(ImageManager.COVERED, PIXEL_SIZE * x, PIXEL_SIZE * y,
                        null);
        }
        else {
            if (mined)
                g.drawImage(ImageManager.BOMB_DEATH, PIXEL_SIZE * x, PIXEL_SIZE * y,
                        null);
            else
                drawAdjacencyCount(g);
        }
    }

    public void uncover() {
        if (!marked)
            covered = false;
    }

    public void mark() {
        if (covered) {
            if (marked)
                marked = false;
            else
                marked = true;
        }

    }

    private void drawAdjacencyCount(Graphics g) {
        g.drawImage(ImageManager.OPEN_1, PIXEL_SIZE * x, PIXEL_SIZE * y, null);
    }

}
