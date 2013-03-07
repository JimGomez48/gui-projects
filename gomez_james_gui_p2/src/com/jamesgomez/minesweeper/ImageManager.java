package com.jamesgomez.minesweeper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {

    public static BufferedImage COVERED;
    public static BufferedImage MARKED;
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

    public static void LoadResources() {
        try {
            COVERED = ImageIO.read(new File(toFullPath("blank")));
            MARKED = ImageIO.read(new File(toFullPath("bombflagged")));
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
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String toFullPath(String filename) {
        return new String("gomez_james_gui_p2/res/" + filename + ".gif");
    }
}
