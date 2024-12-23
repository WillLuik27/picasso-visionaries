package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageClip;
import picasso.parser.tokens.Token;


/**
 * Analyzer for the imageClip function.
 * @author Tim Johns
 */
public class ImageClipAnalyzer implements SemanticAnalyzerInterface {

    @Override
    public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
        tokens.pop();

        ExpressionTreeNode yCoord = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        ExpressionTreeNode xCoord = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
        ExpressionTreeNode path = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);

        return new ImageClip(path, xCoord, yCoord);
    }
}
