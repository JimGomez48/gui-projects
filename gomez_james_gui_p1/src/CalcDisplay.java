import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * User: Jim
 * Date: 1/30/13
 * Time: 10:58 PM
 */
public class CalcDisplay extends JTextField {

    public static final int PREF_WIDTH = 220, PREF_HEIGHT = 40;
    private final String allowed = "0123456789()+-*/";
    private boolean focused;

    public CalcDisplay() {
        super("0");
        requestFocusInWindow();

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
                    if (focused)
                        evaluateExpression();

                    System.out.println("Enter pressed");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Do nothing
            }
        });

        addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                focused = true;
                System.out.println("Focused");
            }

            @Override
            public void focusLost(FocusEvent e) {
                focused = false;
                System.out.println("unfocused");
            }
        });
    }

    public boolean isFocused() {
        return focused;
    }

    private void evaluateExpression() {
        //TODO: evaluate the expression in the display

        /*
        - If the selection is not empty, then the selection is evaluated to a
        numerical value, and the numerical value
        replaces the selection in the display.  Other parts of the display before
        and after the selection are not
        examined or modified.
        - If the selection is empty, then the entire contents of the display is
        evaluated to a numerical value, and the
        numerical value replaces the entire contents of the display.
        - If a parse exception is detected during evaluation,
        an alert box is displayed, and the selection or display is
        not modified.
        */

        ArrayList<Token> tokens = Tokenizer.tokenize(getText());
        Iterator<Token> iterator = tokens.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().value);
        }
    }
}
