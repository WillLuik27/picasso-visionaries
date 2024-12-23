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
import picasso.parser.language.expressions.Tan;
import picasso.parser.language.expressions.X;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.chars.LeftParenToken;
import picasso.parser.tokens.chars.RightParenToken;
import picasso.parser.tokens.functions.TanToken;
/**
 * 
 * 
 * @author Will Luik
 */
public class TanTests {

    private static final double DELTA = 0.05;

    private ExpressionTreeGenerator expTreeGen;
    private SemanticAnalyzer semAnalyzer;
    private Tokenizer tokenizer;

    @BeforeEach
    void setUp() {
        expTreeGen = new ExpressionTreeGenerator();
        tokenizer = new Tokenizer();
        semAnalyzer = SemanticAnalyzer.getInstance();
    }

    @Test
    public void testTanAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("x"));
        tokens.push(new TanToken());

        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

        assertEquals(new Tan(new X()), actual);
    }

    @Test
    public void testTanToken() {
        String expression = "tan(y)";
        List<Token> tokens = tokenizer.parseTokens(expression);

        assertEquals(new TanToken(), tokens.get(0));
        assertEquals(new LeftParenToken(), tokens.get(1));
        assertEquals(new IdentifierToken("y"), tokens.get(2));
        assertEquals(new RightParenToken(), tokens.get(3));

        assertEquals(new TanToken().toString(), "Tan Function Token");
        assertEquals(new TanToken().isConstant(), false);
        assertEquals(new TanToken().isFunction(), true);
    }

    @Test
    public void testTanMath() {
        String test = "tan(y + 0.5)";
        ExpressionTreeNode param = expTreeGen.makeExpression(test);

        RGBColor expected = new RGBColor(Math.tan(1.5), Math.tan(1.5), Math.tan(1.5));
        RGBColor actual = param.evaluate(1, 1);

        assertEquals(expected.getRed(), actual.getRed(), DELTA);
        assertEquals(expected.getGreen(), actual.getGreen(), DELTA);
        assertEquals(expected.getBlue(), actual.getBlue(), DELTA);
    }
}
