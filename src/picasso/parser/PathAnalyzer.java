/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Path;
import picasso.parser.tokens.ColorToken;
import picasso.parser.tokens.PathToken;
import picasso.parser.tokens.Token;

/**
 * 
 * @author William Luik
 */
public class PathAnalyzer implements SemanticAnalyzerInterface {


	/** 
	 * Need to generate an expression tree for the color analyzer
	 * I think this should be were we call the path of the image and convert pixel sizing into the [-1,1] range
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		Token t = tokens.pop();
		if( ! ( t instanceof PathToken )) {
			throw new ParseException("Expected a path"); //Maybe this is not fully needed similar to the colorAnalyzer
			
		}
		
		PathToken ct = (PathToken) t;
		String path = ct.getPath();
		
		return new Path(path);
	}

}
