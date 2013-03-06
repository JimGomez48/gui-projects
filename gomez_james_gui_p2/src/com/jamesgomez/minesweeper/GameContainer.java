package com.jamesgomez.minesweeper;

import sun.net.idn.StringPrep;

import javax.swing.*;
import java.awt.*;

public class GameContainer extends JFrame {

    private Board gameBoard;

    public GameContainer(String title) {
        super(title);

        getContentPane().setLayout(new BorderLayout(5, 5));
        gameBoard = new Board(new GridLayout(2, 2));
        this.add(gameBoard);


    }

    public static void start() {
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

        //create game container and attach menu bar
        GameContainer game = new GameContainer("Minesweeper");
        game.add(menuBar, BorderLayout.NORTH);


        game.setSize(new Dimension(300, 300));
        game.setVisible(true);



        /*frame = new JFrame("Calculator");
        CalcPanel panel = new CalcPanel(new GridBagLayout());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);*/

        //center the JFrame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        game.setLocation(dim.width / 2 - game.getSize().width / 2,
                dim.height / 2 - game.getSize().height / 2);
    }

    public static void main(String[] args) {
        GameContainer.start();
    }
}
