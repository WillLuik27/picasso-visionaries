/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.chars.CommaToken;
import picasso.parser.tokens.functions.ImageWrapToken;

/**
 * 
 * @author William Luik
 */
public class ImageWrapAnalyzer implements SemanticAnalyzerInterface {


	/** 
	 * 
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		System.out.println(tokens);
		if (tokens.isEmpty()) {
			throw new ParseException("No tokens available for arguments of ImageWrap");
		}	
	
	
	Token token = tokens.pop();
	
	if (!(token instanceof ImageWrapToken)) {
		throw new ParseException("Expected an ImageWrapToken");
	}
	
	
	//get the three arguments of the token in proper order. Will be reverse of how the arguments are listed so. thus:
	//yCoord
	//xCoord
	//Path
	ExpressionTreeNode yCoord = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
	ExpressionTreeNode xCoord = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
	ExpressionTreeNode path = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
	
	return new ImageWrap(path, xCoord, yCoord);
	

}
	
}
