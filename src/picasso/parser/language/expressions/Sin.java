package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the sin function in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Micah Tongen
 * 
 */
public class Sin extends UnaryFunction {

	/**
	 * Create a sin expression tree that takes as a parameter the given expression
	 * 
	 * @param param the expression to sin
	 */
	public Sin(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the Sin of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the Sin of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.sin(result.getRed());
		double green = Math.sin(result.getGreen());
		double blue = Math.sin(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}
