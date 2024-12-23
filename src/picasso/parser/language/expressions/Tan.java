/**
 * 
 */
package picasso.parser.language.expressions;

import java.util.Stack;

import picasso.parser.language.expressions.UnaryFunction;
import picasso.parser.language.ExpressionTreeNode;

/**
 * @author williamluik
 */
public class Tan extends UnaryFunction {

	/**
	 * Create a tan expression tree that creates a tan function
	 * 
	 * @param param the expression to tan
	 */
	public Tan(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the tan of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the tan of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.tan(result.getRed());
		double green = Math.tan(result.getGreen());
		double blue = Math.tan(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}

