package picasso.parser.language.expressions;
import java.util.Map;
import java.util.HashMap;


import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a variable
 * 
 * @author Sara Sprenkle
 *
 */
public class Variable extends ExpressionTreeNode {

	private String name;
	
	public static Map<ExpressionTreeNode, ExpressionTreeNode> assignor = new HashMap<ExpressionTreeNode, ExpressionTreeNode>();

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public RGBColor evaluate(double x, double y) throws RuntimeException{
	    // Check if this variable has been assigned
	    ExpressionTreeNode assignedExpression = assignor.get(this);

	    if (assignedExpression == null) {
	        throw new RuntimeException("Variable '" + name + "' is not assigned to any value.");
	    }

	    // Evaluate the assigned expression
	    return assignedExpression.evaluate(x, y);
	}


	public String getName() {
		return name;
	}
	
	
    /**
     * Overrides equals to compare variables by their name.
     * @param o the object to compare
     * @return true if the object is a Variable with the same name
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        Variable variable = (Variable) o;
        return name.equals(variable.name);
    }

    /**
     * Overrides hashCode to compute the hash based on the variable's name.
     * Overriding this method was necessary because the program was behaving
     * unpredictably when trying to retrieve stored variable mappings in the
     * HashSet. You want to use the hashCode of the name, not the object.
     * This is used when checking for the equality of variables.
     * @return the hash code of the variable's name.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }


}
