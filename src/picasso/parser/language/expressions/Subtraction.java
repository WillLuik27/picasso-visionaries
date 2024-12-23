package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that takes two arguments and subtracts them.
 * 
 * @author Tim Johns
 * 
 */
public class Subtraction extends BinaryFunction {

    /**
     * Creates a Subtraction expression tree with the given left and right parameters.
     * 
     * @param left the left operand
     * @param right the right operand
     */
    public Subtraction(ExpressionTreeNode left, ExpressionTreeNode right) {
        super(left, right);
    }



    /**
     * Evaluates this expression at the given x, y point by subtracting the results of
     * the left and right operands.
     * 
     * @return the difference of the left and right operands' evaluations
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor leftResult = left.evaluate(x, y);
        RGBColor rightResult = right.evaluate(x, y);

        // Sum the red, green, and blue components of the two results
        double red = leftResult.getRed() - rightResult.getRed();
        double green = leftResult.getGreen() - rightResult.getGreen();
        double blue = leftResult.getBlue() - rightResult.getBlue();

        return new RGBColor(red, green, blue);
    }
    
    @Override
    public ExpressionTreeNode simplify() {
    	//simplify the child nodes first with abstract method
    	super.simplify();
    	
    	//right is 0, then return right... x -0
    	if (right instanceof Constant && ((Constant) right).getValue()== 0) {
    		return left;
    	}
    	
    	if (left instanceof Constant && ((Constant) left).getValue() == 0.0) {
    	    // Will have problem if right is another bin function
    	    if (right instanceof Constant) {
    	        return new Constant(-1 * ((Constant) right).getValue());
    	    }
    	}
    	
    	//if right equals left then make zero node... x-x =0
    	if (left.equals(right)) {
    		return new Constant(0.0);
    	}
    	//else return the same Tree
    	return this;
    }
}
  
