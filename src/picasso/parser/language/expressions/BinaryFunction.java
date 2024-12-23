/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * 
 * @author William Luik
 */
public abstract class BinaryFunction extends ExpressionTreeNode {

    // The two parameters for the two branches of binary function
    ExpressionTreeNode left;
    ExpressionTreeNode right;

    /**
     * Creates a binary expression tree with the given left and right parameters.
     * 
     * @param left the left operand
     * @param right the right operand
     */
    public BinaryFunction(ExpressionTreeNode left, ExpressionTreeNode right) {
        this.left = left;
        this.right = right;
    }

    /**
	/**
	 * Returns the string representation of the function in the format "<ClassName>:
	 * <parameter>"
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String classname = this.getClass().getName();
		return classname.substring(classname.lastIndexOf(".") + 1) + "("  + left + ", " + right + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof BinaryFunction)) {
            return false;
        }

        // Make sure the objects are the same type
        BinaryFunction other = (BinaryFunction) o;

        // Check if the left and right operands are equal
        return this.left.equals(other.left) && this.right.equals(other.right);
    }
    
    
    /**
     * recursive simplify method that will be specified for Multiply and Add
     * @return simplifed node if possible
     */
    
    public ExpressionTreeNode simplify() {
        // Recursively simplify left and right branches
        left = left instanceof BinaryFunction ? ((BinaryFunction) left).simplify() : left;
        right = right instanceof BinaryFunction ? ((BinaryFunction) right).simplify() : right;
        
        return this;
    }


}
