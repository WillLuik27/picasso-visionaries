package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the multiplication sign token
 * 
 */
public class MultiplicationToken extends CharToken implements OperationInterface {
	int ORDER_VALUE = 2;
	public MultiplicationToken() {
		super(CharConstants.STAR);
		

	}
    public int getOrderValue() {
        return ORDER_VALUE;
    }

}
