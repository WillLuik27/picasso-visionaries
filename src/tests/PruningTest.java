
package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.SemanticAnalyzer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Constant;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.language.expressions.Wrap;
import picasso.parser.language.expressions.X;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.NumberToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.functions.WrapToken;
import picasso.parser.tokens.operations.AdditionToken;
import picasso.parser.tokens.operations.ModulusToken;
import picasso.parser.tokens.operations.MultiplicationToken;

/**
 * @author williamluik
 */
public class PruningTest {
	
	private Stack<Token> tokens;
	private SemanticAnalyzer analyzer;
	
	

	@BeforeEach
	void setUp() {
		//create a stack that will be analyzed
		tokens = new Stack<>();
		analyzer = SemanticAnalyzer.getInstance();
		
	}
	
	@Test
	public void testSimplifyAdd() {
		
		//stack of 0 + 0.5
		tokens.push(new NumberToken(0.0));
		tokens.push(new NumberToken(0.5));
		tokens.push(new AdditionToken());
		
		//analyze it
		ExpressionTreeNode root = analyzer.generateExpressionTree(tokens); 
		ExpressionTreeNode simplifiedRoot = root.simplify();
		//should now just be Constant(0.5)
		
		assertTrue(simplifiedRoot instanceof Constant);
		assertEquals(0.5, ((Constant) simplifiedRoot).getValue());
		
	}
	
	@Test
	public void testSimplifyMultiply() {
		//stack for x * 1
		tokens.push(new IdentifierToken("x"));
		tokens.push(new NumberToken(1.0));
		tokens.push(new MultiplicationToken());
		
		//analyze it
		ExpressionTreeNode root = analyzer.generateExpressionTree(tokens); 
		ExpressionTreeNode simplifiedRoot = root.simplify();
		//should now just be IdentifierToken("x")
		
		assertTrue(simplifiedRoot instanceof X );
		assertEquals( "x" , simplifiedRoot.toString());
		
	}
	
	@Test 
	public void testSimplifyModulus() {
		//stack for x % x = 0
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new ModulusToken());
		
		//analyze it
		ExpressionTreeNode root = analyzer.generateExpressionTree(tokens); 
		ExpressionTreeNode simplifiedRoot = root.simplify();
		
		assertTrue(simplifiedRoot instanceof Constant);
		assertEquals(0.0 , ((Constant) simplifiedRoot).getValue());
		
	}
	
	
	@Test
	public void testNoSimplification() {
		//stack for wrap(x + y)
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new AdditionToken());
		tokens.push(new WrapToken());
		
		//analyze it
		ExpressionTreeNode root = analyzer.generateExpressionTree(tokens); 
		ExpressionTreeNode simplifiedRoot = root.simplify();
		
		assertTrue(simplifiedRoot instanceof Wrap);
		
		//test certain location
		RGBColor result = simplifiedRoot.evaluate(0.5, 1); //rgb color of 1.5 wrapped to -0.5
		RGBColor actual = new RGBColor(-0.5, -0.5 , -0.5);
		assertEquals(result, actual);
		
		
	}
}
