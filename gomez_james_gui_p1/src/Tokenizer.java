import java.util.ArrayList;
import java.util.Stack;

/*
 * User: Jim
 * Date: 2/5/13
 * Time: 4:25 PM
 */

/**
 * Contains static methods for tokenizing strings. Also contains methods for
 * classifying some character types.
 */
public class Tokenizer {

    /**
     * Takes in a String as its argument and returns a List of tokens suitable
     * for use in evaluating the arithmetic operation represented by the String.
     *
     * @param expression A string representing an arithmetic expression
     * @return A List of arithmetic tokens.
     */
    public static ArrayList<Token> tokenize(String expression) {

        ArrayList<Token> tokens = new ArrayList<Token>();
        String stringBuffer = "";
        Boolean currentIsNum = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

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

        if (!stringBuffer.equals("") || stringBuffer != null)
            tokens.add(new Token(Token.NUM, stringBuffer));

        //return tokens;
        return adjust(tokens);
    }

    /**
     * Adjusts the tokens so that any token representing negation is replaced with
     * two tokens: "-1" and "*". This allows for easier evaluation of the
     * expression that the tokens represent.
     *
     * @param tokens An ArrayList of tokens representing integers,
     *               arithmetic operators, and parentheses.
     * @return An ArrayList of adjusted tokens.
     */
    private static ArrayList<Token> adjust(ArrayList<Token> tokens) {
        ArrayList<Token> newList = new ArrayList<Token>();
        boolean isFirstElement = true;

        for (Token t : tokens) {

            if (t.type == Token.OP && t.value.equals("-")) {

                //if t is the first element of the list
                if (isFirstElement) {
                    isFirstElement = false;
                    newList.add(new Token(Token.NUM, "-1"));
                    newList.add(new Token(Token.OP, "*"));
                    continue;
                }

                Token previous = newList.get(newList.size() - 1);

                if (previous.value.equals("(") || previous.type == Token.OP) {
                    newList.add(new Token(Token.NUM, "-1"));
                    newList.add(new Token(Token.OP, "*"));
                }
                else
                    newList.add(t);

            }
            else
                newList.add(t);

            System.out.println(t.value);
        }
        return newList;
    }

    /**
     * Converts a list of tokens from infix order to postfix order.
     *
     * @param infix An ArrayList of tokens in infix order
     * @return An ArrayList of the tokens in postfix order
     */
    public static ArrayList<Token> infixToPostfix(ArrayList<Token> infix) {
        ArrayList<Token> postfix = new ArrayList<Token>(infix.size());
        Stack<Token> tokenStack = new Stack<Token>();

        for (Token t : infix) {

            if (t.type == Token.NUM) {
                postfix.add(t);
            }
            else if (t.type == Token.OP) {
                for (Token stackT : tokenStack) {
                    if (getOpPrecedence(stackT.value) >= getOpPrecedence(t.value)) {
                        postfix.add(tokenStack.pop());
                    }
                }
            }
            else if (t.type == Token.LEFT_PAREN) {
                tokenStack.push(t);
            }
            else if (t.type == Token.RIGHT_PAREN) {
                for (Token stackT : tokenStack) {
                    if (stackT.type == Token.LEFT_PAREN) {
                        tokenStack.pop();
                        break;
                    }
                    else
                        postfix.add(tokenStack.pop());
                }
            }

            for (Token stackT2 : tokenStack) {
                postfix.add(tokenStack.pop());
            }
            //TODO: validity testing for this method

        }

        return postfix;
    }

    private static int getOpPrecedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        else if (op.equals("*") || op.equals("/")) return 2;
        else return 0;
    }

    /**
     * Checks to see if the specified character is an arithmetic operator.
     *
     * @param c A character
     * @return True if the character represents an arithmetic operator,
     *         false otherwise.
     */
    public static boolean isOp(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') return true;

        return false;
    }

    /**
     * Checks to see if the specified character is a left parentheses.
     *
     * @param c A character
     * @return True if the character represents a left parentheses, false otherwise.
     */
    public static boolean isLP(char c) {
        if (c == '(') return true;

        return false;
    }

    /**
     * Checks to see if the specified character is a right parentheses.
     *
     * @param c A character
     * @return True if the character represents a right parentheses, false otherwise.
     */
    public static boolean isRP(char c) {
        if (c == ')') return true;

        return false;
    }

}
