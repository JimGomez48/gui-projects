import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * User: Jim
 * Date: 1/31/13
 * Time: 10:33 AM
 */
public class OpPanel extends JPanel {

    public static final int PREF_WIDTH = 50, PREF_HEIGHT = 160;
    private CalcDisplay dislay;

    private CalcButton add;
    private CalcButton subtract;
    private CalcButton multiply;
    private CalcButton divide;

    public OpPanel(CalcDisplay display) {
        super(new GridBagLayout(), true);
        setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        this.dislay = display;

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

        createActionListeners();
    }

    private void createActionListeners() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement add button listener
            }
        });

        subtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement subtract button listener
            }
        });

        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement multiply button listener
            }
        });

        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement divide button listener
            }
        });
    }
}
