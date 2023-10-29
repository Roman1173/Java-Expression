package com.poltarabatko.lab2;

import com.poltarabatko.lab2.exception.UnexpectedElementException;
import com.poltarabatko.lab2.processing.ExpressionToken;
import com.poltarabatko.lab2.processing.TokenType;
import com.poltarabatko.lab2.solver.ExpressionSolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author poltarabatko
 */
public class ExpressionSolverTest {

    /**
     * Test for simple addition operation.
     */
    @Test
    public void testSimpleAddition() {
        // Arrange
        List<ExpressionToken> tokens = new ArrayList<>();
        tokens.add(new ExpressionToken(TokenType.NUMBER, "5"));
        tokens.add(new ExpressionToken(TokenType.ADDITION, '+'));
        tokens.add(new ExpressionToken(TokenType.NUMBER, "3"));
        tokens.add(new ExpressionToken(TokenType.END, ""));
        ExpressionSolver solver = new ExpressionSolver(tokens);

        // Act and Assert
        assertEquals(8, solver.solve());
    }

    /**
     * Test for simple multiplication operation.
     */
    @Test
    public void testSimpleMultiplication() {
        // Arrange
        List<ExpressionToken> tokens = new ArrayList<>();
        tokens.add(new ExpressionToken(TokenType.NUMBER, "5"));
        tokens.add(new ExpressionToken(TokenType.MULTIPLICATION, '*'));
        tokens.add(new ExpressionToken(TokenType.NUMBER, "3"));
        tokens.add(new ExpressionToken(TokenType.END, ""));
        ExpressionSolver solver = new ExpressionSolver(tokens);

        // Act and Assert
        assertEquals(15, solver.solve());
    }

    /**
     * Test for a simple function call (sin).
     */
    @Test
    public void testSimpleFunctionCall() {
        // Arrange
        List<ExpressionToken> tokens = new ArrayList<>();
        tokens.add(new ExpressionToken(TokenType.FUNCTION, "sin"));
        tokens.add(new ExpressionToken(TokenType.NUMBER, "0"));
        tokens.add(new ExpressionToken(TokenType.END, ""));
        ExpressionSolver solver = new ExpressionSolver(tokens);

        // Act and Assert
        assertEquals(0, solver.solve());
    }

    /**
     * Test for an invalid function call, expecting a RuntimeException.
     */
    @Test
    public void testInvalidFunctionCall() {
        // Arrange
        List<ExpressionToken> tokens = new ArrayList<>();
        tokens.add(new ExpressionToken(TokenType.FUNCTION, "invalid_function"));
        tokens.add(new ExpressionToken(TokenType.NUMBER, "5"));
        tokens.add(new ExpressionToken(TokenType.END, ""));
        ExpressionSolver solver = new ExpressionSolver(tokens);

        // Act and Assert
        assertThrows(RuntimeException.class, solver::solve);
    }
}
