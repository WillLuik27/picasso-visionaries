/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Cos;
import picasso.parser.tokens.Token;

/**
 * Handles parsing for the cosine function.
 * 
 * @author Cailen Graham
 * 
 */
public class CosAnalyzer extends UnaryFunctionAnalyzer {
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // remove the cos token
		// the parameter is the next token(s) on the stack.
		// But, it needs to be processed
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Cos(paramETN);
	}
}

