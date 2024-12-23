package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that takes two arguments and divides them.
 * 
 * @author Tim Johns
 * 
 */
public class Division extends BinaryFunction {

    /**
     * Creates a Division expression tree with the given left and right parameters.
     * 
     * @param left the left operand
     * @param right the right operand
     */
    public Division(ExpressionTreeNode left, ExpressionTreeNode right) {
        super(left, right);
    }



    /**
     * Evaluates this expression at the given x, y point by dividing the results of
     * the left and right operands.
     * 
     * @return the quotient of the left and right operands' evaluations
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor leftResult = left.evaluate(x, y);
        RGBColor rightResult = right.evaluate(x, y);

        // Sum the red, green, and blue components of the two results
        double red = leftResult.getRed() / rightResult.getRed();
        double green = leftResult.getGreen() / rightResult.getGreen();
        double blue = leftResult.getBlue() / rightResult.getBlue();

        return new RGBColor(red, green, blue);
    }
    
    @Override 
    public ExpressionTreeNode simplify() {
    	//simplify the child nodes first with abstract method
    	super.simplify();
    	
    	//if dividing by 1, then always the left... x/1 =x
    	if (right instanceof Constant && ((Constant) right).getValue() ==1 ) {
    		return left;
    	}
    	
    	//if 0/x == 0 while x not 0 also
    	if (left instanceof Constant && ((Constant) left).getValue()== 0 )
    		if (!(right instanceof Constant && ((Constant) right).getValue()== 0 )) {
    			return new Constant(0.0);
    		}
    	// x/x = 1
    	if (right.equals(left)) {
    		return new Constant(1.0);
    	}
    	//else return the same tree 
    	return this;
    }
}
