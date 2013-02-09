import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;

/*
 * User: Jim
 * Date: 2/5/13
 * Time: 4:25 PM
 */

/**
 * Contains static methods for tokenizing strings and manipulating those tokens.
 * Also contains methods for classifying some character types.
 */
public class Tokenizer {

    /**
     * Takes in a String representing an arithmetic expression as its argument and
     * returns a List of tokens in the order that they are found within the String
     * expression. If incorrect infix syntax is detected, a ParseException is thrown.
     *
     * @param expression A string representing an arithmetic expression
     * @return An ArrayList of arithmetic tokens.
     * @throws ParseException
     */
    public static ArrayList<Token> tokenize(String expression) throws
            ParseException {

        ArrayList<Token> tokens = new ArrayList<Token>();
        String stringBuffer = "";
        Boolean currentIsNum = false, currentIsOp = false;
        int lpCount = 0, rpCount = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                currentIsNum = true;
                currentIsOp = false;
                stringBuffer += String.valueOf(c);
            }
            else {

                if (currentIsNum) {
                    currentIsNum = false;
                    tokens.add(new Token(Token.NUM, stringBuffer));
                    stringBuffer = "";
                }

                if (isOp(c)) {
                    if (currentIsOp)
                        throw new ParseException("Invalid Infix Expression: more " +
                                "than one operator in a row.", i);

                    tokens.add(new Token(Token.OP, String.valueOf(c)));
                    currentIsOp = true;
                }
                else if (isLP(c)) {
                    tokens.add(new Token(Token.LEFT_PAREN, String.valueOf(c)));
                    lpCount++;
                    currentIsOp = false;
                }
                else if (isRP(c)) {
                    tokens.add(new Token(Token.RIGHT_PAREN, String.valueOf(c)));
                    rpCount++;
                    currentIsOp = false;
                }

            }

        }

        if (lpCount != rpCount)
            throw new ParseException("Invalid infix expression: Parentheses do not" +
                    " match.", expression.length());

        //one more token left in stringBuffer to add.
        if (!stringBuffer.equals("") || stringBuffer != null)
            tokens.add(new Token(Token.NUM, stringBuffer));

        return infixToPostfix(adjust(tokens));
    }

    /**
     * Adjusts the tokens so that any token representing negation is replaced with
     * two tokens: "-1" and "*". This allows for easier evaluation of the
     * expression that the tokens represent. Correct infix syntax is assumed.
     *
     * @param tokens An ArrayList of tokens representing integers,
     *               arithmetic operators, and parentheses.
     * @return An ArrayList of adjusted tokens.
     */
    public static ArrayList<Token> adjust(ArrayList<Token> tokens) {
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
            else {
                isFirstElement = false;
                newList.add(t);
            }

        }
        return newList;
    }

    /**
     * An implementation of the Shunting Yard algorithm. Converts a list of tokens
     * from infix order to postfix order. Correct infix syntax is assumed.
     *
     * @param infix An ArrayList of tokens in infix order
     * @return An ArrayList of the tokens in postfix order
     */
    public static ArrayList<Token> infixToPostfix(ArrayList<Token> infix) {
        ArrayList<Token> postfix = new ArrayList<Token>(infix.size());
        Stack<Token> tokenStack = new Stack<Token>();

        for (Token currentToken : infix) {

            if (currentToken.type == Token.NUM) {
                postfix.add(currentToken);
            }
            else if (currentToken.type == Token.OP) {
                Stack<Token> temp = new Stack<Token>();

                while (!tokenStack.empty()) {
                    Token top = tokenStack.pop();

                    /* if top is an operator with precedence >= currentToken,
                    add to postfix. Else, add to a temporary stack. */
                    if (top.type == Token.OP && getOpPrecedence(top.value) >
                            getOpPrecedence(currentToken.value))
                        postfix.add(top);
                    else
                        temp.push(top);

                }

                /* replace all tokens in the temp stack onto the tokenStack in LIFO
                order */
                while (!temp.empty())
                    tokenStack.push(temp.pop());

                tokenStack.push(currentToken);
            }
            else if (currentToken.type == Token.LEFT_PAREN) {
                tokenStack.push(currentToken);
            }
            else if (currentToken.type == Token.RIGHT_PAREN) {
                while (!tokenStack.empty()) {
                    Token token = tokenStack.pop();

                    if (token.type == Token.LEFT_PAREN)
                        break;
                    else
                        postfix.add(token);
                }
            }
            //TODO: throw parse exception if bad syntax is detected
        }

        while (!tokenStack.empty()) {
            postfix.add(tokenStack.pop());
        }

        return postfix;
    }

    /**
     * Takes an ArrayList of arithmetic tokens in postfix order and evaluates the
     * expression that the list represents.
     *
     * @param tokens an ArrayList of tokens in postfix order
     * @return A String whose value represents the evaluation of the tokens
     */
    public static String evaluate(ArrayList<Token> tokens) {
        String answer = "";
        //TODO: evaluate a list of postfix order tokens
        return answer;
    }

    /**
     * Takes a String representing an arithmetic operator and determines its order
     * of precedence.
     *
     * @param op A string representing an arithmetic operator
     * @return An integer representing the precedence of String op (0, 1,
     *         or 2). Higher values have higher precedence than lower values.
     */
    public static int getOpPrecedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        else if (op.equals("*") || op.equals("/")) return 2;
        return 0;
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
