import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * User: Jim
 * Date: 1/31/13
 * Time: 10:33 AM
 */
public class NumPanel extends JPanel {

    public static final int PREF_WIDTH = 150, PREF_HEIGHT = 160;
    private final int ZERO = 0, ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5,
            SIX = 6, SEVEN = 7, EIGHT = 8,
            NINE = 9, LEFT_PAREN = 10, RIGHT_PAREN = 11;

    private CalcDisplay display;
    private ArrayList<CalcButton> buttons;

    public NumPanel(final CalcDisplay display, CalcPanel.ButtonListener listener) {
        super(new GridBagLayout(), true);

        setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        this.display = display;

        //create the op panel buttons and store them in the buttons arraylist
        buttons = new ArrayList<CalcButton>(12);

        buttons.add(new CalcButton("0"));
        buttons.add(new CalcButton("1"));
        buttons.add(new CalcButton("2"));
        buttons.add(new CalcButton("3"));
        buttons.add(new CalcButton("4"));
        buttons.add(new CalcButton("5"));
        buttons.add(new CalcButton("6"));
        buttons.add(new CalcButton("7"));
        buttons.add(new CalcButton("8"));
        buttons.add(new CalcButton("9"));
        buttons.add(new CalcButton("("));
        buttons.add(new CalcButton(")"));

        //arrange the buttons in a vertical grid
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(buttons.get(SEVEN), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(buttons.get(EIGHT), constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(buttons.get(NINE), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(buttons.get(FOUR), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(buttons.get(FIVE), constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(buttons.get(SIX), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(buttons.get(ONE), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(buttons.get(TWO), constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        add(buttons.get(THREE), constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(buttons.get(LEFT_PAREN), constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(buttons.get(ZERO), constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        add(buttons.get(RIGHT_PAREN), constraints);

        //register the button listener with all of the buttons
        for (CalcButton b : buttons) {
            b.addActionListener(listener);
        }
    }
}
