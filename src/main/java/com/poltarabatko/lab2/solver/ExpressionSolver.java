package com.poltarabatko.lab2.solver;

import com.poltarabatko.lab2.exception.UnexpectedElementException;
import com.poltarabatko.lab2.processing.ExpressionToken;
import com.poltarabatko.lab2.processing.TokenType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author poltarabatko
 */
public class ExpressionSolver {
    private int position;
    public List<ExpressionToken> tokens;
    private Map<String, Double> variables;

    /**
     * Constructs an ExpressionSolver object with a list of tokens representing a mathematical expression.
     *
     * @param tokens The list of tokens parsed from the mathematical expression.
     */
    public ExpressionSolver(List<ExpressionToken> tokens) {
        this.tokens = tokens;
        this.position = 0;
        this.variables = new HashMap<>();
    }

    /**
     * Initiates the evaluation of the mathematical expression.
     *
     * @return The result of the mathematical expression after evaluation.
     */
    public double solve() {
        return expressions();
    }

    /**
     * Parses and evaluates the expression's addition and subtraction operations.
     *
     * @return The calculated result after addition and subtraction operations.
     */
    private double expressions() {
        if (getNextToken().getType() == TokenType.END) {
            return 0;
        } else {
            back();
            return plusMinus();
        }
    }

    /**
     * Handles addition and subtraction operations.
     *
     * @return The result of the addition and subtraction operations.
     */
    private double plusMinus() {
        double value = multDiv();
        while (true) {
            ExpressionToken token = getNextToken();
            switch (token.getType()) {
                case ADDITION:
                    value += multDiv();
                    break;
                case SUBTRACTION:
                    value -= multDiv();
                    break;
                case END:
                case RIGHT_BRACKET:
                    back();
                    return value;
                default:
                    throw new UnexpectedElementException("Unexpected token: " + token.getValue()
                            + " at position: " + position);
            }
        }
    }

    /**
     * Handles multiplication and division operations.
     *
     * @return The result of the multiplication and division operations.
     */
    private double multDiv() {
        double value = factor();
        while (true) {
            ExpressionToken token = getNextToken();
            switch (token.getType()) {
                case MULTIPLICATION:
                    value *= factor();
                    break;
                case DIVISION:
                    double divisor = factor();
                    if (divisor == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    value /= divisor;
                    break;
                case END:
                case RIGHT_BRACKET:
                case ADDITION:
                case SUBTRACTION:
                    back();
                    return value;
                default:
                    throw new UnexpectedElementException("Unexpected token: " + token.getValue()
                            + " at position: " + position);
            }
        }
    }

    /**
     * Evaluates the factors including numbers, variables, and function calls.
     *
     * @return The result of evaluating the factors within the expression.
     */
    private double factor() {
        ExpressionToken token = getNextToken();
        switch (token.getType()) {
            case NUMBER:
                return Double.parseDouble(token.getValue());
            case VARIABLE:
                String varName = token.getValue();
                if (!variables.containsKey(varName)) {
                    double varValue = requestVariableValue(varName);
                    variables.put(varName, varValue);
                }
                return variables.get(varName);
            case LEFT_BRACKET:
                double value = plusMinus();
                token = getNextToken();
                if (token.getType() != TokenType.RIGHT_BRACKET) {
                    throw new UnexpectedElementException("Unexpected token: " + token.getValue()
                            + " at position: " + position);
                }
                return value;
            case FUNCTION:
                String functionName = token.getValue();
                double argument = factor();
                return executeFunction(functionName, argument);
            case SUBTRACTION:
                ExpressionToken nextToken = getNextToken();
                if (nextToken.getType() == TokenType.NUMBER) {
                    return -Double.parseDouble(nextToken.getValue());
                } else {
                    back();
                    return -factor();
                }
            default:
                throw new UnexpectedElementException("Unexpected token: " + token.getValue()
                        + " at position: " + position);
        }
    }

    /**
     * Evaluates unary operators, specifically the unary minus.
     *
     * @return The value after handling the unary minus operation.
     */
    private double unary() {
        ExpressionToken token = getNextToken();
        if (token.getType() == TokenType.SUBTRACTION) {
            return -1 * factor(); // Applies the unary minus to the factor
        }
        back();
        return factor();
    }

    /**
     * Requests user input for the variable's value.
     *
     * @param varName The name of the variable for which the value is requested.
     * @return The value entered by the user for the specified variable.
     */
    private double requestVariableValue(String varName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение переменной " + varName + " : ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Пожалуйста, введите действительное значение.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    /**
     * Executes the specified mathematical function on the given argument.
     *
     * @param functionName The name of the mathematical function to execute.
     * @param argument The argument on which the function is applied.
     * @return The result after applying the function on the argument.
     */
    private double executeFunction(String functionName, double argument) {
        switch (functionName) {
            case "sin":
                return Math.sin(argument);
            case "cos":
                return Math.cos(argument);
            case "tan":
                return Math.tan(argument);
            case "cot":
                return 1.0 / Math.tan(argument);
            default:
                throw new RuntimeException("Unsupported function: " + functionName);
        }
    }

    /**
     * Retrieves the next token in the list.
     *
     * @return The next token.
     */
    private ExpressionToken getNextToken() {
        if (position < tokens.size()) {
            return tokens.get(position++);
        } else {
            return new ExpressionToken(TokenType.END, "");
        }
    }

    /**
     * Moves the position in the token list backward by one.
     */
    private void back() {
        position--;
    }
}