package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the plus sign token
 * 
 */
public class AdditionToken extends CharToken implements OperationInterface {
	int ORDER_VALUE = 1;
	public AdditionToken() {
		super(CharConstants.PLUS);

	}
    public int getOrderValue() {
        return ORDER_VALUE;
    }
}
