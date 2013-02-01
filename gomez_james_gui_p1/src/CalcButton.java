import javax.swing.*;
import java.awt.*;

/**
 * User: Jim Date: 1/30/13 Time: 10:41 PM
 */
public class CalcButton extends JButton {

    public static final int PREF_WIDTH = 50, PREF_HEIGHT = 40;

    public CalcButton(String text) {
        super(text);
        setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        //TODO: set a nice font and font size for these buttons
    }
}
