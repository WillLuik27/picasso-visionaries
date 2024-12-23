package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the unary not (!) token.
 */
public class NotToken extends CharToken implements OperationInterface {
	int ORDER_VALUE = 3;
    public NotToken() {
        super(CharConstants.BANG); 
    }

	@Override
	public int getOrderValue() {
		return ORDER_VALUE;
	}

}
