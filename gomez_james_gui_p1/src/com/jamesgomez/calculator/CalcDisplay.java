package com.jamesgomez.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

/*
 * User: Jim
 * Date: 1/30/13
 * Time: 10:58 PM
 */
public class CalcDisplay extends JTextField {

    public static final int PREF_WIDTH = 220, PREF_HEIGHT = 40;
    private final String allowed = "0123456789()+-*/";
    private boolean focused, debug;

    public CalcDisplay() {
        super();
        requestFocusInWindow(); //ensure initial focus is on this calc display

        debug = true;

        setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setFont(new Font("SansSerif", Font.PLAIN, 20));

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (allowed.indexOf(e.getKeyChar()) == -1)
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("Enter pressed");

                    if (focused)
                        try {
                            evaluateExpression();
                        }
                        catch (CalcPanel.CalcParseException exception) {
                            exception.printStackTrace();
                            JOptionPane.showMessageDialog(getRootPane(),
                                    exception.getMessage(), "Input Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                focused = true;

                if (debug)
                    System.out.println("Focused");
            }

            @Override
            public void focusLost(FocusEvent e) {
                focused = false;

                if (debug)
                    System.out.println("unfocused");
            }
        });
    }

    public boolean isFocused() {
        return focused;
    }

    /**
     * <p>Evaluates the expression currently held in the display.</p>
     * <p>If the selection is not empty, then the selection is evaluated to a
     * numerical value, and the numerical value replaces the selection in the
     * display. Other parts of the display before and after the selection are not
     * examined or modified. If the selection is empty, then the entire contents of
     * the display is evaluated to a numerical value, and the numerical value
     * replaces the entire contents of the display. If a parse exception is detected
     * during evaluation, a CalcParseException is thrown.</p>
     */
    private void evaluateExpression() throws CalcPanel.CalcParseException {

        String text = getSelectedText();
        boolean selectedText = true;

        if (text == null) {
            text = getText();
            selectedText = false;
        }

        try {
            ArrayList<Token> PostfixTokens = Tokenizer.infixToPostfix(Tokenizer
                    .adjustNegatives(Tokenizer.tokenize(text)));
            String answer = Tokenizer.evaluatePostfix(PostfixTokens);

            if (selectedText)
                replaceSelection(answer);
            else
                setText(answer);
        }
        catch (CalcPanel.CalcParseException e) { throw e; }

    }

}
