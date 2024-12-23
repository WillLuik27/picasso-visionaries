package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Abs;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the absolute value function.
 * @author Tim Johns
 */
public class AbsAnalyzer extends UnaryFunctionAnalyzer {

	/**
	 * @param tokens
	 * @return the root node for an absolute value expression tree.
	 * @see picasso.parser.UnaryFunctionAnalyzer#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the Abs token
		// the parameter is the next token(s) on the stack.
		// But, it needs to be processed
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Abs(paramETN);
	}

}
