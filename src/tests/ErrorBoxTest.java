package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;

import picasso.view.Canvas;
import picasso.view.Frame;
import picasso.view.commands.ErrorBox;

/**
 * JUnit testing for the ErrorBox class.
 * 
 * @author Tim Johns
 * 
 * */
class ErrorBoxTest {

    private Canvas canvas;
    private ErrorBox errorBox;
    private final String testMessage = "Test";

    @BeforeEach
    void setUp() {
    	
        //Create a frame and canvas with a set size
    	Dimension SIZE = new Dimension(600, 600);
    	Frame frame = new Frame(SIZE);
        canvas = new Canvas(frame);
        canvas.setSize(500, 500);

        //Initialize the ErrorBox
        errorBox = new ErrorBox(testMessage);
    }

    @Test
    void testErrorBoxDisplay() {
        //Execute the error box command
        errorBox.execute(canvas);

        //Ensure the error box has been added to the canvas
        boolean isErrorBoxAdded = false;
        
        //Get the JPanel that is on the canvas:
        for (var component : canvas.getComponents()) {
            if (component instanceof JPanel) {
            	//Store the panel in panel variable for checking:
                JPanel panel = (JPanel) component;

                //Check if the panel matches the error box by color and size
                if (panel.getBackground().equals(new Color(255, 200, 200)) && panel.getWidth() == 250) {
                    //Confirm that the box was added:
                	isErrorBoxAdded = true;

                    //Ensure that the panel contains the correct label:
                    JLabel label = (JLabel) panel.getComponent(0);
                    assertEquals(testMessage, label.getText());
                    assertEquals(Color.RED, label.getForeground());
                }
            }
        }

        //assertTrue(isErrorBoxAdded, "Error box was not added to the canvas.");
    }

    @Test
    void testErrorBoxRemoval() throws InterruptedException {
        //Execute the error box command
        errorBox.execute(canvas);

        //Wait for 3 seconds for the error box to dissapear:
        Thread.sleep(3000);

        //Initialize variable to confirm that the error box has been removed from the canvas
        boolean wasErrorBoxRemoved = true;
        
        //Get the panel again and store in panel variable:
        for (var component : canvas.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;

                //Check if the panel has the correct color and dimensions:
                if (panel.getBackground().equals(new Color(255, 200, 200)) && panel.getWidth() == 250) {
                    wasErrorBoxRemoved = false;
                }
            }
        }

        assertTrue(wasErrorBoxRemoved, "Error box was not removed from the canvas after 3 seconds.");
    }
}
