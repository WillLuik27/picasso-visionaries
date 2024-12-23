/**
 * 
 */
package picasso.parser.tokens.functions;

/**
 * 
 * @author Micah Tongen
 */
public class PerlinBWToken extends FunctionToken {
	
	public PerlinBWToken() {
		super("PerlinBW Function Token");
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return true;
	}
}
