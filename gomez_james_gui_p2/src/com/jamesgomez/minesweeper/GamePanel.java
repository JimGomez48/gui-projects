package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Board gameBoard;
    private Display display;

    public GamePanel() {
        super(new BorderLayout(), true);

        setLayout(new BorderLayout(5, 5));
        gameBoard = new Board(new GridLayout(2, 2));
        display = new Display(new BorderLayout(5, 5));

        gameBoard.setBackground(Color.red);
        display.setPreferredSize(new Dimension(300, 30));
        display.setBackground(Color.blue);

        add(gameBoard, BorderLayout.CENTER);
        add(display, BorderLayout.NORTH);
    }

    public static JMenuBar createMenuBar() {
        //create menu objects
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem beginner = new JMenuItem("Beginner");
        JMenuItem intermediate = new JMenuItem("Intermediate");
        JMenuItem expert = new JMenuItem("Expert");
        //add menu items to game menu
        gameMenu.add(beginner);
        gameMenu.add(intermediate);
        gameMenu.add(expert);
        //add game menu to menu bar
        menuBar.add(gameMenu);

        return menuBar;
    }

    public static void start() {
        JFrame frame = new JFrame("Minesweeper");
        GamePanel gamePanel = new GamePanel();
        frame.add(createMenuBar(), BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(new Dimension(300, 300));
        //frame.setResizable(false);
        frame.setVisible(true);

        //center the JFrame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                dim.height / 2 - frame.getSize().height / 2);
    }

    public static void main(String[] args) {
        GamePanel.start();
    }
}
