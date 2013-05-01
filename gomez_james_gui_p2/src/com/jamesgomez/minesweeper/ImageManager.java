package com.jamesgomez.minesweeper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** Static class used to create and hold public instances of image files. */
public class ImageManager {

    public static BufferedImage COVERED;
    public static BufferedImage MARKED;
    public static BufferedImage MIS_MARKED;
    public static BufferedImage BOMB_DEATH;
    public static BufferedImage BOMB_REVEALED;
    public static BufferedImage OPEN_0;
    public static BufferedImage OPEN_1;
    public static BufferedImage OPEN_2;
    public static BufferedImage OPEN_3;
    public static BufferedImage OPEN_4;
    public static BufferedImage OPEN_5;
    public static BufferedImage OPEN_6;
    public static BufferedImage OPEN_7;
    public static BufferedImage OPEN_8;
    public static BufferedImage TIME_0;
    public static BufferedImage TIME_1;
    public static BufferedImage TIME_2;
    public static BufferedImage TIME_3;
    public static BufferedImage TIME_4;
    public static BufferedImage TIME_5;
    public static BufferedImage TIME_6;
    public static BufferedImage TIME_7;
    public static BufferedImage TIME_8;
    public static BufferedImage TIME_9;
    public static BufferedImage FACE_SMILE;
    public static BufferedImage FACE_OOH;
    public static BufferedImage FACE_WIN;
    public static BufferedImage FACE_DEAD;

    /** Loads image resources into memory and sets public references to them. */
    public static void LoadResources() {
        try {
            COVERED = ImageIO.read(new File(toFullPath("blank")));
            MARKED = ImageIO.read(new File(toFullPath("bombflagged")));
            MIS_MARKED = ImageIO.read(new File(toFullPath("bombmisflagged")));
            BOMB_DEATH = ImageIO.read(new File(toFullPath("bombdeath")));
            BOMB_REVEALED = ImageIO.read(new File(toFullPath("bombrevealed")));
            OPEN_0 = ImageIO.read(new File(toFullPath("open0")));
            OPEN_1 = ImageIO.read(new File(toFullPath("open1")));
            OPEN_2 = ImageIO.read(new File(toFullPath("open2")));
            OPEN_3 = ImageIO.read(new File(toFullPath("open3")));
            OPEN_4 = ImageIO.read(new File(toFullPath("open4")));
            OPEN_5 = ImageIO.read(new File(toFullPath("open5")));
            OPEN_6 = ImageIO.read(new File(toFullPath("open6")));
            OPEN_7 = ImageIO.read(new File(toFullPath("open7")));
            OPEN_8 = ImageIO.read(new File(toFullPath("open8")));
            TIME_0 = ImageIO.read(new File(toFullPath("time0")));
            TIME_1 = ImageIO.read(new File(toFullPath("time1")));
            TIME_2 = ImageIO.read(new File(toFullPath("time2")));
            TIME_3 = ImageIO.read(new File(toFullPath("time3")));
            TIME_4 = ImageIO.read(new File(toFullPath("time4")));
            TIME_5 = ImageIO.read(new File(toFullPath("time5")));
            TIME_6 = ImageIO.read(new File(toFullPath("time6")));
            TIME_7 = ImageIO.read(new File(toFullPath("time7")));
            TIME_8 = ImageIO.read(new File(toFullPath("time8")));
            TIME_9 = ImageIO.read(new File(toFullPath("time9")));
            FACE_SMILE = ImageIO.read(new File(toFullPath("facesmile")));
            FACE_OOH = ImageIO.read(new File(toFullPath("faceooh")));
            FACE_WIN = ImageIO.read(new File(toFullPath("facewin")));
            FACE_DEAD = ImageIO.read(new File(toFullPath("facedead")));
        }
        catch (IOException e) {
            System.err.println("ImageManager failed to read an image file");
            e.printStackTrace();
        }
    }

    private static String toFullPath(String filename) {
        return new String("gomez_james_gui_p2/res/" + filename + ".gif");
    }
}
