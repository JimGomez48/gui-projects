import javax.swing.*;
import java.awt.*;

/** User: Jim Date: 1/30/13 Time: 10:58 PM */
public class CalcDisplay extends JTextField {

    public static final int PREF_WIDTH = 250, PREF_HEIGHT = 50;

    public CalcDisplay(){
        super("0");
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));
        //TODO: set a nicely visible font for this display panel
    }
}
