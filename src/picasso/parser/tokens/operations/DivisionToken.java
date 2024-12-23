package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the division sign token
 * 
 */
public class DivisionToken extends CharToken implements OperationInterface {
	int ORDER_VALUE = 2;
	public DivisionToken() {
		super(CharConstants.SLASH);
	}
    public int getOrderValue() {
        return ORDER_VALUE;
    }
}
