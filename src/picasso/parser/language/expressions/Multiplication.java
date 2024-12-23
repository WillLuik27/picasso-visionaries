package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that takes two arguments and adds them together.
 * 
 * @author Tim Johns
 * 
 */
public class Multiplication extends BinaryFunction {

    /**
     * Creates a Multiplication expression tree with the given left and right parameters.
     * 
     * @param left the left operand
     * @param right the right operand
     */
    public Multiplication(ExpressionTreeNode left, ExpressionTreeNode right) {
        super(left, right);
    }



    /**
     * Evaluates this expression at the given x, y point by multiplying the results of
     * the left and right operands.
     * 
     * @return the product of the left and right operands' evaluations
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor leftResult = left.evaluate(x, y);
        RGBColor rightResult = right.evaluate(x, y);

        // Multiply the red, green, and blue components of the two results
        double red = leftResult.getRed() * rightResult.getRed();
        double green = leftResult.getGreen() * rightResult.getGreen();
        double blue = leftResult.getBlue() * rightResult.getBlue();

        return new RGBColor(red, green, blue);
    }
    
    @Override
    public ExpressionTreeNode simplify() {
    	//simplify the child nodes first with abstract method
    	super.simplify();
    	
    	//return zero if either of the children are zero
    	if ((left instanceof Constant && ((Constant) left).getValue() ==0) || 
    			(right instanceof Constant && ((Constant) right).getValue() == 0)) {
    		return new Constant(0.0);
    	}
    	
    	//return the other child if you multiply by 1
    	if (left instanceof Constant && ((Constant) left).getValue() == 1){
    		return right;
    	}
    	if (right instanceof Constant && ((Constant) right).getValue() == 1) {
    		return left;
    	}
    	//else return the same expression tree
    	return this;
    	
    }
}
