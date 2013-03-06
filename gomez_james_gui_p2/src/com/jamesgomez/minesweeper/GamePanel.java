package com.jamesgomez.minesweeper;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private static JFrame frame;
    private Board gameBoard;
    private Display display;

    public enum GameDifficulty {BEGINNER, INTERMEDIATE, EXPERT, CUSTOM };
    private GameDifficulty difficulty;

    public GamePanel() {
        super(new BorderLayout(), true);

        setLayout(new BorderLayout(5, 5));
        gameBoard = new Board(new GridLayout(2, 2));
        display = new Display(new BorderLayout(5, 5));

        difficulty = GameDifficulty.BEGINNER;

        gameBoard.setBackground(Color.red);
        display.setPreferredSize(new Dimension(300, 30));
        display.setBackground(Color.blue);

        add(gameBoard, BorderLayout.CENTER);
        add(display, BorderLayout.NORTH);
    }

    public static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");
        JMenu difficultyMenu = new JMenu("Difficulty");
        JMenuItem beginner = new JMenuItem("Beginner");
        JMenuItem intermediate = new JMenuItem("Intermediate");
        JMenuItem expert = new JMenuItem("Expert");
        JMenuItem custom = new JMenuItem("Custom");

        //TODO implement correct action on menu item clicks
        beginner.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Beginner");
            }
        });

        intermediate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Intermediate");
            }
        });

        expert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Expert");
            }
        });

        custom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Custom");
            }
        });

        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "New Game");
            }
        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        //add menu items to game menu
        gameMenu.add(newGame);
        gameMenu.addSeparator();
        gameMenu.add(exit);
        difficultyMenu.add(beginner);
        difficultyMenu.add(intermediate);
        difficultyMenu.add(expert);
        difficultyMenu.addSeparator();
        difficultyMenu.add(custom);
        menuBar.add(gameMenu);
        menuBar.add(difficultyMenu);

        return menuBar;
    }

    public static void start() {
        frame = new JFrame("Minesweeper");
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
