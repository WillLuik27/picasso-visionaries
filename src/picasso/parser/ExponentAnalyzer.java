package picasso.parser;

import java.util.Stack;


import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Exponent;

/**
 * Handles parsing the caret or "exponent function".
 * 
 * @author Robert C. Duvall
 * @author Tim Johns
 * 
 */
public class ExponentAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the caret token
		ExpressionTreeNode Right = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode Left = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Exponent(Left, Right);
	}

}
