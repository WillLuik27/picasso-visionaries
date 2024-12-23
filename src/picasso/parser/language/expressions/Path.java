/**
 * 
 */
package picasso.parser.language.expressions;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import picasso.parser.language.ExpressionTreeNode;

/**
 * 
 * @author William Luik
 */
public class Path extends ExpressionTreeNode {

	private final String path; 
	private BufferedImage image; 

	
	public Path(String path) {
		this.path = path;
	}

	/** 
	 * Steps will be to:
	 * 1. find what the pixel coordinates are relative to the parameter x, y. Map [-1,1] to pixel [min,max]width/height
	 * 2. clamp to make sure that we are not outside the bounds of the image
	 * 3. get the rgb value from the image
	 * 4. map this from the 255 rgb to [-1, 1]
	 * 5. reutrn RGBColor like in Constant.java
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		if (image == null) {
			loadImage();
		}
		
		//map from the x,y parameters to the image width and height
		int imageX = (int) ((x + 1) / 2 * (image.getWidth() - 1)); 
		int imageY = (int) ((y + 1) / 2 * (image.getHeight() -1));
		
		//clamp so we dont go over the bounds
		imageX = Math.max(0,  Math.min(imageX,  image.getWidth() -1)); 
		imageY = Math.max(0,  Math.min(imageY,  image.getHeight() -1)); 
		
		//get the RGB value of the image
		int rgbValue = image.getRGB(imageX, imageY); 
		Color color = new Color(rgbValue);
		
		//map from 225 rgb to [-1,1]
		double red = (color.getRed() /225.0) * 2- 1; 
		double green = (color.getGreen() /225.0) * 2- 1; 
		double blue = (color.getBlue() /225.0) * 2- 1; 
		
		//return the RGB data type 
		return new RGBColor(red, green, blue); 
		
		
		
	}
	
	private void loadImage() {
		try {
		File file = new File("images/" + path);
		System.out.println(file.getAbsolutePath());
		image = ImageIO.read(file);
		
		if (image ==null) {
			throw new RuntimeException("No file for the given path");
		
		}}catch (IOException e) {
			throw new RuntimeException("Error Processing Image");
		}
	}

	public String getPath() {
		return path;
	}
}


