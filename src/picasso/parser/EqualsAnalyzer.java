package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Equals;
import picasso.parser.language.expressions.Variable;
import picasso.parser.language.expressions.X;
import picasso.parser.tokens.Token;

/**
 * Class that impliments the EqualsAnalyzer class.
 * @author Micah Tongen
 * @Author Tim Johns
 * 
 * */

public class EqualsAnalyzer implements SemanticAnalyzerInterface{

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
	    tokens.pop(); //Remove the EqualsToken

	    //Get the right-hand side expression
	    ExpressionTreeNode right = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);

	    //Get the left-hand side expression
	    ExpressionTreeNode left = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
	    
	    //Ensure the left-hand side is a Variable
	    if (!(left instanceof Variable)) {
	        throw new ParseException("Left-hand side of assignment must be a variable, but found: " + left.getClass().getSimpleName());
	    }

	    // Cast the left-hand side to Variable
	    Variable var = (Variable) left;
	    
	    // Update the variable mapping
	    Variable.assignor.put(var, right);
	    
	    //Return the right-hand side as the value of the assignment
	    return right;
	}



}
