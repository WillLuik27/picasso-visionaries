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
import picasso.parser.language.expressions.Abs;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.language.expressions.UnaryFunction;
import picasso.parser.language.expressions.X;
import picasso.parser.language.expressions.Y;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.chars.LeftParenToken;
import picasso.parser.tokens.chars.RightParenToken;
import picasso.parser.tokens.functions.AbsToken;
import picasso.parser.tokens.functions.FloorToken;
import picasso.parser.tokens.operations.AdditionToken;

/**
 * Unit testing for the Abs.java, AbsToken.java, and AbsAnalyzer.java 
 * classes.
 * @author Tim Johns
 */
class AbsTests{
	
	private Abs absoluteValue;
	private ExpressionTreeGenerator expTreeGen;
	private Tokenizer tokenizer;
	private List<Token> tokens;
	private SemanticAnalyzer semAnalyzer = SemanticAnalyzer.getInstance();
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		expTreeGen = new ExpressionTreeGenerator();
	}
	
	
//----------------AbsAnalyzer.java.java-Tests-------------------
	
	@Test
	void testAbsAnalyzer() {
		
		//Create a stack that would generate an expression tree with
		//an AbsToken as the root node.
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new AbsToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		//Compare an Abs object with the root node created by AbsAnalyzer().
		assertEquals(new Abs(new X()), actual);
	}
	
	
//-------------------AbsToken.java-Tests-----------------------
	
	@Test
	public void testAbsToken() {
		//Setup a Tokenizer:
		tokenizer = new Tokenizer();
		
		//Basic AbsToken Tests:
		String expression = "abs(x)";
		tokens = tokenizer.parseTokens(expression);
		assertEquals(new AbsToken(), tokens.get(0));
		assertEquals(new LeftParenToken(), tokens.get(1));
		assertEquals(new IdentifierToken("x"), tokens.get(2));
		assertEquals(new RightParenToken(), tokens.get(3));
		
		//Test the toString() and isConstant/isFunction methods for the AbsToken:
		assertEquals(new AbsToken().toString(),"Absolute Value Function Token");
		assertEquals(new AbsToken().isConstant(),false);
		assertEquals(new AbsToken().isFunction(),true);
	}

//------------------------Abs.java-Tests-----------------------
	@Test
	void testAbsEvaluateColors() {
		
		String test1 = "abs([-1,-1,-1])";
		String test2 = "abs([1,1,1])";
		String test3 = "abs([0,0,0])";
		String test4 = "abs([-0.3,0.7,-0.4])";
		
		//Test to ensure [-1,-1,-1] evaluates to [1,1,1]
		ExpressionTreeNode param1 = expTreeGen.makeExpression(test1);
		absoluteValue = new Abs(param1);
		RGBColor rgb1 = new RGBColor(1,1,1);
		assertEquals(absoluteValue.evaluate(1,1),rgb1);
		assertEquals(absoluteValue.evaluate(1,-1),rgb1);
		assertEquals(absoluteValue.evaluate(-1,1),rgb1);
		assertEquals(absoluteValue.evaluate(-1,-1),rgb1);
		
		//Test to ensure [1,1,1] evaluates to [1,1,1]
		ExpressionTreeNode param2 = expTreeGen.makeExpression(test2);
		absoluteValue = new Abs(param2);
		RGBColor rgb2 = new RGBColor(1,1,1);
		assertEquals(absoluteValue.evaluate(1,1),rgb2);
		assertEquals(absoluteValue.evaluate(1,-1),rgb2);
		assertEquals(absoluteValue.evaluate(-1,1),rgb2);
		assertEquals(absoluteValue.evaluate(-1,-1),rgb2);
		
		//Test to ensure [0,0,0] evaluates to [0,0,0]
		ExpressionTreeNode param3 = expTreeGen.makeExpression(test3);
		absoluteValue = new Abs(param3);
		RGBColor rgb3 = new RGBColor(0,0,0);
		assertEquals(absoluteValue.evaluate(1,1),rgb3);
		assertEquals(absoluteValue.evaluate(1,-1),rgb3);
		assertEquals(absoluteValue.evaluate(-1,1),rgb3);
		assertEquals(absoluteValue.evaluate(-1,-1),rgb3);
		
		//Test to ensure [-0.3,0.7,-0.4] evaluates to [0.3,0.7,0.4]
		ExpressionTreeNode param4 = expTreeGen.makeExpression(test4);
		absoluteValue = new Abs(param4);
		RGBColor rgb4 = new RGBColor(0.3,0.7,0.4);
		assertEquals(absoluteValue.evaluate(1,1),rgb4);
		assertEquals(absoluteValue.evaluate(1,-1),rgb4);
		assertEquals(absoluteValue.evaluate(-1,1),rgb4);
		assertEquals(absoluteValue.evaluate(-1,-1),rgb4);
		
	}
	
	@Test
	void testAbsEvaluateVariables(){
		String test1 = "abs(x)";
		String test2 = "abs(y)";
		
		//Test to ensure x = -1 or 1 evaluates to [1,1,1]
		ExpressionTreeNode param1 = expTreeGen.makeExpression(test1);
		absoluteValue = new Abs(param1);
		RGBColor rgb1 = new RGBColor(1,1,1);
		assertEquals(absoluteValue.evaluate(1,1),rgb1);
		assertEquals(absoluteValue.evaluate(-1,1),rgb1);
		//Now test when x = 0
		RGBColor rgb2 = new RGBColor(0,0,0);
		assertEquals(absoluteValue.evaluate(0,1),rgb2);
				
		//Test to ensure y = -1 or 1 evaluates to [1,1,1]
		ExpressionTreeNode param2 = expTreeGen.makeExpression(test2);
		absoluteValue = new Abs(param2);
		RGBColor rgb3 = new RGBColor(1,1,1);
		assertEquals(absoluteValue.evaluate(1,1),rgb3);
		assertEquals(absoluteValue.evaluate(1,-1),rgb3);
		//Now test when y = 0
		RGBColor rgb4 = new RGBColor(0,0,0);
		assertEquals(absoluteValue.evaluate(1,0),rgb4);
	}
	
	
	@Test
	void testAbsEvaluateConstants(){
		
		String test1 = "abs(-1)";
		String test2 = "abs(1)";
		String test3 = "abs(0)";
		String test4 = "abs(-0.5)";
		
		//Test to ensure abs(-1) evalutes to [1,1,1]
		ExpressionTreeNode param1 = expTreeGen.makeExpression(test1);
		absoluteValue = new Abs(param1);
		RGBColor rgb1 = new RGBColor(1,1,1);
		assertEquals(absoluteValue.evaluate(1,1),rgb1);
		assertEquals(absoluteValue.evaluate(-1,1),rgb1);
		assertEquals(absoluteValue.evaluate(1,-1),rgb1);
		assertEquals(absoluteValue.evaluate(-1,-1),rgb1);
		
		//Test to ensure abs(1) evalutes to [1,1,1]
		ExpressionTreeNode param2 = expTreeGen.makeExpression(test2);
		absoluteValue = new Abs(param2);
		RGBColor rgb2 = new RGBColor(1,1,1);
		assertEquals(absoluteValue.evaluate(1,1),rgb2);
		assertEquals(absoluteValue.evaluate(-1,1),rgb2);
		assertEquals(absoluteValue.evaluate(1,-1),rgb2);
		assertEquals(absoluteValue.evaluate(-1,-1),rgb2);
		
		//Test to ensure abs(0) evalutes to [0,0,0]
		ExpressionTreeNode param3 = expTreeGen.makeExpression(test3);
		absoluteValue = new Abs(param3);
		RGBColor rgb3 = new RGBColor(0,0,0);
		assertEquals(absoluteValue.evaluate(1,1),rgb3);
		assertEquals(absoluteValue.evaluate(-1,1),rgb3);
		assertEquals(absoluteValue.evaluate(1,-1),rgb3);
		assertEquals(absoluteValue.evaluate(-1,-1),rgb3);
		
		//Test to ensure abs(-0.5) evalutes to [0.5,0.5,0.5]
		ExpressionTreeNode param4 = expTreeGen.makeExpression(test4);
		absoluteValue = new Abs(param4);
		RGBColor rgb4 = new RGBColor(0.5,0.5,0.5);
		assertEquals(absoluteValue.evaluate(1,1),rgb4);
		assertEquals(absoluteValue.evaluate(-1,1),rgb4);
		assertEquals(absoluteValue.evaluate(1,-1),rgb4);
		assertEquals(absoluteValue.evaluate(-1,-1),rgb4);

	}
	
	@Test
	void testAbsEvaluateExpressions() {
	    //Test cases with binary operations and complex expressions

	    //Test abs(x + y)
	    String test1 = "abs(x + y)";
	    ExpressionTreeNode param1 = expTreeGen.makeExpression(test1);
	    absoluteValue = new Abs(param1);
	    RGBColor rgb1 = new RGBColor(2, 2, 2); // Assuming x = 1, y = 1
	    assertEquals(absoluteValue.evaluate(1, 1), rgb1);

	    //Test abs(x - y)
	    String test2 = "abs(x - y)";
	    ExpressionTreeNode param2 = expTreeGen.makeExpression(test2);
	    absoluteValue = new Abs(param2);
	    RGBColor rgb2 = new RGBColor(0, 0, 0); // Assuming x = 1, y = 1
	    assertEquals(absoluteValue.evaluate(1, 1), rgb2);

	    //Test abs(x * y)
	    String test3 = "abs(x * y)";
	    ExpressionTreeNode param3 = expTreeGen.makeExpression(test3);
	    absoluteValue = new Abs(param3);
	    RGBColor rgb3 = new RGBColor(1, 1, 1); // Assuming x = 1, y = 1
	    assertEquals(absoluteValue.evaluate(1, 1), rgb3);

	    //Test abs(x / y)
	    String test4 = "abs(x / y)";
	    ExpressionTreeNode param4 = expTreeGen.makeExpression(test4);
	    absoluteValue = new Abs(param4);
	    RGBColor rgb4 = new RGBColor(1, 1, 1); // Assuming x = 1, y = 1
	    assertEquals(absoluteValue.evaluate(1, 1), rgb4);

	    //Test abs(x * abs(y))
	    String test5 = "abs(x * abs(y))";
	    ExpressionTreeNode param5 = expTreeGen.makeExpression(test5);
	    absoluteValue = new Abs(param5);
	    RGBColor rgb5 = new RGBColor(1, 1, 1); // Assuming x = 1, y = 1
	    assertEquals(absoluteValue.evaluate(1, 1), rgb5);


	    //Test abs(x * x)
	    String test7 = "abs(x * x)";
	    ExpressionTreeNode param7 = expTreeGen.makeExpression(test7);
	    absoluteValue = new Abs(param7);
	    RGBColor rgb7 = new RGBColor(1, 1, 1); // Assuming x = 1
	    assertEquals(absoluteValue.evaluate(1, 1), rgb7);

	    //Test abs(y + 0.5)
	    String test8 = "abs(y + 0.5)";
	    ExpressionTreeNode param8 = expTreeGen.makeExpression(test8);
	    absoluteValue = new Abs(param8);
	    RGBColor rgb8 = new RGBColor(1.5, 1.5, 1.5); // Assuming y = 1
	    assertEquals(absoluteValue.evaluate(1, 1), rgb8);
	}


}
