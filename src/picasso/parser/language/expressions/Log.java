/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * 
 */
public class Log extends UnaryFunction {

	/**
	 * Create a log expression tree that takes as a parameter the given expression
	 * 
	 * @param param the expression to log
	 */
	public Log(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the log of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the log of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.log(Math.abs(result.getRed()));
		double green = Math.log(Math.abs(result.getGreen()));
		double blue = Math.log(Math.abs(result.getBlue()));

		return new RGBColor(red, green, blue);
	}

}


