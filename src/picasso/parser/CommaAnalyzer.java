/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;

/**
 * 
 * @author William Luik
 */
public class CommaAnalyzer implements SemanticAnalyzerInterface {

	/**
	 * 
	 */
	public CommaAnalyzer() {
		// TODO Auto-generated constructor stub
	}

	/** 
	 * method method returns null because we dont want to create a node from just the comma
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		// TODO Auto-generated method stub
		return null;
	}

}
