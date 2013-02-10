package com.jamesgomez.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * User: Jim
 * Date: 1/30/13
 * Time: 10:48 PM
 */
public class CalcPanel extends JPanel {

    private static JFrame frame;
    private NumPanel numPanel;
    private OpPanel opPanel;
    private CalcDisplay display;

    public static class CalcParseException extends Exception {

        public CalcParseException(String msg) {
            super("Invalid Infix Expression: " + msg);
        }
    }

    public CalcPanel(LayoutManager manager) {
        super(manager, true);

        //create panels and a constraints objects
        GridBagConstraints constraints = new GridBagConstraints();
        display = new CalcDisplay();
        ButtonListener buttonListener = new ButtonListener();
        numPanel = new NumPanel(display, buttonListener);
        opPanel = new OpPanel(display, buttonListener);

        //setup and attach display
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(display, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(numPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(opPanel, constraints);

    }

    public static void buildGui() {
        frame = new JFrame("Calculator");
        CalcPanel panel = new CalcPanel(new GridBagLayout());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        //center the JFrame
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                dim.height / 2 - frame.getSize().height / 2);
    }

    public static void main(String[] args) {
        CalcPanel.buildGui();
    }

    /** This class should be instantiated once and reused for all buttons */
    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (display.isFocused())
                ;
            else
                display.setText(display.getText().concat(e.getActionCommand()));
        }
    }
}
