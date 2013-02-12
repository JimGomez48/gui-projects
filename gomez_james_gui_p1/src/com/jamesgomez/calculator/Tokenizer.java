package com.jamesgomez.calculator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;

import com.jamesgomez.calculator.CalcPanel.CalcParseException;

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

    //flag for turning debug messages on or off
    private static final boolean debug = true;

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
            CalcParseException {

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
                        throw new CalcParseException("More than one operator in a " +
                                "row.");

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
            throw new CalcParseException("Parentheses do not match.");

        //If last token is NUM, add it to tokens as new NUM token.
        if (!stringBuffer.equals("") && stringBuffer != null)
            tokens.add(new Token(Token.NUM, stringBuffer));

        //debug code. turn on with debug boolean.
        if (debug) {
            System.out.println("\nINFIX Tokens");
            for (Token t : tokens)
                System.out.print(t.value + " ");
            System.out.println();
        }

        return tokens;
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
    public static ArrayList<Token> adjustNegatives(ArrayList<Token> tokens) {
        ArrayList<Token> adjustedList = new ArrayList<Token>();
        boolean isFirstElement = true;

        for (Token t : tokens) {

            if (t.type == Token.OP && t.value.equals("-")) {

                //if t is the first element of the list
                if (isFirstElement) {
                    isFirstElement = false;
                    adjustedList.add(new Token(Token.NUM, "-1"));
                    adjustedList.add(new Token(Token.OP, "*"));
                    continue;
                }

                Token previous = adjustedList.get(adjustedList.size() - 1);

                if (previous.value.equals("(") || previous.type == Token.OP) {
                    adjustedList.add(new Token(Token.NUM, "-1"));
                    adjustedList.add(new Token(Token.OP, "*"));
                }
                else
                    adjustedList.add(t);

            }
            else {
                isFirstElement = false;
                adjustedList.add(t);
            }

        }

        //debug code. turn on with debug boolean.
        if (debug) {
            System.out.println("\nADJUSTED Tokens");
            for (Token t : adjustedList)
                System.out.print(t.value + " ");
            System.out.println();
        }

        return adjustedList;
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
            //TODO: fix error in op precedence and postfix op ordering
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
        }

        while (!tokenStack.empty()) {
            postfix.add(tokenStack.pop());
        }

        //debug code. turn on with debug boolean.
        if (debug) {
            System.out.println("\nPOSTFIX Tokens");
            for (Token t : postfix)
                System.out.print(t.value + " ");
            System.out.println();
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
    public static String evaluatePostfix(ArrayList<Token> tokens) {
        Stack<Token> stack = new Stack<Token>();

        for (Token t : tokens) {

            if (t.type == Token.NUM)
                stack.push(t);
            else if (t.type == Token.OP) {
                double v2 = Double.parseDouble(stack.pop().value);
                double v1 = Double.parseDouble(stack.pop().value);
                double result = 0;

                if (t.value.equals("+")) {
                    result = v1 + v2;
                }
                else if (t.value.equals("-")) {
                    result = v1 - v2;
                }
                else if (t.value.equals("*")) {
                    result = v1 * v2;
                }
                else if (t.value.equals("-")) {
                    result = v1 / v2;
                }

                stack.push(new Token(Token.NUM, Double.toString(result)));
            }
        }

        return stack.pop().value;
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
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    /**
     * Checks to see if the specified character is a left parentheses.
     *
     * @param c A character
     * @return True if the character represents a left parentheses, false otherwise.
     */
    public static boolean isLP(char c) {
        return c == '(';
    }

    /**
     * Checks to see if the specified character is a right parentheses.
     *
     * @param c A character
     * @return True if the character represents a right parentheses, false otherwise.
     */
    public static boolean isRP(char c) {
        return c == ')';
    }

}
