package com.jamesgomez.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DisplayBar extends JPanel {

    private final UnmarkedMinesRead unmarkedMinesRead;
    private final TimerRead timerRead;
    private final FaceButton faceButton;

    private final Timer timer;

    public DisplayBar() {
        super(new BorderLayout(2, 2), true);
        setBackground(Color.LIGHT_GRAY);

        faceButton = new FaceButton();
        unmarkedMinesRead = new UnmarkedMinesRead();
        timerRead = new TimerRead();

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timerRead.setRead(timerRead.getRead() + 1);
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if (faceButton.isPointInButton(e.getX(), e.getY())) {
                    faceButton.setImage(faceButton.FACE_OOH);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                if (faceButton.isPointInButton(e.getX(), e.getY())) {
                    faceButton.setImage(faceButton.FACE_SMILE);
                    Game.getInstance().newGame(Game.getInstance().getDifficulty());
                }
            }
        });

        reset();
    }

    public boolean isTimerStarted() {
        return timer.isRunning();
    }

    public void startTimer(){
        timer.start();
    }

    public void incrementMineRead() {
        unmarkedMinesRead.setRead(unmarkedMinesRead.getRead() + 1);
    }

    public void decrementMineRead() {
        unmarkedMinesRead.setRead(unmarkedMinesRead.getRead() - 1);
    }

    public void reset(){
        timer.stop();
        timerRead.setRead(0);
        unmarkedMinesRead.setRead(Game.getInstance().getGameBoard().getNumMines());
        faceButton.setImage(FaceButton.FACE_SMILE);
    }

    public void setLost(){
        faceButton.setImage(FaceButton.FACE_DEAD);
        timer.stop();
    }

    public void setWon(){
        faceButton.setImage(FaceButton.FACE_WIN);
        unmarkedMinesRead.setRead(0);
        timer.stop();
    }

    public void setFaceClicked(boolean clicked){
        if (clicked)
            faceButton.setImage(FaceButton.FACE_OOH);
        else
            faceButton.setImage(FaceButton.FACE_SMILE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        faceButton.draw(g);
        unmarkedMinesRead.draw(g);
        timerRead.draw(g);
        repaint();
    }

    private class FaceButton implements Drawable {

        public final int WIDTH = ImageManager.FACE_SMILE.getWidth();
        public final int HEIGHT = ImageManager.FACE_SMILE.getHeight();

        public static final int FACE_SMILE = 0;
        public static final int FACE_OOH = 1;
        public static final int FACE_WIN = 2;
        public static final int FACE_DEAD = 3;

        private BufferedImage currentImage;

        public FaceButton() {
            currentImage = ImageManager.FACE_SMILE;
        }

        public void setImage(int num) {
            switch (num){
                case FACE_SMILE: currentImage = ImageManager.FACE_SMILE; break;
                case FACE_OOH: currentImage = ImageManager.FACE_OOH; break;
                case FACE_WIN: currentImage = ImageManager.FACE_WIN; break;
                case FACE_DEAD: currentImage = ImageManager.FACE_DEAD; break;
            }
        }

        public boolean isPointInButton(float x, float y) {
            int X = (getWidth() / 2) - (WIDTH / 2);
            int Y= (getHeight() / 2) - (HEIGHT / 2);

            return X <= x && X + WIDTH >= x && Y <= y && Y + HEIGHT >= y;
        }

        @Override
        public void draw(Graphics g) {
            g.drawImage(currentImage, (getWidth() / 2) - (WIDTH / 2),
                    (getHeight() / 2) - (HEIGHT / 2), null);
        }
    }

    private class UnmarkedMinesRead extends DigitalRead {

        @Override
        public void draw(Graphics g) {
            g.drawImage(this.left, 0, (getHeight() / 2) - (HEIGHT / 2), null);
            g.drawImage(this.center, DIGIT_WIDTH, (getHeight() / 2) - (HEIGHT / 2)
                    , null);
            g.drawImage(this.right, 2 * DIGIT_WIDTH, (getHeight() / 2) - (HEIGHT /
                    2), null);
        }
    }

    private class TimerRead extends DigitalRead {

        @Override
        public void draw(Graphics g) {
            g.drawImage(this.left, getWidth() - WIDTH,
                    (getHeight() / 2) - (HEIGHT / 2), null);
            g.drawImage(this.center, getWidth() - 2 * DIGIT_WIDTH,
                    (getHeight() / 2) - (HEIGHT / 2), null);
            g.drawImage(this.right, getWidth() - DIGIT_WIDTH,
                    (getHeight() / 2) - (HEIGHT / 2), null);
        }
    }

}
