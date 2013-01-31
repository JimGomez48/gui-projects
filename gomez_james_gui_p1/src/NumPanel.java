import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * User: Jim
 * Date: 1/31/13
 * Time: 10:33 AM
 */
public class NumPanel extends JPanel {

    public static final int PREF_WIDTH = 240, PREF_HEIGHT = 200;
    private CalcDisplay display;

    private CalcButton zero;
    private CalcButton one;
    private CalcButton two;
    private CalcButton three;
    private CalcButton four;
    private CalcButton five;
    private CalcButton six;
    private CalcButton seven;
    private CalcButton eight;
    private CalcButton nine;
    private CalcButton leftParen;
    private CalcButton rightParen;


    public NumPanel(CalcDisplay display) {
        super(new GridBagLayout(), true);
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        this.display = display;

        zero = new CalcButton("0");
        one = new CalcButton("1");
        two = new CalcButton("2");
        three = new CalcButton("3");
        four = new CalcButton("4");
        five = new CalcButton("5");
        six = new CalcButton("6");
        seven = new CalcButton("7");
        eight = new CalcButton("8");
        nine = new CalcButton("9");
        leftParen = new CalcButton("(");
        rightParen = new CalcButton(")");

        createActionListeners();
    }

    private void createActionListeners(){
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement 0 button listener
            }
        });

        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement 1 button listener
            }
        });
    }
}
