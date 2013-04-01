package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DisplayBar extends JPanel {

    private DigitalRead unmarkedMinesRead;
    private DigitalRead timerRead;
    private JButton newGameButton;

    public DisplayBar(LayoutManager manager) {
        super(manager, true);
        setBackground(Color.GRAY);

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
        public static final int HEIGHT = 23;

        private int leftNum;
        private int centerNum;
        private int rightNum;

        protected BufferedImage left;
        protected BufferedImage center;
        protected BufferedImage right;

        public DigitalRead() {
            leftNum = 0;
            centerNum = 0;
            rightNum = 0;

            left = ImageManager.TIME_0;
            center = ImageManager.TIME_0;
            right = ImageManager.TIME_0;
        }

        public void setRead(int number) {
//            left = ImageManager.TIME_0;
//            center = ImageManager.TIME_0;
//            right = ImageManager.TIME_0;
        }

        @Override
        public abstract void draw(Graphics g);

    }

}
