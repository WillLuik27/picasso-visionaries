package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the plus sign token
 * 
 */
public class ExponentToken extends CharToken implements OperationInterface {
	int ORDER_VALUE = 3;
	public ExponentToken() {
		super(CharConstants.CARET);
		
	}
	    public int getOrderValue() {
	        return ORDER_VALUE;
	    }

	
}
