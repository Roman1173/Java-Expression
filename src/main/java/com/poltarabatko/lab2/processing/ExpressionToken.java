package com.poltarabatko.lab2.processing;

/**
 * ExpressionToken implementation
 * @author poltarabatko
 */
public class ExpressionToken {
    TokenType type;
    String value;

    /**
     * Constructs an ExpressionToken object with a specified type and value.
     *
     * @param type  The type of the token.
     * @param value The value associated with the token.
     */
    public ExpressionToken(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Constructs an ExpressionToken object with a specified type and character value.
     *
     * @param type  The type of the token.
     * @param value The character value associated with the token.
     */
    public ExpressionToken(TokenType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    /**
     * Retrieves the type of the token.
     *
     * @return The type of the token.
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Retrieves the value associated with the token.
     *
     * @return The value of the token.
     */
    public String getValue() {
        return value;
    }
}