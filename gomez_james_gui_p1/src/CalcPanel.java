import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** User: Jim Date: 1/30/13 Time: 10:48 PM */
public class CalcPanel extends JPanel {

    public static final int PANEL_WIDTH = 250, PANEL_HEIGHT = 250;
    private static JFrame frame;
    private NumPanel numPanel;
    private OpPanel opPanel;

    private CalcDisplay display;

    public CalcPanel(LayoutManager manager) {
        super(manager, true);

        //create panels and a constraints objects
        GridBagConstraints constraints = new GridBagConstraints();
        display = new CalcDisplay();
        numPanel = new NumPanel(display);
        opPanel = new OpPanel(display);

        //setup and attach display
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(display, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        //constraints.weightx = 70;
        //constraints.fill  = GridBagConstraints.HORIZONTAL;
        add(numPanel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        //constraints.weightx = 30;
        //constraints.fill  = GridBagConstraints.HORIZONTAL;
        add(opPanel, constraints);

    }

    public static void buildGui() {
        frame = new JFrame("Calculator");
        //frame.setResizable(false);
        CalcPanel panel = new CalcPanel(new GridBagLayout());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(260, 280));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        CalcPanel.buildGui();
    }
}
