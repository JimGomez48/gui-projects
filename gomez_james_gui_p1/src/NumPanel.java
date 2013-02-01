import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** User: Jim Date: 1/31/13 Time: 10:33 AM */
public class NumPanel extends JPanel {

    public static final int PREF_WIDTH = 150, PREF_HEIGHT = 160;
    private CalcDisplay display;

    private CalcButton zero;
    private CalcButton one;
    private CalcButton two;
    private CalcButton three;
    private CalcButton four;
    private CalcButton five;
    private CalcButton six;
    private CalcButton seven;
    private CalcButton eight;
    private CalcButton nine;
    private CalcButton leftParen;
    private CalcButton rightParen;


    public NumPanel(final CalcDisplay display) {
        super(new GridBagLayout(), true);
        setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        this.display = display;

        zero = new CalcButton("0");
        one = new CalcButton("1");
        two = new CalcButton("2");
        three = new CalcButton("3");
        four = new CalcButton("4");
        five = new CalcButton("5");
        six = new CalcButton("6");
        seven = new CalcButton("7");
        eight = new CalcButton("8");
        nine = new CalcButton("9");
        leftParen = new CalcButton("(");
        rightParen = new CalcButton(")");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(seven, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(eight, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(nine, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(four, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(five, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        add(six, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(one, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(two, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        add(three, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(leftParen, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(zero, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        add(rightParen, constraints);


        createButtonListeners();
    }

    private void createButtonListeners() {
        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(one.getText()));
            }
        });

        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(two.getText()));
            }
        });

        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(three.getText()));
            }
        });

        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(four.getText()));
            }
        });

        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(five.getText()));
            }
        });

        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(six.getText()));
            }
        });

        seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(seven.getText()));
            }
        });

        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(eight.getText()));
            }
        });

        nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(nine.getText()));
            }
        });

        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(zero.getText()));
            }
        });

        leftParen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(leftParen.getText()));
            }
        });

        rightParen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = display.getText();
                display.setText(text.concat(rightParen.getText()));
            }
        });
    }
}
