package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
                7, gameBoard.getPreferredSize().height + displayBar
                .getPreferredSize().height + menuBar.getPreferredSize().height + 34);
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

        private JPanel mainPanel;
        private JPanel entryPanel;
        private JPanel buttonPanel;

        private JSpinner width;
        private JSpinner height;
        private JSpinner numMines;

        private JButton okButton;
        private JButton cancelButton;

        public CustomDialog() {
            super(frame, "Customize", true);

            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            createEntryPanel();
            createButtonPanel();

            mainPanel.add(entryPanel);
            mainPanel.add(buttonPanel);
            getContentPane().add(mainPanel);

            setResizable(false);
            pack();
            setLocationRelativeTo(frame);
            setVisible(false);
        }

        private void createEntryPanel(){
            entryPanel = new JPanel();
            GroupLayout groupLayout = new GroupLayout(entryPanel);
            groupLayout.setAutoCreateGaps(true);
            groupLayout.setAutoCreateContainerGaps(true);
            entryPanel.setLayout(groupLayout);

            width = new JSpinner(new SpinnerNumberModel(0, 0, 30, 1));
            height = new JSpinner(new SpinnerNumberModel(0, 0, 16, 1));
            numMines = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));

            JLabel widthLabel = new JLabel("Width (9-30): ");
            JLabel heightLabel = new JLabel("Height (9-16): ");
            JLabel minesLabel = new JLabel("Mines (10-668): ");

            //use GroupLayout to set up the layout of the spinners and labels
            GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
            hGroup.addGroup(groupLayout.createParallelGroup()
                    .addComponent(widthLabel)
                    .addComponent(heightLabel)
                    .addComponent(minesLabel));
            hGroup.addGroup(groupLayout.createParallelGroup()
                    .addComponent(width)
                    .addComponent(height)
                    .addComponent(numMines));
            groupLayout.setHorizontalGroup(hGroup);

            GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
            vGroup.addGroup(groupLayout.createParallelGroup()
                    .addComponent(widthLabel)
                    .addComponent(width));
            vGroup.addGroup(groupLayout.createParallelGroup()
                    .addComponent(heightLabel)
                    .addComponent(height));
            vGroup.addGroup(groupLayout.createParallelGroup()
                    .addComponent(minesLabel)
                    .addComponent(numMines));
            groupLayout.setVerticalGroup(vGroup);
        }

        private void createButtonPanel(){
            buttonPanel= new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 10));

            okButton = new JButton("Ok");
            okButton.setPreferredSize(new Dimension(80, 26));
            okButton.addActionListener(this);
            cancelButton = new JButton("Cancel");
            cancelButton.setPreferredSize(new Dimension(80, 26));
            cancelButton.addActionListener(this);
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
        }

        private void initializeSpinners(){
            width.setModel(new SpinnerNumberModel(gameBoard.getNumColumns(), 9,
                    30, 1));
            height.setModel(new SpinnerNumberModel(gameBoard.getNumRows(), 9, 16, 1));
            numMines.setModel(new SpinnerNumberModel(gameBoard.getNumMines(), 10, 668, 1));
        }

        @Override
        public void setVisible(boolean visible){
            if (visible)
                initializeSpinners();

            super.setVisible(visible);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (okButton == e.getSource()) {
                difficulty = GameDifficulty.CUSTOM;
                gameBoard.reset((Integer) height.getValue(),
                        (Integer) width.getValue(), (Integer) numMines.getValue());
                displayBar.reset();
                resizeAndCenter();
                setVisible(false);
            }
            else if (cancelButton == e.getSource()) {
                setVisible(false);
            }
        }
    }

}
