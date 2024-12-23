package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the modulus token
 * 
 */
public class ModulusToken extends CharToken implements OperationInterface {
	int ORDER_VALUE = 3;
	public ModulusToken() {
		super(CharConstants.MOD);
		

	}
    public int getOrderValue() {
        return ORDER_VALUE;
    }
}
