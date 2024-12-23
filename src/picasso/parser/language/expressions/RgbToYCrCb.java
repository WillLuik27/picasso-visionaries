package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * Converts RGB colors to YCrCb color space.
 */
public class RgbToYCrCb extends UnaryFunction {

    public RgbToYCrCb(ExpressionTreeNode param) {
        super(param);
    }

    /**
	 * Gets the red, green, and blue values,
	 * then does a conversion on them and creates the new color.
	 * 
	 * @return the color made with the new Y, Rr and Cb values.
	 */
    @Override
    public RGBColor evaluate(double x, double y) {
        RGBColor rgb = param.evaluate(x, y);
        //Get the normal rgb colors:
        double red = rgb.getRed();
        double green = rgb.getGreen();
        double blue = rgb.getBlue();

        //Convert the RGB colors to YCrCb
        double Y = 0.299 * red + 0.587 * green + 0.114 * blue;
        double Cr = 0.5 * (red - Y);
        double Cb = 0.5 * (blue - Y);

        //Return the new colors:
        return new RGBColor(Y, Cr, Cb);
    }
}
