/**
 * 
 */
package picasso.parser;

import picasso.parser.language.expressions.PerlinBW;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.chars.CommaToken;
import picasso.parser.tokens.functions.PerlinBWToken;

/**
 * 
 * @author Micah Tongen
 */
public class PerlinBWAnalyzer implements SemanticAnalyzerInterface {


	/** 
	 * 
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		System.out.println(tokens);
		if (tokens.isEmpty()) {
			throw new ParseException("No tokens available for arguments of PerlinBW");
		}	
	
	
	Token token = tokens.pop();
	
	if (!(token instanceof PerlinBWToken)) {
		throw new ParseException("Expected an PerlinBWToken");
	}
	
	
	//get the two arguments of the token in proper order. Will be reverse of how the arguments are listed so. thus:
	//left
	//right
	ExpressionTreeNode left = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
	ExpressionTreeNode right = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
	
	return new PerlinBW(left, right);
	

}
	
}
