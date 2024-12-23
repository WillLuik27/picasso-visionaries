/**
 * 
 */
package picasso.parser.tokens.functions;

/**
 * 
 * @author Micah Tongen
 */
public class PerlinColorToken extends FunctionToken {
	
	public PerlinColorToken() {
		super("PerlinColor Function Token");
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
