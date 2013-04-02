package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {

    private static JFrame frame;
    private static Game instance;
    private Board gameBoard;
    private DisplayBar displayBar;

    private GameDifficulty difficulty;
    public enum GameDifficulty {BEGINNER, INTERMEDIATE, EXPERT, CUSTOM}

    public Game() {
        super(new BorderLayout(5, 5), true);

        instance = this;
        difficulty = GameDifficulty.BEGINNER;

        gameBoard = new Board(9, 9, 10);
        displayBar = new DisplayBar();
        add(gameBoard, BorderLayout.CENTER);
        add(displayBar, BorderLayout.NORTH);

        frame.add(createMenuBar(), BorderLayout.NORTH);
        displayBar.setPreferredSize(new Dimension(gameBoard.getPixelWidth(), 30));
    }

    /** @return the singleton instance of this game */
    public static Game getInstance() {
        if (instance == null)
            instance = new Game();

        return instance;
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

        beginner.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = GameDifficulty.BEGINNER;
                newGame();
                gameBoard.repaint();
            }
        });

        intermediate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = GameDifficulty.INTERMEDIATE;
                newGame();
                gameBoard.repaint();
            }
        });

        expert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = GameDifficulty.EXPERT;
                newGame();
                gameBoard.repaint();
            }
        });

        //TODO implement "custom" click with new CustomDialog
        custom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = GameDifficulty.CUSTOM;

                CustomDialog custom = new CustomDialog();
                custom.setLocationRelativeTo(frame);
                custom.setVisible(true);
                gameBoard.repaint();

            }
        });

        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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

    public void newGame() {
        switch (difficulty) {
            case BEGINNER:      gameBoard.reset(9, 9, 10); break;
            case INTERMEDIATE:  gameBoard.reset(16, 16, 40); break;
            case EXPERT:        gameBoard.reset(16, 30, 99); break;
            case CUSTOM:        break;
        }
    }

    public static void start() {
        frame = new JFrame("Minesweeper");
        frame.setIconImage(ImageManager.BOMB_REVEALED);
        Game game = new Game();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 360);
        frame.setResizable(false);

        //center the JFrame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ImageManager.LoadResources();
        Game.start();
    }

    private class CustomDialog extends JDialog {
        //TODO implement custom dialog on "custom" menu item click

        public CustomDialog() {
            super();
        }
    }
}