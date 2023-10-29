package com.poltarabatko.lab2.processing;

/**
 * The {@code TokenType} enum represents different types of tokens used in expression parsing.
 * Tokens are elements of a mathematical expression that signify operations, variables, numbers, and special markers.
 * Each token type signifies a distinct element within the mathematical expression.
 *
 * @author poltarabatko
 */
public enum TokenType {
    ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION,
    LEFT_BRACKET, RIGHT_BRACKET,
    NUMBER, VARIABLE,
    FUNCTION,
    END;
}