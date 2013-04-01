package com.jamesgomez.minesweeper;

import java.awt.*;

/** Provides drawing behavior to objects that are not a Swing {@link Component}. */
public interface Drawable {

    /** Draws this object. */
    public void draw(Graphics g);

}
