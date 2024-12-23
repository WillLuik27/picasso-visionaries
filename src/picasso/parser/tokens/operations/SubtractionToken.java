package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the minus sign token
 * 
 */
public class SubtractionToken extends CharToken implements OperationInterface {
	int ORDER_VALUE = 1;
	public SubtractionToken() {
		super(CharConstants.MINUS);
	

	}
    public int getOrderValue() {
        return ORDER_VALUE;
    }
}
