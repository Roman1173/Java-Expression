package com.poltarabatko.lab2;

import com.poltarabatko.lab2.processing.ExpressionAnalyzer;
import com.poltarabatko.lab2.processing.ExpressionToken;
import com.poltarabatko.lab2.processing.TokenType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link ExpressionAnalyzer}.
 * @author poltarabatko
 */
public class ExpressionAnalyzerTest {

    /**
     * Tests the {@link ExpressionAnalyzer#analyze(String)} method for a simple expression.
     */
    @Test
    public void testAnalyzeSimpleExpression() {
        ExpressionAnalyzer analyzer = new ExpressionAnalyzer();
        List<ExpressionToken> tokens = analyzer.analyze("3 + 4");

        assertEquals(4, tokens.size());

        // Testing token types
        assertEquals(TokenType.NUMBER, tokens.get(0).getType());
        assertEquals(TokenType.ADDITION, tokens.get(1).getType());
        assertEquals(TokenType.NUMBER, tokens.get(2).getType());
        assertEquals(TokenType.END, tokens.get(3).getType());

        // Testing token values
        assertEquals("3", tokens.get(0).getValue());
        assertEquals("+", tokens.get(1).getValue());
        assertEquals("4", tokens.get(2).getValue());
        assertEquals("", tokens.get(3).getValue());
    }

    /**
     * Tests the {@link ExpressionAnalyzer#analyze(String)} method for a complex expression.
     */
    @Test
    public void testAnalyzeComplexExpression() {
        ExpressionAnalyzer analyzer = new ExpressionAnalyzer();
        List<ExpressionToken> tokens = analyzer.analyze("sin(x) * 2 - 7");

        assertEquals(9, tokens.size());

        // Testing token types
        assertEquals(TokenType.FUNCTION, tokens.get(0).getType());
        assertEquals(TokenType.LEFT_BRACKET, tokens.get(1).getType());
        assertEquals(TokenType.VARIABLE, tokens.get(2).getType());
        assertEquals(TokenType.RIGHT_BRACKET, tokens.get(3).getType());
        assertEquals(TokenType.MULTIPLICATION, tokens.get(4).getType());
        assertEquals(TokenType.NUMBER, tokens.get(5).getType());
        assertEquals(TokenType.SUBTRACTION, tokens.get(6).getType());
        assertEquals(TokenType.NUMBER, tokens.get(7).getType());
        assertEquals(TokenType.END, tokens.get(8).getType());

        // Testing token values
        assertEquals("sin", tokens.get(0).getValue());
        assertEquals("(", tokens.get(1).getValue());
        assertEquals("x", tokens.get(2).getValue());
        assertEquals(")", tokens.get(3).getValue());
        assertEquals("*", tokens.get(4).getValue());
        assertEquals("2", tokens.get(5).getValue());
        assertEquals("-", tokens.get(6).getValue());
        assertEquals("7", tokens.get(7).getValue());
        assertEquals("", tokens.get(8).getValue());
    }
}
