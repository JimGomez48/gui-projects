package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.beans.Customizer;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Game extends JPanel {

    private static JFrame frame;
    private static Game instance;
    private Board gameBoard;
    private DisplayBar displayBar;
    private JMenuBar menuBar;
    private CustomDialog customDialog;

    private GameDifficulty difficulty;

    public enum GameDifficulty {BEGINNER, INTERMEDIATE, EXPERT, CUSTOM}

    public Game() {
        super(new BorderLayout(5, 5), true);

        instance = this;
        difficulty = GameDifficulty.BEGINNER;

        gameBoard = new Board();
        displayBar = new DisplayBar();
        customDialog = new CustomDialog();

        add(gameBoard, BorderLayout.CENTER);
        add(displayBar, BorderLayout.NORTH);

        frame.add(createMenuBar(), BorderLayout.NORTH);

        displayBar.setPreferredSize(new Dimension(gameBoard.getPreferredSize()
                .width, 28));

        newGame();
    }

    /** @return the singleton instance of this game */
    public static Game getInstance() {
        if (instance == null)
            instance = new Game();

        return instance;
    }

    /** @return this Game's Board */
    public Board getGameBoard() {
        return gameBoard;
    }

    /** @return this Game's DisplayBar */
    public DisplayBar getDisplayBar() {
        return displayBar;
    }

    /**
     * Creates the menu bar at the top of this Game's JFrame and set listeners to
     * them.
     */
    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");
        JMenu difficultyMenu = new JMenu("Difficulty");
        JMenuItem beginner = new JMenuItem("Beginner");
        JMenuItem intermediate = new JMenuItem("Intermediate");
        JMenuItem expert = new JMenuItem("Expert");
        JMenuItem custom = new JMenuItem("Customize...");

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

                customDialog.setLocationRelativeTo(frame);
                customDialog.setVisible(true);
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
                System.exit(0);
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

    /**
     * Resets this Game to a new game state, including this Game's Board and
     * DisplayBar. If the difficulty of the game has changed. The board is
     * initialized to reflect this change.
     */
    public void newGame() {
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

        displayBar.reset();
        resizeAndCenter();
    }

    /** Resizes and centers the JFrame  containing the Game JPanel. */
    private void resizeAndCenter(){
        //resize the JFrame
        Dimension frameSize = new Dimension(gameBoard.getPreferredSize().width +
                5, gameBoard.getPreferredSize().height + displayBar
                .getPreferredSize().height + menuBar.getPreferredSize().height + 32);
        frame.setPreferredSize(frameSize);
        frame.pack();

        //center the JFrame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                dim.height / 2 - frame.getSize().height / 2);
    }

    /**
     * Builds the JFrame and initializes the Board and DisplayBar at difficulty
     * level BEGINNER
     */
    public static void start() {
        frame = new JFrame("Minesweeper");
        frame.setIconImage(ImageManager.BOMB_REVEALED);
        Game game = new Game();
        frame.add(game);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setSize(500, 360);
        frame.setResizable(false);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ImageManager.LoadResources();
        Game.start();
    }

    /**
     * A customized Dialog Window used for setting user-desirde parameters to the
     * Game. The Dialog is shown when the user selects the customize menu item
     * from the menu bar.
     */
    private class CustomDialog extends JDialog implements ActionListener {

//        public CustomDialog() {
//            super(frame, "Customize Game", true);
//        }

        private JPanel myPanel = null;
        private JButton yesButton = null;
        private JButton noButton = null;
        private boolean answer = false;

        public boolean getAnswer() { return answer; }

        public CustomDialog() {
            super(frame, true);

            myPanel = new JPanel();
            getContentPane().add(myPanel);
            myPanel.add(new JLabel("Custom Dialog?"));
            yesButton = new JButton("Yes");
            yesButton.addActionListener(this);
            myPanel.add(yesButton);
            noButton = new JButton("No");
            noButton.addActionListener(this);
            myPanel.add(noButton);
            pack();
            setLocationRelativeTo(frame);
            setVisible(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (yesButton == e.getSource()) {
                System.out.println("User chose yes.");
                answer = true;
                setVisible(false);
            }
            else if (noButton == e.getSource()) {
                System.out.println("User chose no.");
                answer = false;
                setVisible(false);
            }
        }
    }

}
