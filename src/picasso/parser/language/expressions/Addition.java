package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that takes two arguments and adds them together.
 * 
 * @author Joe Holden
 * 
 */
public class Addition extends BinaryFunction {

    /**
     * Creates a Addition expression tree with the given left and right parameters.
     * 
     * @param left the left operand
     * @param right the right operand
     */
    public Addition(ExpressionTreeNode left, ExpressionTreeNode right) {
        super(left, right);
    }



    /**
     * Evaluates this expression at the given x, y point by adding the results of
     * the left and right operands.
     * 
     * @return the sum of the left and right operands' evaluations
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor leftResult = left.evaluate(x, y);
        RGBColor rightResult = right.evaluate(x, y);

        // Sum the red, green, and blue components of the two results
        double red = leftResult.getRed() + rightResult.getRed();
        double green = leftResult.getGreen() + rightResult.getGreen();
        double blue = leftResult.getBlue() + rightResult.getBlue();

        return new RGBColor(red, green, blue);
    }
    
    @Override
    public ExpressionTreeNode simplify() {
    	//simplify the child nodes first with abstract method
    	super.simplify();
    	
    	//if either child is 0 then just return the other child
    	if (left instanceof Constant && ((Constant) left).getValue() ==0 ) {
    		return right;
    	}
    	if (right instanceof Constant && ((Constant) right).getValue() == 0) {
    		return left;
    	}
    	
    	//else return the same tree
    	return this;
    	
    }
}
