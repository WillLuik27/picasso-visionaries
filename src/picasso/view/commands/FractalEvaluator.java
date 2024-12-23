package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;

import picasso.model.Pixmap;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.RGBColor;
import picasso.view.Canvas;

/**
 * Evaluates an expression iteratively to create evolving fractal patterns.
 * @author Joe Holden
 */
public class FractalEvaluator extends Evaluator {
    private int iterations;

    public FractalEvaluator(String infixString, Canvas canvas, int iterations) {
        super(infixString, canvas);
        this.iterations = iterations;
    }

    @Override
    public void execute(Pixmap target) {
        Dimension size = target.getSize();
        ExpressionTreeNode expr = createExpression(infixString);

        // Create a buffer to store the previous iteration's results
        RGBColor[][] previousColors = new RGBColor[size.width][size.height];

        for (int i = 0; i < iterations; i++) {
            for (int imageY = 0; imageY < size.height; imageY++) {
                double evalY = imageToDomainScale(imageY, size.height);
                for (int imageX = 0; imageX < size.width; imageX++) {
                    double evalX = imageToDomainScale(imageX, size.width);

                    // Modify inputs based on previous iteration's color
                    if (previousColors[imageX][imageY] != null) {
                        evalX += previousColors[imageX][imageY].getRed() * 0.01;
                        evalY += previousColors[imageX][imageY].getGreen() * 0.01;
                    }

                    RGBColor result = expr.evaluate(evalX, evalY);
                    previousColors[imageX][imageY] = result;
                    target.setColor(imageX, imageY, result.toJavaColor());
                }
            }
            // Refresh the canvas after each iteration to visualize progress
            canvas.refresh();
        }
    }
}
