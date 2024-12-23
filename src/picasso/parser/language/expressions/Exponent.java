package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that takes two arguments and calculates the exponent.
 * 
 * @author Tim Johns
 * 
 */
public class Exponent extends BinaryFunction {

    /**
     * Creates a Exponent expression tree with the given left and right parameters.
     * 
     * @param left the left operand
     * @param right the right operand
     */
    public Exponent(ExpressionTreeNode left, ExpressionTreeNode right) {
        super(left, right);
    }



    /**
     * Evaluates this expression at the given x, y point by exponentiating the results of
     * the left and right operands.
     * 
     * @return the product of the left and right operands' evaluations
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor leftResult = left.evaluate(x, y);
        RGBColor rightResult = right.evaluate(x, y);

        // Exponentiate the red, green, and blue components of the two results
        double red = Math.pow(leftResult.getRed(), rightResult.getRed());
        double green = Math.pow(leftResult.getGreen(),rightResult.getGreen());
        double blue = Math.pow(leftResult.getBlue(),rightResult.getBlue());

        return new RGBColor(red, green, blue);
    }
    
    @Override 
    public ExpressionTreeNode simplify() {
    	//simplify the child nodes first with abstract method
    	super.simplify();
    	
    	//zero power rule... x^0  =1
    	if (right instanceof Constant && ((Constant) right).getValue() ==0) {
    		return new Constant(1.0);
    	}
    	
    	//x^1 = x
    	if (right instanceof Constant && ((Constant) right).getValue() ==1 ) {
    		return left;
    	}
    	
    	//1^x always equal to 1
    	if (left instanceof Constant && ((Constant) left).getValue()==1 ) {
    		return new Constant(1.0);
    	}
    	
    	//else keep the same tree
    	return this;
    }
}
