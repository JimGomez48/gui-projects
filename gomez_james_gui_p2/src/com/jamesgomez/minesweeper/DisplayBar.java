package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DisplayBar extends JPanel {

    private DigitalRead unmarkedMinesRead;
    private DigitalRead timerRead;
    private JButton newGameButton;

    public DisplayBar() {
        super(true);
        setBackground(Color.LIGHT_GRAY);

        newGameButton = new JButton(new ImageIcon(ImageManager.FACE_SMILE));

        unmarkedMinesRead = new DigitalRead() {

            @Override
            public void draw(Graphics g) {
                g.drawImage(this.left, 0, 0, null);
                g.drawImage(this.center, DIGIT_WIDTH, 0, null);
                g.drawImage(this.right, 2 * DIGIT_WIDTH, 0, null);
            }
        };

        timerRead = new DigitalRead() {

            @Override
            public void draw(Graphics g) {
                g.drawImage(this.left, getWidth() - 3 * DIGIT_WIDTH, 0, null);
                g.drawImage(this.center, getWidth() - 2 * DIGIT_WIDTH, 0, null);
                g.drawImage(this.right, getWidth() - DIGIT_WIDTH, 0, null);
            }
        };

//        add(newGameButton, BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        unmarkedMinesRead.draw(g);
        timerRead.draw(g);
    }

    private abstract class DigitalRead implements Drawable {

        public static final int DIGIT_WIDTH = 13;
        public static final int DIGIT_HEIGHT = 23;
        public static final int WIDTH = 3 * DIGIT_WIDTH;
        public static final int HEIGHT = DIGIT_HEIGHT;

        private int hundredsDigit;
        private int tensDigit;
        private int onesDigit;

        protected BufferedImage left;
        protected BufferedImage center;
        protected BufferedImage right;

        public DigitalRead() {
            hundredsDigit = 0;
            tensDigit = 0;
            onesDigit = 0;

            left = ImageManager.TIME_0;
            center = ImageManager.TIME_0;
            right = ImageManager.TIME_0;
        }

        public void setRead(int number) {
            if (number < 0)
                System.out.println("Number " + number + " is too small to display");
            else if (number > 999)
                System.out.println("Number " + number + " is too large to display");
            else {
                hundredsDigit = number / 100;
                tensDigit = (number % 100) / 10;
                onesDigit = (number % 10);

                switch (hundredsDigit){
                    case 0: left = ImageManager.TIME_0; break;
                    case 1: left = ImageManager.TIME_1; break;
                    case 2: left = ImageManager.TIME_2; break;
                    case 3: left = ImageManager.TIME_3; break;
                    case 4: left = ImageManager.TIME_4; break;
                    case 5: left = ImageManager.TIME_5; break;
                    case 6: left = ImageManager.TIME_6; break;
                    case 7: left = ImageManager.TIME_7; break;
                    case 8: left = ImageManager.TIME_8; break;
                    case 9: left = ImageManager.TIME_9; break;
                }

                switch (tensDigit){
                    case 0: center = ImageManager.TIME_0; break;
                    case 1: center = ImageManager.TIME_1; break;
                    case 2: center = ImageManager.TIME_2; break;
                    case 3: center = ImageManager.TIME_3; break;
                    case 4: center = ImageManager.TIME_4; break;
                    case 5: center = ImageManager.TIME_5; break;
                    case 6: center = ImageManager.TIME_6; break;
                    case 7: center = ImageManager.TIME_7; break;
                    case 8: center = ImageManager.TIME_8; break;
                    case 9: center = ImageManager.TIME_9; break;
                }

                switch (onesDigit){
                    case 0: right = ImageManager.TIME_0; break;
                    case 1: right = ImageManager.TIME_1; break;
                    case 2: right = ImageManager.TIME_2; break;
                    case 3: right = ImageManager.TIME_3; break;
                    case 4: right = ImageManager.TIME_4; break;
                    case 5: right = ImageManager.TIME_5; break;
                    case 6: right = ImageManager.TIME_6; break;
                    case 7: right = ImageManager.TIME_7; break;
                    case 8: right = ImageManager.TIME_8; break;
                    case 9: right = ImageManager.TIME_9; break;
                }
            }
        }

        @Override
        public abstract void draw(Graphics g);

    }

}
