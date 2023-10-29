package com.poltarabatko.lab2;

import com.poltarabatko.lab2.processing.ExpressionToken;
import com.poltarabatko.lab2.processing.TokenType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author poltarabatko
 */
public class ExpressionTokenTest {

    /**
     * Verifies the {@link ExpressionToken#getType()} method.
     */
    @Test
    public void testGetType() {
        ExpressionToken token = new ExpressionToken(TokenType.ADDITION, '+');
        assertEquals(TokenType.ADDITION, token.getType());
    }

    /**
     * Verifies the {@link ExpressionToken#getValue()} method for the constructor that takes a character.
     */
    @Test
    public void testGetValueForCharacterConstructor() {
        ExpressionToken token = new ExpressionToken(TokenType.RIGHT_BRACKET, ')');
        assertEquals(")", token.getValue());
    }

    /**
     * Verifies the {@link ExpressionToken#getValue()} method for the constructor that takes a string.
     */
    @Test
    public void testGetValueForStringConstructor() {
        ExpressionToken token = new ExpressionToken(TokenType.VARIABLE, "x");
        assertEquals("x", token.getValue());
    }
}

