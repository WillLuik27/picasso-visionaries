package picasso.parser.language.expressions;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Converts YCrCb colors to RGB color space.
 * @author Tim Johns
 */
public class YCrCbToRGB extends UnaryFunction {

    public YCrCbToRGB(ExpressionTreeNode param) {
        super(param);
    }

    @Override
    public RGBColor evaluate(double x, double y) {
        //Get colors:
    	RGBColor yCrCb = param.evaluate(x, y);
        double Y = yCrCb.getRed();
        double Cr = yCrCb.getGreen();
        double Cb = yCrCb.getBlue();

        //Convert YCrCb to RGB
        double red = Y + 2 * Cr;
        double blue = Y + 2 * Cb;
        double green = Y - 0.344136 * Cb - 0.714136 * Cr;

        return new RGBColor(red, green, blue);
    }
}
