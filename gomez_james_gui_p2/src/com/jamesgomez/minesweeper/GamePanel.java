package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private static JFrame frame;
    private GameDifficulty difficulty;
    private Board gameBoard;
    private Display display;

    public enum GameDifficulty {BEGINNER, INTERMEDIATE, EXPERT, CUSTOM}

    public GamePanel() {
        super(new BorderLayout(), true);

        setLayout(new BorderLayout(5, 5));

        difficulty = GameDifficulty.BEGINNER;

        gameBoard = new Board(new GridLayout(2, 2), 9, 9, 10);
        display = new Display(new BorderLayout(5, 5));

        gameBoard.setBackground(Color.LIGHT_GRAY);
        display.setPreferredSize(new Dimension(600, 30));
        display.setBackground(Color.LIGHT_GRAY);

        add(gameBoard, BorderLayout.CENTER);
        add(display, BorderLayout.NORTH);

        frame.add(createMenuBar(), BorderLayout.NORTH);
    }

    public JMenuBar createMenuBar() {
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
                difficulty = GameDifficulty.BEGINNER;
                newGame();
            }
        });

        intermediate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Intermediate");
                difficulty = GameDifficulty.INTERMEDIATE;
                newGame();
            }
        });

        expert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Expert");
                difficulty = GameDifficulty.EXPERT;
                newGame();
            }
        });

        custom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = GameDifficulty.CUSTOM;

                CustomDialog custom = new CustomDialog();
                custom.setLocationRelativeTo(frame);
                custom.setVisible(true);

            }
        });

        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "New Game");
                newGame();
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

    private void newGame() {
        switch (difficulty) {
            case BEGINNER:
                gameBoard.reset(9, 9, 10);
            case INTERMEDIATE:
                gameBoard.reset(16, 16, 40);
            case EXPERT:
                gameBoard.reset(30, 16, 99);
            case CUSTOM:
                break;
        }
    }

    public static void start() {
        frame = new JFrame("Minesweeper");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(new Dimension(600, 400));
        //frame.setResizable(false);

        //center the JFrame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                dim.height / 2 - frame.getSize().height / 2);

        ImageManager.LoadResources();

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GamePanel.start();
    }

    private class CustomDialog extends JDialog{
        //TODO implement custom dialog on "custom" menu item click

        public CustomDialog(){
            super();
        }
    }
}
