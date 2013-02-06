import java.util.ArrayList;

/**
 * User: Jim
 * Date: 2/5/13
 * Time: 4:25 PM
 */
public class Tokenizer {

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

        return adjust(tokens);
    }

    private static ArrayList<Token> adjust(ArrayList<Token> tokens) {
        ArrayList<Token> newList = new ArrayList<Token>();
        boolean isFirstElement = true;

        //TODO: fix bug: does not get full list of tokens. last token is omitted.
        for (Token t : tokens) {

            /*if (t.type == Token.OP && t.value.equals("-")) {

                //if t is the first element of the list
                if (isFirstElement) {
                    isFirstElement = false;
                    newList.add(new Token(Token.NUM, "-1"));
                    newList.add(new Token(Token.OP, "*"));
                    continue;
                }

                Token previous = newList.get(newList.size() - 1);

                if (previous.value == "(" || previous.type == Token.OP) {
                    newList.add(new Token(Token.NUM, "-1"));
                    newList.add(new Token(Token.OP, "*"));
                }
                else
                    newList.add(t);

            }
            else
                newList.add(t);*/

            System.out.println(t.value);
        }
        return newList;
    }

    public static boolean isOp(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') return true;

        return false;
    }

    public static boolean isLP(char c) {
        if (c == '(') return true;

        return false;
    }

    public static boolean isRP(char c) {
        if (c == ')') return true;

        return false;
    }

}
