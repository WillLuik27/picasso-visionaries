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
import picasso.parser.language.expressions.ImageClip;
import picasso.parser.language.expressions.Path;
import picasso.parser.language.expressions.X;
import picasso.parser.language.expressions.Y;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.PathToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.chars.CommaToken;
import picasso.parser.tokens.chars.LeftParenToken;
import picasso.parser.tokens.chars.RightParenToken;
import picasso.parser.tokens.functions.ImageClipToken;

/**
 * Unit tests for the ImageClip class, along with its token and analyzer.
 */
public class ImageClipTests {

	//Precision delta:
    private static final double DELTA = 0.5;

    private ExpressionTreeGenerator expTreeGen;
    private SemanticAnalyzer semAnalyzer;
    private Tokenizer tokenizer;
    private List<Token> tokens;

    @BeforeEach
    void setUp() {
        expTreeGen = new ExpressionTreeGenerator();
        semAnalyzer = SemanticAnalyzer.getInstance();
        tokenizer = new Tokenizer();
    }

    //----------------ImageClip Token Tests-----------------------

    @Test
    public void testImageClipToken() {
        tokenizer = new Tokenizer();

        String expression = "imageClip(\"testimage.png\", x, y)";
        tokens = tokenizer.parseTokens(expression);

        assertEquals(new ImageClipToken(), tokens.get(0));
        assertEquals(new LeftParenToken(), tokens.get(1));
        assertEquals(new PathToken("testimage.png"), tokens.get(2));
        assertEquals(new CommaToken(), tokens.get(3));
        assertEquals(new IdentifierToken("x"), tokens.get(4));
        assertEquals(new CommaToken(), tokens.get(5));
        assertEquals(new IdentifierToken("y"), tokens.get(6));
        assertEquals(new RightParenToken(), tokens.get(7));

        assertEquals(new ImageClipToken().toString(), "ImageClip Function Token");
        assertFalse(new ImageClipToken().isConstant());
        assertTrue(new ImageClipToken().isFunction());
    }

    //----------------ImageClip Analyzer Tests-------------------


    @Test
    void testImageClipAnalyzer() {
        Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("y"));
        tokens.push(new IdentifierToken("x"));
        tokens.push(new PathToken("testimage.png"));
        tokens.push(new ImageClipToken());

        ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

        // Check that the actual node is an instance of ImageClip - cannot do "assertEquals" due to object references
        assertTrue(actual instanceof ImageClip);
    }



    @Test
    void testImageClipEvaluation() {
    	
    	Stack<Token> tokens = new Stack<>();
        tokens.push(new IdentifierToken("y"));
        tokens.push(new IdentifierToken("x"));
        tokens.push(new PathToken("testimage.png"));
        tokens.push(new ImageClipToken());
    	
    	ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

    	// Cast the root node to ImageClip
        ImageClip actualImageClip = (ImageClip) actual;
        
    	//Test evaluate()
        RGBColor centerColor = actualImageClip.evaluate(0, 0);
        assertEquals(0.5, centerColor.getRed(), DELTA);
        assertEquals(0.5, centerColor.getGreen(), DELTA);
        assertEquals(0.5, centerColor.getBlue(), DELTA);

        RGBColor topLeftColor = actualImageClip.evaluate(-1, 1);
        assertEquals(-1.0, topLeftColor.getRed(), DELTA);
        assertEquals(-1.0, topLeftColor.getGreen(), DELTA);
        assertEquals(-1.0, topLeftColor.getBlue(), DELTA);

        RGBColor bottomRightColor = actualImageClip.evaluate(1, -1); 
        assertEquals(-1.0, bottomRightColor.getRed(), DELTA);
        assertEquals(-1.0, bottomRightColor.getGreen(), DELTA);
        assertEquals(-1.0, bottomRightColor.getBlue(), DELTA);
        
    }




}
