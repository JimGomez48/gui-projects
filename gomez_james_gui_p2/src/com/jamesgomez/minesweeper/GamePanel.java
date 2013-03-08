package com.jamesgomez.minesweeper;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private static JFrame frame;
    private GameDifficulty difficulty;
    private Board gameBoard;
    private Display display;

    public enum GameDifficulty {BEGINNER, INTERMEDIATE, EXPERT, CUSTOM}

    public GamePanel(LayoutManager manager) {
        super(manager, true);

        difficulty = GameDifficulty.BEGINNER;

        gameBoard = new Board(new GridLayout(2, 2), 9, 9, 10);
        gameBoard.setBackground(Color.LIGHT_GRAY);

        /*Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        gameBoard.setBorder(BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel));*/

        display = new Display(new BorderLayout(5, 5));
        display.setBackground(Color.LIGHT_GRAY);

        add(gameBoard, BorderLayout.CENTER);
        add(display, BorderLayout.NORTH);

        frame.add(createMenuBar(), BorderLayout.NORTH);
        display.setPreferredSize(new Dimension(gameBoard.getPixelWidth(), 30));
        /*frame.setSize(new Dimension(gameBoard.getPixelWidth()+16,
                gameBoard.getPixelHeight() + 100));*/
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

    private void newGame() {
        switch (difficulty) {
            case BEGINNER:
                gameBoard.reset(9, 9, 10);
                break;
            case INTERMEDIATE:
                gameBoard.reset(16, 16, 40);
                break;
            case EXPERT:
                gameBoard.reset(16, 30, 99);
                break;
            case CUSTOM:
                break;
        }
    }

    public static void start() {
        frame = new JFrame("Minesweeper");
        GamePanel gamePanel = new GamePanel(new BorderLayout(5, 5));
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);

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

    private class CustomDialog extends JDialog {
        //TODO implement custom dialog on "custom" menu item click

        public CustomDialog() {
            super();
        }
    }
}
