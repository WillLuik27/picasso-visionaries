package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Not;

/**
 * Handles parsing the unary not (!) function.
 * 
 * @author Joe Holden
 */
public class NotAnalyzer implements SemanticAnalyzerInterface {

    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
        tokens.pop(); // Remove the NotToken from the stack
        ExpressionTreeNode operand = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        return new Not(operand);
    }
}
