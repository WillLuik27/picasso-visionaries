package picasso.view.commands;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.BorderFactory;

import picasso.util.Command;
import picasso.view.Canvas;

/**
 * This class creates a temporary error bar with a given string.
 * The error bar spans the entire width of the canvas and appears at the top of the screen.
 * 
 * @author Tim Johns
 */
public class ErrorBox implements Command<Canvas> {

    private static final int BOX_HEIGHT = 30; // Height of the error bar
    private static final int BOX_Y_POSITION = 30; // Adjust based on ButtonPanel height
    private final JPanel errorBox;
    private final JLabel messageLabel;

    /**
     * Create an error bar for the user to read.
     * @param message An error message to be displayed
     */
    public ErrorBox(String message) {

        //Create the error bar panel:
        errorBox = new JPanel();
        //Set background to a light red color:
        errorBox.setBackground(new Color(255, 200, 200));
        //Set border to a darker red:
        errorBox.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        //Use null layout for manual positioning
        errorBox.setLayout(null);

        //Create the error message label:
        messageLabel = new JLabel(message, JLabel.CENTER);
        //Set the label text color to red:
        messageLabel.setForeground(Color.RED);
        //Set bounds for the label (dynamically updated later)
        messageLabel.setBounds(10, 0, 0, BOX_HEIGHT);

        //Add the message label to the error bar:
        errorBox.add(messageLabel);

        //Set the error bar height dynamically based on constant BOX_HEIGHT:
        errorBox.setSize(0, BOX_HEIGHT); // Width is updated dynamically in execute
    }

    /**
     * Displays the error bar on the target canvas for 3 seconds.
     * @param target The target canvas to display the error bar
     */
    @Override
    public void execute(Canvas target) {

        //Set the width of the error bar to the full width of the canvas:
        int boxWidth = target.getWidth();
        errorBox.setSize(boxWidth, BOX_HEIGHT);

        //Update label bounds to fit the error box:
        messageLabel.setBounds(0, 0, boxWidth, BOX_HEIGHT);

        //Position the error bar just below the top ButtonPanel:
        errorBox.setLocation(0, BOX_Y_POSITION);

        //Ensure the target layout supports adding new components:
        target.setLayout(null);

        //Add the error bar to the canvas:
        target.add(errorBox);

        //Refresh the UI:
        SwingUtilities.invokeLater(target::refresh);

        //Remove the error bar after 3 seconds:
        new Timer(3000, e -> {
            target.remove(errorBox);
            SwingUtilities.invokeLater(target::refresh);
        }).start();
    }
}
