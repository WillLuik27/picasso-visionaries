package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * Represents a function that takes two arguments and adds them together.
 * 
 * @author Micah Tongen
 * @Author Tim Johns
 */
public class Equals extends ExpressionTreeNode{

	ExpressionTreeNode left;
    ExpressionTreeNode right;
    
    /**
     * Creates an Equals object that consists of the variable name (left)
     * and expression (right).
     * 
     * @param left the left operand (variable)
     * @param right the right operand (expression)
     */
    public Equals(ExpressionTreeNode left, ExpressionTreeNode right) {
    	this.left = left;
        this.right = right;

    }



    /**
     * Evaluates this assignment by creating a variable with the
     * left node, and then storing the mapping in our static variable
     * HashMap mapped to the right expression tree.
     * @return 
     */
    public RGBColor evaluate(double x, double y) {
    	
    	Variable var = (Variable) left;
        Variable.assignor.put(var, right);
        //Dummy color return:
        return new RGBColor(0, 0, 0);
    }


}