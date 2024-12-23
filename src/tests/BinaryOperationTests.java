package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.SemanticAnalyzer;
import picasso.parser.Tokenizer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.operations.*;

/**
 * Unit testing class for all binary operations.
 * (Addition, Subtraction, Multiplication, Division, 
 * Modulus, and Exponentiation.)
 * @author Tim Johns
 */
class BinaryOperationTests {

    private Addition addition;
    private Subtraction subtraction;
    private Multiplication multiplication;
    private Division division;
    private Modulus modulus;
    private Exponent exponent;

    private ExpressionTreeGenerator expTreeGen;
    private Tokenizer tokenizer;
    private List<Token> tokens;
    private SemanticAnalyzer semAnalyzer = SemanticAnalyzer.getInstance();

    @BeforeEach
    void setUp() {
        expTreeGen = new ExpressionTreeGenerator();
        tokenizer = new Tokenizer();
    }

    //---------------- Addition and Subtraction Tests -------------------
    @Test
    void testAdditionAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new IdentifierToken("y"));
        tokens.push(new AdditionToken());
        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
        assertEquals(new Addition(new X(), new Y()), actual);
    }

    @Test
    void testSubtractionAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new IdentifierToken("y"));
        tokens.push(new SubtractionToken());
        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
        assertEquals(new Subtraction(new X(), new Y()), actual);
    }

    @Test
    void testAdditionToken() {
        String expression = "x + y";
        tokens = tokenizer.parseTokens(expression);
        assertEquals(new IdentifierToken("x"), tokens.get(0));
        assertEquals(new AdditionToken(), tokens.get(1));
        assertEquals(new IdentifierToken("y"), tokens.get(2));
    }

    @Test
    void testSubtractionToken() {
        String expression = "x - y";
        tokens = tokenizer.parseTokens(expression);
        assertEquals(new IdentifierToken("x"), tokens.get(0));
        assertEquals(new SubtractionToken(), tokens.get(1));
        assertEquals(new IdentifierToken("y"), tokens.get(2));
    }

    @Test
    void testAdditionEvaluateConstants() {
        ExpressionTreeNode left = new RGBColor(2, 2, 2);
        ExpressionTreeNode right = new RGBColor(3, 3, 3);
        addition = new Addition(left, right);
        RGBColor expected = new RGBColor(5, 5, 5);
        assertEquals(expected, addition.evaluate(0, 0));
    }

    @Test
    void testSubtractionEvaluateConstants() {
        ExpressionTreeNode left = new RGBColor(5, 5, 5);
        ExpressionTreeNode right = new RGBColor(3, 3, 3);
        subtraction = new Subtraction(left, right);
        RGBColor expected = new RGBColor(2, 2, 2);
        assertEquals(expected, subtraction.evaluate(0, 0));
    }

    //---------------- Multiplication Tests -------------------
    @Test
    void testMultiplicationAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new IdentifierToken("y"));
        tokens.push(new MultiplicationToken());
        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
        assertEquals(new Multiplication(new X(), new Y()), actual);
    }

    @Test
    void testMultiplicationToken() {
        String expression = "x * y";
        tokens = tokenizer.parseTokens(expression);
        assertEquals(new IdentifierToken("x"), tokens.get(0));
        assertEquals(new MultiplicationToken(), tokens.get(1));
        assertEquals(new IdentifierToken("y"), tokens.get(2));
    }

    @Test
    void testMultiplicationEvaluateConstants() {
        ExpressionTreeNode left = new RGBColor(2, 2, 2);
        ExpressionTreeNode right = new RGBColor(3, 3, 3);
        multiplication = new Multiplication(left, right);
        RGBColor expected = new RGBColor(6, 6, 6);
        assertEquals(expected, multiplication.evaluate(0, 0));
    }

    //---------------- Division Tests -------------------
    @Test
    void testDivisionAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new IdentifierToken("y"));
        tokens.push(new DivisionToken());
        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
        assertEquals(new Division(new X(), new Y()), actual);
    }

    @Test
    void testDivisionToken() {
        String expression = "x / y";
        tokens = tokenizer.parseTokens(expression);
        assertEquals(new IdentifierToken("x"), tokens.get(0));
        assertEquals(new DivisionToken(), tokens.get(1));
        assertEquals(new IdentifierToken("y"), tokens.get(2));
    }

    @Test
    void testDivisionEvaluateConstants() {
        ExpressionTreeNode left = new RGBColor(6, 6, 6);
        ExpressionTreeNode right = new RGBColor(3, 3, 3);
        division = new Division(left, right);
        RGBColor expected = new RGBColor(2, 2, 2);
        assertEquals(expected, division.evaluate(0, 0));
    }

    //---------------- Modulus and Exponent Tests -------------------
    @Test
    void testModulusAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new IdentifierToken("y"));
        tokens.push(new ModulusToken());
        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
        assertEquals(new Modulus(new X(), new Y()), actual);
    }

    @Test
    void testExponentAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new IdentifierToken("y"));
        tokens.push(new ExponentToken());
        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
        assertEquals(new Exponent(new X(), new Y()), actual);
    }

    @Test
    void testModulusEvaluateConstants() {
        ExpressionTreeNode left = new RGBColor(7, 7, 7);
        ExpressionTreeNode right = new RGBColor(3, 3, 3);
        modulus = new Modulus(left, right);
        RGBColor expected = new RGBColor(1, 1, 1);
        assertEquals(expected, modulus.evaluate(0, 0));
    }

    @Test
    void testExponentEvaluateConstants() {
        ExpressionTreeNode left = new RGBColor(2, 2, 2);
        ExpressionTreeNode right = new RGBColor(3, 3, 3);
        exponent = new Exponent(left, right);
        RGBColor expected = new RGBColor(8, 8, 8);
        assertEquals(expected, exponent.evaluate(0, 0));
    }
}
