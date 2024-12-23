package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.SemanticAnalyzer;
import picasso.parser.Tokenizer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Abs;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.language.expressions.Path;
import picasso.parser.language.expressions.X;
import picasso.parser.language.expressions.Addition;
import picasso.parser.language.expressions.Constant;
import picasso.parser.language.expressions.Subtraction;
import picasso.parser.language.expressions.Y;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.chars.LeftParenToken;
import picasso.parser.tokens.chars.CommaToken;
import picasso.parser.tokens.chars.RightParenToken;
import picasso.parser.tokens.functions.AbsToken;
import picasso.parser.tokens.functions.ImageWrapToken;
import picasso.parser.tokens.PathToken;
import picasso.parser.language.expressions.RGBColor;



public class ImageWrapTests {
	
	private ImageWrap imageWrap;
	private ExpressionTreeGenerator expTreeGen;
    private Tokenizer tokenizer;
    private List<Token> tokens;
    private SemanticAnalyzer semAnalyzer = SemanticAnalyzer.getInstance();
    private Path path;
    private String image = "testimage.png";

    /**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		expTreeGen = new ExpressionTreeGenerator();
	}
	
	//----------------ImageWrapToken.java.java-Tests-------------------
	
		@Test
		void testImageWrapToken() {
			tokenizer = new Tokenizer();
			
			
			//Basic ImageWrapToken Tests:
			String expression = "imageWrap(\"testimage.png\",x,y)"; 
			tokens = tokenizer.parseTokens(expression);
			System.out.println(tokens);
			assertEquals(new ImageWrapToken(), tokens.get(0));
			assertEquals(new LeftParenToken(), tokens.get(1));
			
			//Check that it's a PathToken - cannot do "assertEqual" because it will be at different spots in memory so always fails
			assertTrue(tokens.get(2) instanceof PathToken);
			assertEquals(new CommaToken(), tokens.get(3));
			assertEquals(new IdentifierToken("x"), tokens.get(4));
			assertEquals(new CommaToken(), tokens.get(5));
			assertEquals(new IdentifierToken("y"), tokens.get(6));
			assertEquals(new RightParenToken(), tokens.get(7));
			
			//Test the toString() and isConstant/isFunction methods for the ImageWrapToken:
			assertEquals(new ImageWrapToken().toString(),"ImageWrap Function Token");
			assertEquals(new ImageWrapToken().isConstant(),false);
			assertEquals(new ImageWrapToken().isFunction(),true);
		
		}
	
	//-----------------ImageWrapAnalyzer.java-Tests--------------------------
		@Test
		void testImageWrapAnalyzer() {
			Stack<Token> tokens = new Stack<>();
			tokens.push(new IdentifierToken("y"));
			tokens.push(new IdentifierToken("x"));
			tokens.push(new PathToken(image));
			tokens.push(new ImageWrapToken());
			
			ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

			//Check that actual actually created an ImageWrap - cannot do "assertEqual" because it will be at different spots in memory
			assertTrue(actual instanceof ImageWrap);
		}
	
	//------------------ImageWrap.java-Tests--------------------------------
		@Test
		void testImageWrapAddition() {
			
			String test1 = "x+x";
			ExpressionTreeNode left1 = new RGBColor(1, 1, 1);
	        ExpressionTreeNode right1 = new RGBColor(0,0,0);
	        imageWrap = new ImageWrap(new Path("testimage.png"),new Constant (1.0), new Constant(0.0));
			assertTrue(imageWrap.evaluate(1, 0) instanceof RGBColor);
			
			String test2 = "x*x";
			ExpressionTreeNode left2 = new RGBColor(1,1,1);
	        ExpressionTreeNode right2 = new RGBColor(0,0,0);
	        assertTrue(imageWrap.evaluate(1, 0) instanceof RGBColor);
			
			
			String test9 = "x/y";
			String test10 = "y/x";
			String test11 = "abs(x)";
			String test12 = "abs(y)";
			String test13 = "floor(x)";
			String test14 = "floor(y)";
			String test15 = "sin(x)";
			String test16 = "sin(y)";
			String test17 = "cos(x)";
			String test18 = "cos(y)";
			
			
			
		}
}
