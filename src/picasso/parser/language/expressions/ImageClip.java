package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;


/**
 * Implements the imageClip function, which extends
 * the edges of an image to the edges of the frame.
 * @author Tim Johns
 */
public class ImageClip extends ExpressionTreeNode {

    private final ExpressionTreeNode path;
    private final ExpressionTreeNode xCoordNode;
    private final ExpressionTreeNode yCoordNode;

    /**
     * @param path the image path
     * @param xCoordNode the x-coordinate expression
     * @param yCoordNode the y-coordinate expression
     */
    public ImageClip(ExpressionTreeNode path, ExpressionTreeNode xCoordNode, ExpressionTreeNode yCoordNode) {
        this.path = path;
        this.xCoordNode = xCoordNode;
        this.yCoordNode = yCoordNode;
    }

    @Override
    public RGBColor evaluate(double x, double y) {
        double xCoord = xCoordNode.evaluate(x, y).getRed();
        double yCoord = yCoordNode.evaluate(x, y).getRed();

        //Clip the x coordinate:
        if (xCoord < -1.0) {
        	 //Clip to the left edge
            xCoord = -1.0;
        } else if (xCoord > 1.0) {
        	//Clip to the right edge
            xCoord = 1.0;
        }

        //Clip the y coordinates:
        if (yCoord < -1.0) {
        	//Clip the top edge:
            yCoord = -1.0;
        } else if (yCoord > 1.0) {
        	 //Clip the bottom edge
            yCoord = 1.0;
        }

        //Evaluate the image at the clipped coordinates
        return path.evaluate(xCoord, yCoord);
    }
    
    

}
