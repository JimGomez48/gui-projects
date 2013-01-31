import javax.swing.*;
import java.util.ArrayList;

/** User: Jim Date: 1/30/13 Time: 10:48 PM */
public class CalcPanel extends JPanel {

    public static JFrame frame;

    private ArrayList<CalcButton> numButtons;
    private CalcButton add;
    private CalcButton subtract;
    private CalcButton multiply;
    private CalcButton divide;
    private CalcButton leftParens;
    private CalcButton rightParens;
    private CalcDisplay display;

    public CalcPanel() {
        super(true);

        display = new CalcDisplay();
        add(display);

        numButtons = new ArrayList<CalcButton>(10);

        //Add number buttons to the numButtons arraylist, then add them to the panel
        for (int i = 0; i < 10; i++) {
            CalcButton button = new CalcButton(String.valueOf(i));
            numButtons.add(button);
            add(button);
        }

        add = new CalcButton("+");
        add(add);
        subtract = new CalcButton("-");
        add(subtract);
        multiply = new CalcButton("*");
        add(multiply);
        divide = new CalcButton("/");
        add(divide);
        leftParens = new CalcButton("(");
        add(leftParens);
        rightParens = new CalcButton(")");
        add(rightParens);
    }

    public static void buildGui() {
        frame = new JFrame("Calculator");
        CalcPanel panel = new CalcPanel();
        frame.setContentPane(panel);
        frame.setSize(250, 250);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        CalcPanel.buildGui();
    }
}
