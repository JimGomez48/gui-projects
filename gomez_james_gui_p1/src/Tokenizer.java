import java.util.ArrayList;

/**
 * User: Jim
 * Date: 2/5/13
 * Time: 4:25 PM
 */
public class Tokenizer {

    //TODO: finish this tokenize method
    public static ArrayList<Token> tokenize(String s) {
        ArrayList<Token> tokens = new ArrayList<Token>(s.length());
        String buffer = new String("");

        for (int i = 0; i < s.length(); i++) {

            Token t = null;
            try {
                t = new Token();
                char c = s.charAt(i);

                if (isOp(c)) {
                    t.type = Token.OP;
                    t.value = String.valueOf(c);
                } else if (isLP(c)) {

                } else if (isRP(c)) {

                } else if (Character.isDigit(c)) {

                } else {
                    throw new Exception("Invalid character entered in display.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            tokens.add(t);
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
