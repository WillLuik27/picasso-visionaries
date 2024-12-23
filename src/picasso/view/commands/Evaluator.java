package picasso.view.commands;


import java.awt.Color;
import java.awt.Dimension;


import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import picasso.view.Canvas;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 * @author Sara Sprenkle
 */
public class Evaluator implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;

	protected String infixString;
	protected Canvas canvas;
	
	

	//Constructor that just takes in a string.
	public Evaluator(String infixString, Canvas canvas) {
		this.infixString = infixString;
		this.canvas = canvas;
	}	
	
	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) {
		
		//Get the text in the text box:
		String infix = infixString;
		
		//If there is an error, display an error message. 
        if (infix == null || infix.trim().isEmpty()) {
            ErrorBox error = new ErrorBox("No expression provided");
	    	error.execute(canvas);
	    	System.err.println("No expression provided.");
            return;
        }
        
		//create the expression to evaluate from the text box:
      
     // Create the expression to evaluate from the text box:
        ExpressionTreeNode expr = createExpression(infix);
        
		
		//evaluate it for each pixel
		Dimension size = target.getSize();
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = imageToDomainScale(imageX, size.width);
				
				  try {
			            //Evaluate the color for the current pixel
			            Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
			            target.setColor(imageX, imageY, pixelColor);
			        } 
				  catch (Exception e) {
			            //Handle runtime evaluation errors
			            System.err.println("Error evaluating expression at (" + evalX + ", " + evalY + "): " + e.getMessage());
			            ErrorBox error = new ErrorBox(e.getMessage());
				    	error.execute(canvas);
			            e.printStackTrace(); // Log stack trace for debugging		         
			            return;
			        }
			}
		}
	}

	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}

	/**
	 * Method used to create expressions from an infix string:
	 * @param String infix  - the infix expression
	 * @returns ExpressionTreeNode - the root node for the infix string
	 */
protected ExpressionTreeNode createExpression(String infix) {

		//Create an expressionTreeGenerator:
		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		
		try {
            //Test to ensure that an expression can be made.
            ExpressionTreeNode test = expTreeGen.makeExpression(infix);
        } 
		catch (Exception e) {
			
            //Handle errors generating a tree:
            System.err.println(e.getMessage());
            ErrorBox error = new ErrorBox(e.getMessage());
	    	error.execute(canvas);
            e.printStackTrace(); // Log stack trace for debugging		         
        }
		
		return expTreeGen.makeExpression(infix); 

		
	}
	public String getInfixString() {
	    return infixString;
	}

}
