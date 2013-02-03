import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** User: Jim Date: 1/30/13 Time: 10:58 PM */
public class CalcDisplay extends JTextField {

    public static final int PREF_WIDTH = 220, PREF_HEIGHT = 40;
    private final String allowed = "0123456789()+-*/";

    public CalcDisplay() {
        super("0");

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
                    evaluateExpression();
                    System.out.println("Enter pressed");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Do nothing
            }
        });
    }

    private void evaluateExpression() {
        //TODO: evaluate the expression in the display
    }
}
