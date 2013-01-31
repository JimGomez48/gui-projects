import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * User: Jim Date: 1/30/13 Time: 10:48 PM
 */
public class CalcPanel extends JPanel {

    public static JFrame frame;
    public static final int PANEL_WIDTH = 250, PANEL_HEIGHT = 250;

    private CalcDisplay display;

    public CalcPanel(LayoutManager manager) {
        super(manager, true);

        GridBagConstraints displayConstraints = new GridBagConstraints();
        display = new CalcDisplay();
        displayConstraints.fill = GridBagConstraints.HORIZONTAL;
        //displayConstraints.gridheight = CalcDisplay.PREF_HEIGHT;
        displayConstraints.gridx = 0;
        displayConstraints.gridy = 0;
        add(display, displayConstraints);
    }

    public static void buildGui() {
        frame = new JFrame("Calculator");
        frame.setResizable(false);
        CalcPanel panel = new CalcPanel(new GridBagLayout());
        frame.setContentPane(panel);
        frame.setSize(250, 250);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        CalcPanel.buildGui();
    }
}
