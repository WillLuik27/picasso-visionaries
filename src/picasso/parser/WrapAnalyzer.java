/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Wrap;
import picasso.parser.tokens.Token;

/**
 * 
 * @author William Luik
 */
public class WrapAnalyzer extends UnaryFunctionAnalyzer {



	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // remove wrap token
		// the parameter is the next token(s) on the stack. ie: Wrap(x)
		// But, it needs to be processed
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Wrap(paramETN);
	}

}
