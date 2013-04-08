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
        gameBoard = new Board();
        displayBar = new DisplayBar();
        customDialog = new CustomDialog();

        add(gameBoard, BorderLayout.CENTER);
        add(displayBar, BorderLayout.NORTH);

        frame.add(createMenuBar(), BorderLayout.NORTH);

        displayBar.setPreferredSize(new Dimension(gameBoard.getPreferredSize()
                .width, 28));

        newGame(GameDifficulty.BEGINNER);
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

    /** @return the current game difficulty */
    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Creates the menu bar at the top of this Game's JFrame and sets a custom
     * ActionListener class to them.
     */
    private JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        final JMenu gameMenu = new JMenu("Game");
        final JMenu difficultyMenu = new JMenu("Difficulty");
        final JMenuItem newGame = new JMenuItem("New Game");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenuItem beginner = new JMenuItem("Beginner");
        final JMenuItem intermediate = new JMenuItem("Intermediate");
        final JMenuItem expert = new JMenuItem("Expert");
        final JMenuItem custom = new JMenuItem("Customize...");

        class MenuListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem selectedItem = (JMenuItem) e.getSource();

                if (selectedItem.equals(newGame)) {
                    newGame(difficulty);
                }
                else if (selectedItem.equals(exit)) {
                    frame.dispose();
                    System.exit(0);
                }
                else if (selectedItem.equals(beginner)) {
                    newGame(GameDifficulty.BEGINNER);
                }
                else if (selectedItem.equals(intermediate)) {
                    newGame(GameDifficulty.INTERMEDIATE);
                }
                else if (selectedItem.equals(expert)) {
                    newGame(GameDifficulty.EXPERT);
                }
                else if (selectedItem.equals(custom)) {
                    customDialog.setLocationRelativeTo(frame);
                    customDialog.setVisible(true);
                }

                repaint();
            }
        }

        MenuListener menuListener = new MenuListener();
        newGame.addActionListener(menuListener);
        exit.addActionListener(menuListener);
        beginner.addActionListener(menuListener);
        intermediate.addActionListener(menuListener);
        expert.addActionListener(menuListener);
        custom.addActionListener(menuListener);

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
     * DisplayBar. If the difficulty of the game has changed, the board is
     * initialized to reflect this change. This method must be called on creation of
     * the Minesweeper game application.
     */
    public void newGame(GameDifficulty d) {
        difficulty = d;

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
                int rows = getGameBoard().getNumRows();
                int columns = getGameBoard().getNumColumns();
                int mines = getGameBoard().getNumMines();
                newCustomGame(rows, columns, mines);
                return;
        }

        displayBar.reset();
        resizeAndCenter();
    }

    /** Resets this Game to a new customized game state */
    public void newCustomGame(int rows, int columns, int mines){
        difficulty = GameDifficulty.CUSTOM;
        gameBoard.reset(rows, columns, mines);
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
     * A customized Dialog Window used for setting user-desired parameters to the
     * Game. The Dialog is shown when the user selects the "customize" menu item
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
            mainPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

            createEntryPanel();
            createButtonPanel();

            mainPanel.add(entryPanel);
            mainPanel.add(buttonPanel);
            getContentPane().add(mainPanel);

            pack();
            setResizable(false);
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

            JLabel widthLabel = new JLabel("Width (9 - 30): ");
            JLabel heightLabel = new JLabel("Height (9 - 16): ");
            JLabel minesLabel = new JLabel("Mines (10 - 432): ");

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
            buttonPanel= new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

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
            height.setModel(new SpinnerNumberModel(gameBoard.getNumRows(), 9, 16,
                    1));
            numMines.setModel(new SpinnerNumberModel(gameBoard.getNumMines(), 10,
                    668, 1));
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

                // Show warning dialog if user attempts to set number of mines to
                // greater than 90% of the total number of cells. Then set number
                // of mines to max allowed.
                int maxMines = (int) ((Integer) width.getValue() * (Integer)
                        height.getValue() * 0.9);
                if ((Integer) numMines.getValue() > maxMines) {
                    JOptionPane.showMessageDialog(this, "Number of mines cannot " +
                            "exceed " + maxMines + " for the current Board settings",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    numMines.setValue(new Integer(maxMines));
                    return;
                }

                newCustomGame((Integer) height.getValue(),
                        (Integer) width.getValue(), (Integer) numMines.getValue());
                resizeAndCenter();
                setVisible(false);
            }
            else if (cancelButton == e.getSource()) {
                setVisible(false);
            }
        }
    }

}
