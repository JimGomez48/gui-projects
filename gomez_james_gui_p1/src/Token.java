/**
 * User: Jim
 * Date: 2/5/13
 * Time: 5:38 PM
 */
public class Token {

    public static final int NUM = 0, OP = 1, LEFT_PAREN = 2, RIGHT_PAREN = 3;

    public int type;
    String value;

    public Token() {}

    public Token(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
