package picasso.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.X;
import picasso.parser.language.expressions.Y;
import picasso.parser.language.expressions.Variable;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;

/**
 * Handle an identifier token 
 * 
 * @author Sara Sprenkle
 * @author Tim Johns
 */
public class IdentifierAnalyzer implements SemanticAnalyzerInterface {

	static Map<String, ExpressionTreeNode> idToExpression = new HashMap<String, ExpressionTreeNode>();

	static {
		// We always have x and y defined.
		idToExpression.put("x", new X());
		idToExpression.put("y", new Y());
	}

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		IdentifierToken t = (IdentifierToken) tokens.pop();
		String id = t.getName();
		ExpressionTreeNode mapped = idToExpression.get(id);
		if (mapped != null) {
			return mapped;
		}
		
		//If we don't recognize the identifier, create a new variable.
		Variable var = new Variable(id);

		//If you don't recognize the identifier, return it to be used to
		//create a new variable through assignment.
		return var;
		
	}

}
