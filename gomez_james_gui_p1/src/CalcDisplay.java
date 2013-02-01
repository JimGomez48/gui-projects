import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** User: Jim Date: 1/30/13 Time: 10:58 PM */
public class CalcDisplay extends JTextField {

    public static final int PREF_WIDTH = 220, PREF_HEIGHT = 40;

    public CalcDisplay() {
        super("0");

        setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        setFont(new Font("SansSerif", Font.PLAIN, 20));

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();

                if (!(key == '0' || key == '1' || key == '2' || key == '3' || key ==
                        '4' || key == '5' || key == '6' || key == '7' || key ==
                        '8' || key == '9' || key == '(' || key == ')' || key ==
                        '+' || key == '-' || key == '*' || key == '/'))
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();

                if (keycode == KeyEvent.VK_ENTER) {
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
