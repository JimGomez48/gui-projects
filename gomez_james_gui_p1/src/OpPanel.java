import javax.swing.*;
import java.awt.*;

/**
 * User: Jim Date: 1/31/13 Time: 10:33 AM
 */
public class OpPanel extends JPanel {

    public static final int PREF_WIDTH = 50, PREF_HEIGHT = 160;
    private CalcDisplay display;

    private CalcButton add;
    private CalcButton subtract;
    private CalcButton multiply;
    private CalcButton divide;

    public OpPanel(CalcDisplay display, CalcPanel.ButtonListener listener) {
        super(new GridBagLayout(), true);
        setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        this.display = display;

        //create the op panel buttons and arrange them in a vertical grid
        add = new CalcButton("+");
        subtract = new CalcButton("-");
        multiply = new CalcButton("*");
        divide = new CalcButton("/");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 5, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(add, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(subtract, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(multiply, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(divide, constraints);

        //register the button listener with all of the buttons
        add.addActionListener(listener);
        subtract.addActionListener(listener);
        multiply.addActionListener(listener);
        divide.addActionListener(listener);
    }
}