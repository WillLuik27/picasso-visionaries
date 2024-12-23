 package picasso.parser.tokens.functions;

/**
 * Represents the imageClip function token.
 * @author Tim Johns
 */
public class ImageClipToken extends FunctionToken {

    public ImageClipToken() {
        super("ImageClip Function Token");
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
