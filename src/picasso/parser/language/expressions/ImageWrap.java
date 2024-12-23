/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;



/**
 * 
 * @author William Luik
 */
public class ImageWrap extends ExpressionTreeNode {

	
	private final ExpressionTreeNode path;
	private final ExpressionTreeNode xCoordNode; 
	private final ExpressionTreeNode yCoordNode;
	
	/**
	 *  @param path
	 *  @param xCoord
	 *  @param yCoord
	 */
	public ImageWrap(ExpressionTreeNode path, ExpressionTreeNode xCoordNode, ExpressionTreeNode yCoordNode ) {
		this.path = path;
		this.xCoordNode = xCoordNode;
		this.yCoordNode = yCoordNode;
	}

	/** 
	 * get the wrapped location of x and y.
	 * then call the path.java to evaluate
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		double xCoord = xCoordNode.evaluate(x, y).getRed(); //not confident about the toDouble
		double yCoord = yCoordNode.evaluate( x, y).getRed();
		
		//Wrap the values to the range from the public class in Wrap.java
		double wrappedX = Wrap.wrapAlgo(xCoord);
		double wrappedY = Wrap.wrapAlgo(yCoord);
		
		return path.evaluate(wrappedX, wrappedY);
		
	}
	

	

}
