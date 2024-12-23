package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.UnaryFunction;

/**
 * @author williamluik
 */
public class Atan extends UnaryFunction {

	/**
	 * Create a arc tan expression tree that creates a arc tan function
	 * 
	 * @param param the expression to arc tan
	 */
	public Atan(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the arc tan of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the arc tan of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.atan(result.getRed());
		double green = Math.atan(result.getGreen());
		double blue = Math.atan(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}
