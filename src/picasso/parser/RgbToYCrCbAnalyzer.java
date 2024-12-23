package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.RgbToYCrCb;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the RgbtoYCrCb function.
 * 
 * @author Tim Johns
 * 
 */
public class RgbToYCrCbAnalyzer extends UnaryFunctionAnalyzer {

    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
        tokens.pop();
        ExpressionTreeNode param = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        return new RgbToYCrCb(param);
    }
}
