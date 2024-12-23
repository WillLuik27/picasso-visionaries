package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the exponential function in the Picasso language.
 * 
 * @author Joe Holden
 */
public class Exp extends UnaryFunction {

    /**
     * Create an exponential expression tree that takes as a parameter the given expression
     * 
     * @param param the expression to compute e^param
     */
    public Exp(ExpressionTreeNode param) {
        super(param);
    }

    /**
     * Evaluates this expression at the given x, y point by computing e raised to the power of the function's parameter.
     * 
     * @return the color from evaluating e^param of the expression's parameter
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor result = param.evaluate(x, y);
        
        // Apply e^parameter to each color component
        double red = Math.exp(result.getRed());
        double green = Math.exp(result.getGreen());
        double blue = Math.exp(result.getBlue());

        return new RGBColor(red, green, blue);
    }
}