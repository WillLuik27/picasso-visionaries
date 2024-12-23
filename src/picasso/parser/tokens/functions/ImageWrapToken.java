/**
 * 
 */
package picasso.parser.tokens.functions;

/**
 * 
 * @author William Luik
 */
public class ImageWrapToken extends FunctionToken {
	
	public ImageWrapToken() {
		super("ImageWrap Function Token");
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
