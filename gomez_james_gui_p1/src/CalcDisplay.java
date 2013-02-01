import javax.swing.*;
import java.awt.*;

/** User: Jim Date: 1/30/13 Time: 10:58 PM */
public class CalcDisplay extends JTextField {

    public static final int PREF_WIDTH = 240, PREF_HEIGHT = 40;

    public CalcDisplay(){
        super("0");
        setMinimumSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        //TODO: set a nicely visible font for this display panel
    }
}
