package com.jamesgomez.calculator;

/*
 * User: Jim
 * Date: 2/5/13
 * Time: 5:38 PM
 */

/**
 * A simple class that stores Strings as Arithmetic tokens,
 * as well as the token type
 */
public class Token {

    public static final int NUM = 0, OP = 1, LEFT_PAREN = 2, RIGHT_PAREN = 3;

    /** The type of this token (NUM, OP, LEFT_PAREN, or RIGHT_PAREN) */
    public int type;
    /** The String value of this token */
    String value;

    public Token() {
        type = NUM;
        value = null;
    }

    public Token(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
