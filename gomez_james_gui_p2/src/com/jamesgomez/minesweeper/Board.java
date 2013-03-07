package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    Cell cell;

    public Board(LayoutManager manager) {
        super(manager, true);

        cell = new Cell(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        cell.draw(g);
    }
}
