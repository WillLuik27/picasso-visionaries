package picasso.parser;

import java.util.Stack;


import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Subtraction;

/**
 * Handles parsing the minus or "subtraction function".
 * 
 * @author Robert C. Duvall
 * @author Tim Johns
 * 
 */
public class SubtractionAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the subtraction token
		ExpressionTreeNode Right = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode Left = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Subtraction(Left, Right);
	}

}
