package com.jamesgomez.minesweeper;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class DigitalRead implements Drawable{

    public static final int DIGIT_WIDTH = ImageManager.TIME_0.getWidth();
    public static final int DIGIT_HEIGHT = ImageManager.TIME_0.getHeight();
    public static final int WIDTH = 3 * DIGIT_WIDTH;
    public static final int HEIGHT = DIGIT_HEIGHT;

    private int currentRead;

    protected BufferedImage left;
    protected BufferedImage center;
    protected BufferedImage right;

    public DigitalRead() {
        currentRead = 0;
        left = ImageManager.TIME_0;
        center = ImageManager.TIME_0;
        right = ImageManager.TIME_0;
    }

    public int getRead(){
        return currentRead;
    }

    public void setRead(int number) {
        if (number < 0)
            System.err.println("Number " + number + " is too small to display");
        else if (number > 999)
            System.err.println("Number " + number + " is too large to display");
        else {
            currentRead = number;

            switch (getHundredsDigit(currentRead)){
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
                default: left = ImageManager.TIME_0;
            }

            switch (getTensDigit(currentRead)){
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
                default: left = ImageManager.TIME_0;
            }

            switch (getOnesDigit(currentRead)){
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
                default: left = ImageManager.TIME_0;
            }
        }
    }

    private int  getHundredsDigit(int number){
        return number / 100;
    }

    private int  getTensDigit(int number){
        return (number % 100) / 10;
    }

    private int getOnesDigit(int number){
        return (number % 10);
    }

    @Override
    public abstract void draw(Graphics g);
}
