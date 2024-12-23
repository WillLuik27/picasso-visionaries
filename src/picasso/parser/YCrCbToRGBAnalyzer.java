package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.YCrCbToRGB;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the YCrCbToRgb function.
 * 
 * @author Tim Johns
 * 
 */
public class YCrCbToRGBAnalyzer extends UnaryFunctionAnalyzer {

    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
        tokens.pop();
        ExpressionTreeNode param = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        return new YCrCbToRGB(param);
    }
}
