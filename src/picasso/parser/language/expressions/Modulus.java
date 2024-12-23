package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that takes two arguments and mods them.
 * 
 * @author Tim Johns
 * 
 */
public class Modulus extends BinaryFunction {

    /**
     * Creates a Modulus expression tree with the given left and right parameters.
     * 
     * @param left the left operand
     * @param right the right operand
     */
    public Modulus(ExpressionTreeNode left, ExpressionTreeNode right) {
        super(left, right);
    }



    /**
     * Evaluates this expression at the given x, y point by dividing the results of
     * the left and right operands and returning the remainder.
     * 
     * @return the modulus of the left and right operands' evaluations
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor leftResult = left.evaluate(x, y);
        RGBColor rightResult = right.evaluate(x, y);

        //Mod the red, green, and blue components of the two results
        double red = leftResult.getRed() % rightResult.getRed();
        double green = leftResult.getGreen() % rightResult.getGreen();
        double blue = leftResult.getBlue() % rightResult.getBlue();

        return new RGBColor(red, green, blue);
    }
    
    @Override 
    public ExpressionTreeNode simplify() {
    	//simplify the child nodes first with abstract method
    	super.simplify();
    	
    	//x % 1 will always be 0
    	if (right instanceof Constant && ((Constant) right).getValue() == 1) {
    		return new Constant(0.0);
    	}
    	//0 % x will always be 0
    	if (left instanceof Constant && ((Constant) left).getValue() == 1) {
    		return new Constant(0.0);
    	}
    	
    	//x % x will always be 0 
    	if (right.equals(left)) {
    		return new Constant(0.0);
    	}
    	
    	//else return the existing string
    	return this;
    }
    
}
