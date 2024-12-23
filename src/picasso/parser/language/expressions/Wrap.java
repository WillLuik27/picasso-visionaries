/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * 
 * @author William Luik
 */
public class Wrap extends UnaryFunction {
	
	static final double range = 2.0;
	static final double min = -1.0; //class vars used for wrap algorithm 

	/**
	 * Create a wrap expression tree that takes as a parameter the given expression
	 * 
	 * @param param
	 */
	public Wrap(ExpressionTreeNode param) {
		super(param);
		
	}

	/**
	 * Evaluates this expression at the given x,y point by wrapping values outside of the bounds of [-1,1] into the function
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the floor of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = wrapAlgo(result.getRed());
		double green = wrapAlgo(result.getGreen());
		double blue = wrapAlgo(result.getBlue());

		return new RGBColor(red, green, blue);
	}
	
	public static double wrapAlgo (double value) {
		double wrapValue = (((value -min)% range + range)% range ) + min;
		return wrapValue;
		
	}

}
