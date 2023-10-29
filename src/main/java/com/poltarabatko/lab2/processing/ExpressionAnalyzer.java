package com.poltarabatko.lab2.processing;

import com.poltarabatko.lab2.exception.UnexpectedElementException;
import com.poltarabatko.lab2.processing.ExpressionToken;
import com.poltarabatko.lab2.processing.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * ExpressionAnalyzer implementation
 * @author poltarabatko
 */
public class ExpressionAnalyzer {
    private List<ExpressionToken> tokens;
    private int position;

    /**
     * Analyzes the given mathematical expression and generates a list of tokens.
     *
     * @param expressionText The mathematical expression to be analyzed.
     * @return A list of tokens parsed from the expression.
     */
    public List<ExpressionToken> analyze(String expressionText) {
        tokens = new ArrayList<>();
        position = 0;

        while (position < expressionText.length()) {
            char symbol = expressionText.charAt(position);
            switch (symbol) {
                case '(':
                    tokens.add(new ExpressionToken(TokenType.LEFT_BRACKET, symbol));
                    position++;
                    break;
                case ')':
                    tokens.add(new ExpressionToken(TokenType.RIGHT_BRACKET, symbol));
                    position++;
                    break;
                case '+':
                    tokens.add(new ExpressionToken(TokenType.ADDITION, symbol));
                    position++;
                    break;
                case '-':
                    tokens.add(new ExpressionToken(TokenType.SUBTRACTION, symbol));
                    position++;
                    break;
                case '*':
                    tokens.add(new ExpressionToken(TokenType.MULTIPLICATION, symbol));
                    position++;
                    break;
                case '/':
                    tokens.add(new ExpressionToken(TokenType.DIVISION, symbol));
                    position++;
                    break;
                default:
                    position = processCharacter(expressionText, position, symbol, tokens);
            }
        }
        tokens.add(new ExpressionToken(TokenType.END, ""));
        return tokens;
    }

    /**
     * Retrieves the next token in the list.
     *
     * @return The next token.
     */
    public ExpressionToken nextToken() {
        if (position < tokens.size()) {
            return tokens.get(position++);
        }
        return new ExpressionToken(TokenType.END, "");
    }

    /**
     * Moves the position in the token list backward by one.
     */
    public void back() {
        if (position > 0) {
            position--;
        }
    }

    /**
     * Retrieves the current position in the token list.
     *
     * @return The current position in the token list.
     */
    public int getPosition() {
        return position;
    }

    private static int processCharacter(String expressionText, int position, char symbol, List<ExpressionToken> tokens) {
        if (Character.isLetter(symbol)) {
            return processLetters(expressionText, position, tokens);
        } else if (Character.isDigit(symbol) || symbol == '.') {
            return processNumbers(expressionText, position, tokens);
        } else {
            if (symbol != ' ') {
                throw new UnexpectedElementException("Unexpected character: " + symbol);
            }
            return ++position;
        }
    }

    /**
     * Processes a sequence of letters (potential variable or function name) in the expression text.
     *
     * @param expressionText The mathematical expression text.
     * @param position       The current position in the expression text.
     * @param tokens         The list of tokens to which the processed token will be added.
     * @return The updated position after processing the sequence of letters.
     */
    private static int processLetters(String expressionText, int position, List<ExpressionToken> tokens) {
        StringBuilder varBuilder = new StringBuilder();
        varBuilder.append(expressionText.charAt(position));
        position++;

        while (position < expressionText.length() &&
                (Character.isLetterOrDigit(expressionText.charAt(position)) || expressionText.charAt(position) == '_')) {
            varBuilder.append(expressionText.charAt(position));
            position++;
        }

        String var = varBuilder.toString();
        if (var.equals("sin") || var.equals("cos") || var.equals("tan") || var.equals("cot")) {
            tokens.add(new ExpressionToken(TokenType.FUNCTION, var));
        } else {
            tokens.add(new ExpressionToken(TokenType.VARIABLE, var));
        }

        return position;
    }

    /**
     * Processes a sequence of numbers in the expression text.
     *
     * @param expressionText The mathematical expression text.
     * @param position       The current position in the expression text.
     * @param tokens         The list of tokens to which the processed token will be added.
     * @return The updated position after processing the sequence of numbers.
     */
    private static int processNumbers(String expressionText, int position, List<ExpressionToken> tokens) {
        StringBuilder numBuilder = new StringBuilder();
        numBuilder.append(expressionText.charAt(position));
        position++;

        boolean hasDecimal = false;
        while (position < expressionText.length()) {
            char currentChar = expressionText.charAt(position);
            if (Character.isDigit(currentChar)) {
                numBuilder.append(currentChar);
                position++;
            } else if (currentChar == '.' && !hasDecimal) {
                numBuilder.append(currentChar);
                hasDecimal = true;
                position++;
            } else {
                break;
            }
        }

        tokens.add(new ExpressionToken(TokenType.NUMBER, numBuilder.toString()));
        return position;
    }

}
