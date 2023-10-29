package com.poltarabatko.lab2;

import com.poltarabatko.lab2.processing.ExpressionAnalyzer;
import com.poltarabatko.lab2.processing.ExpressionToken;
import com.poltarabatko.lab2.solver.ExpressionSolver;

import java.util.List;
import java.util.Scanner;

/**
 * Main implementation
 * @author poltarabatko
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String expressionText = scanner.nextLine();

        ExpressionAnalyzer expressionAnalyzer = new ExpressionAnalyzer();
        List<ExpressionToken> tokens = expressionAnalyzer.analyze(expressionText);
        ExpressionSolver expressionSolver = new ExpressionSolver(tokens);
        double result = expressionSolver.solve();
        System.out.println("Ответ: " + result);
    }
}