package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.SemanticAnalyzer;
import picasso.parser.Tokenizer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.language.expressions.RgbToYCrCb;
import picasso.parser.language.expressions.YCrCbToRGB;
import picasso.parser.language.expressions.X;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.chars.LeftParenToken;
import picasso.parser.tokens.chars.RightParenToken;
import picasso.parser.tokens.functions.RgbToYCrCbToken;
import picasso.parser.tokens.functions.YCrCbToRGBToken;

/**
 * Unit testing for the RgbToYCrCb and YCrCbToRGB classes, along with their tokens and analyzers.
 */
class RgbYCrCbConversionTests {

    // Error tolerance for floating-point comparisons
    private static final double DELTA = 0.5;

    private ExpressionTreeGenerator expTreeGen;
    private Tokenizer tokenizer;
    private List<Token> tokens;
    private SemanticAnalyzer semAnalyzer = SemanticAnalyzer.getInstance();

    @BeforeEach
    void setUp() throws Exception {
        expTreeGen = new ExpressionTreeGenerator();
    }

    //----------------RgbToYCrCb Analyzer Tests-------------------

    @Test
    void testRgbToYCrCbAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new RgbToYCrCbToken());

        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

        assertEquals(new RgbToYCrCb(new X()), actual);
    }

    //----------------YCrCbToRGB Analyzer Tests-------------------

    @Test
    void testYCrCbToRGBAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new YCrCbToRGBToken());

        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

        assertEquals(new YCrCbToRGB(new X()), actual);
    }

    //----------------RgbToYCrCb Token Tests-----------------------

    @Test
    public void testRgbToYCrCbToken() {
        tokenizer = new Tokenizer();

        String expression = "rgbToYCrCb(x)";
        tokens = tokenizer.parseTokens(expression);

        assertEquals(new RgbToYCrCbToken(), tokens.get(0));
        assertEquals(new LeftParenToken(), tokens.get(1));
        assertEquals(new IdentifierToken("x"), tokens.get(2));
        assertEquals(new RightParenToken(), tokens.get(3));

        assertEquals(new RgbToYCrCbToken().toString(), "rgbToYCrCb Function Token");
    }

    //----------------YCrCbToRGB Token Tests-----------------------

    @Test
    public void testYCrCbToRGBToken() {
        tokenizer = new Tokenizer();

        String expression = "yCrCbToRGB(x)";
        tokens = tokenizer.parseTokens(expression);

        assertEquals(new YCrCbToRGBToken(), tokens.get(0));
        assertEquals(new LeftParenToken(), tokens.get(1));
        assertEquals(new IdentifierToken("x"), tokens.get(2));
        assertEquals(new RightParenToken(), tokens.get(3));

        assertEquals(new YCrCbToRGBToken().toString(), "yCrCbToRGB Function Token");
    }

    //----------------RgbToYCrCb Evaluation Tests-------------------

    @Test
    void testRgbToYCrCbEvaluation() {
        //Test Red
        String test1 = "rgbToYCrCb([1, 0, 0])";
        ExpressionTreeNode param1 = expTreeGen.makeExpression(test1);
        RGBColor result1 = param1.evaluate(0, 0);
        assertEquals(0.299, result1.getRed(), DELTA);
        assertEquals(0.5, result1.getGreen(), DELTA);
        assertEquals(-0.1687, result1.getBlue(), DELTA);

        //Test Green
        String test2 = "rgbToYCrCb([0, 1, 0])";
        ExpressionTreeNode param2 = expTreeGen.makeExpression(test2);
        RGBColor result2 = param2.evaluate(0, 0);
        assertEquals(0.587, result2.getRed(), DELTA);
        assertEquals(-0.4187, result2.getGreen(), DELTA);
        assertEquals(-0.3313, result2.getBlue(), DELTA);

        //Test Blue
        String test3 = "rgbToYCrCb([0, 0, 1])";
        ExpressionTreeNode param3 = expTreeGen.makeExpression(test3);
        RGBColor result3 = param3.evaluate(0, 0);
        assertEquals(0.114, result3.getRed(), DELTA);
        assertEquals(-0.0813, result3.getGreen(), DELTA);
        assertEquals(0.5, result3.getBlue(), DELTA);
    }

    //----------------YCrCbToRGB Evaluation Tests-------------------

    @Test
    void testYCrCbToRGBEvaluation() {
        //Test for Red
        String test1 = "yCrCbToRGB([0.299, 0.5, -0.1687])";
        ExpressionTreeNode param1 = expTreeGen.makeExpression(test1);
        RGBColor result1 = param1.evaluate(0, 0);
        assertEquals(1.0, result1.getRed(), DELTA);
        assertEquals(0.0, result1.getGreen(), DELTA);
        assertEquals(0.0, result1.getBlue(), DELTA);

        //Test YCrCb for Green
        String test2 = "yCrCbToRGB([0.587, -0.4187, -0.3313])";
        ExpressionTreeNode param2 = expTreeGen.makeExpression(test2);
        RGBColor result2 = param2.evaluate(0, 0);
        assertEquals(0.0, result2.getRed(), DELTA);
        assertEquals(1.0, result2.getGreen(), DELTA);
        assertEquals(0.0, result2.getBlue(), DELTA);

        //Test YCrCb for Blue
        String test3 = "yCrCbToRGB([0.114, -0.0813, 0.5])";
        ExpressionTreeNode param3 = expTreeGen.makeExpression(test3);
        RGBColor result3 = param3.evaluate(0, 0);
        assertEquals(0.0, result3.getRed(), DELTA);
        assertEquals(0.0, result3.getGreen(), DELTA);
        assertEquals(1.0, result3.getBlue(), DELTA);
    }
}
