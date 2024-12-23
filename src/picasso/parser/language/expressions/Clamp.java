/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * @author williamluik
 */
public class Clamp extends UnaryFunction {

	/**
	 * Create a clamp expression tree that takes as a parameter the given expression
	 * 
	 * @param param
	 */
	public Clamp(ExpressionTreeNode param) {
		super(param);
		
	}

	/**
	 * clamp values greater than 1 to 1 and values less than -1 to -1
	 * 
	 * @return the color from evaluating the clamp of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = clampAlgo(result.getRed());
		double green = clampAlgo(result.getGreen());
		double blue = clampAlgo(result.getBlue());

		return new RGBColor(red, green, blue);
	}
	
	private static double clampAlgo(double value){
		if (value > 1.0) {
			return 1.0; 
		}
		if (value < -1.0) {
			return -1.0;
		}
		//else we are within the bounds and nothing needs to change
		return value;
	}

}
