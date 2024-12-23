/**
 * 
 */
package picasso.parser.language.expressions;
import java.awt.Color;
import java.util.Random;

import picasso.parser.language.expressions.RGBColor;


import picasso.parser.language.ExpressionTreeNode;

/**
 * 
 * @author Micah Tongen
 */
public class PerlinBW extends ExpressionTreeNode {

	
	private final ExpressionTreeNode expr1; 
	private final ExpressionTreeNode expr2;
	
	/**
	 *  @param path
	 *  @param xCoord
	 *  @param yCoord
	 */
	public PerlinBW(ExpressionTreeNode left, ExpressionTreeNode right ) {
		this.expr1 = left;
		this.expr2 = right;
	}

	public static double perlinNoise(double x, double y) {
        int X = (int) Math.floor(x) & 255; // Wrap to 0-255
        int Y = (int) Math.floor(y) & 255;
        x -= Math.floor(x);
        y -= Math.floor(y);
        double u = fade(x);
        double v = fade(y);

        int A = (perm[X] + Y) & 255;
        int B = (perm[X + 1] + Y) & 255;

        return lerp(v, lerp(u, grad(perm[A], x, y),
                            grad(perm[B], x - 1, y)),
                       lerp(u, grad(perm[A + 1], x, y - 1),
                            grad(perm[B + 1], x - 1, y - 1)));
    }
	
	
	/** 
	 * get the wrapped location of x and y.
	 * then call the path.java to evaluate
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		Random rand = new Random();
		double xval = expr1.evaluate(x, y).getRed() * rand.nextInt(255); 
		double yval = expr2.evaluate( x, y).getBlue() * rand.nextInt(255);
		
		
		double noiseValue = perlinNoise(xval, yval);
		
		
        return new RGBColor(noiseValue, noiseValue, noiseValue);
		
	}
	

	// Fade function for Perlin noise
    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    // Linear interpolation
    private static double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    // Gradient function
    private static double grad(int hash, double x, double y) {
        int h = hash & 15;
        double u = h < 8 ? x : y;
        double v = h < 4 ? y : (h == 12 || h == 14 ? x : 0);
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    // Permutation table
    private static final int[] perm = new int[512];
    private static final int[] p = {
        151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225,
        140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148,
        247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32
    };

}
