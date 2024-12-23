package picasso.parser;

import java.util.Stack;


import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Multiplication;

/**
 * Handles parsing the multiplication or "multiplication function".
 * 
 * @author Robert C. Duvall
 * @author Tim Johns
 * 
 */
public class MultiplicationAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the multiplication token
		ExpressionTreeNode Right = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode Left = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Multiplication(Left, Right);
	}

}
