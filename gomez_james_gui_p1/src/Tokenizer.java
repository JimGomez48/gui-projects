import java.util.ArrayList;

/**
 * User: Jim
 * Date: 2/5/13
 * Time: 4:25 PM
 */
public class Tokenizer {

    //TODO: finish this tokenize method
    public static ArrayList<Token> tokenize(String s) {

        ArrayList<Token> tokens = new ArrayList<Token>();
        String stringBuffer = "";
        Boolean currentIsNum = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {

                currentIsNum = true;
                stringBuffer += String.valueOf(c);
            }
            else {

                if (currentIsNum) {
                    currentIsNum = false;
                    tokens.add(new Token(Token.NUM, stringBuffer));
                    stringBuffer = "";
                }

                if (isOp(c))
                    tokens.add(new Token(Token.OP, String.valueOf(c)));
                else if (isLP(c))
                    tokens.add(new Token(Token.LEFT_PAREN, String.valueOf(c)));
                else if (isRP(c))
                    tokens.add(new Token(Token.RIGHT_PAREN, String.valueOf(c)));

            }

        }

        return tokens;
    }

    private static boolean isOp(char c) {

        if (c == '+' || c == '-' || c == '*' || c == '/') return true;

        return false;
    }

    private static boolean isLP(char c) {
        if (c == '(') return true;

        return false;
    }

    private static boolean isRP(char c) {
        if (c == ')') return true;

        return false;
    }

}
