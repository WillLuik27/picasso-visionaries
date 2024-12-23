package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a unary not (!) function that inverts a grayscale constant value.
 * 
 * For a grayscale constant, the result is calculated as (1 - value).
 */
public class Not extends UnaryFunction {

    /**
     * Creates a Not expression tree with the given parameter.
     * 
     * @param param the expression to be inverted
     * @author Joe Holden
     */
    public Not(ExpressionTreeNode param) {
        super(param);
    }

    /**
     * Evaluates this expression at the given (x, y) point by inverting the grayscale value.
     * 
     * @return the inverted grayscale color (1 - value)
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor operandResult = param.evaluate(x, y);

        // Invert each component by negating it
        double red = -operandResult.getRed();
        double green = -operandResult.getGreen();
        double blue = -operandResult.getBlue();

        return new RGBColor(red, green, blue);
    }

    /**
     * Simplifies the Not expression if possible.
     * 
     * @return a simplified expression if applicable, otherwise this expression
     */
    @Override
    public ExpressionTreeNode simplify() {
        // Simplify the parameter first
        param = param.simplify();

        // If the parameter is a Constant, compute the inverted value directly
        if (param instanceof Constant) {
            double value = ((Constant) param).getValue();
            double invertedValue = 1 - value;
            return new Constant(invertedValue);
        }

        // If the parameter is another Not, simplify !(!x) to x (double negation)
        if (param instanceof Not) {
            return ((Not) param).param;
        }

        // No further simplification possible, return the current Not expression
        return this;
    }

}
