package picasso.view.commands;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * This class creates textBox objects. These objects contain a 
 * number of useful attributes that allow the user to scroll through
 * the history of executed statements from the text box.
 * 
 * @author Tim Johns
 * */
public class TextBox {
	
	//Controls the width of the text box:
    private static final int COLUMNS = 35;
    private JTextField textField;
    
    //Initialize a list to contain the history of commands:
    private List<String> history;
    
    //Initialize an int to store the index of the list:
    private int historyPointer;

    /**
     * Constructs a text box. Initializes fields to 
     * contain the textField itself, the list containing the textbox's
     * history of executed expressions, and the variable that is
     * storing the index of that list, which the user will
     * use to scroll through the history.
     * 
     * */
    public TextBox() {
    	
        textField = new JTextField(COLUMNS);
        history = new ArrayList<>();
        historyPointer = -1;

        //Add a KeyListener to the textbox to listen for up and down clicks on the keyboard:
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	//If the key event is an 'UP' on the arrow pad, move backwards through the
            	//history by subtracting 1 from the index:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    navigateHistory(-1);
                } 
                //If the key event is a 'DOWN' on the arrow pad, move forward through the
                //history by adding one to the index:
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    navigateHistory(1);
                }
            }
        });
    }
	
	/**
	 * Returns the textField to the user.
	 *  @returns textField - a JTextField object to be used.
	 */
	public JTextField getTextBox() {
		return textField;
	}
	
	/**
	 * Returns the textField to the user.
	 *  @returns The text that the user entered.
	 */
	public String getText() {
		return textField.getText();
	}
	
	public void setText(String t) {
		textField.setText(t);
	}

    /**
     * Adds the current expression to the list of recorded expressions.
     * Adds executed expression in the text box to the history.
     * */
    public void addToHistory(String currentExpression) {
    	
    	//Only add the expression if it isn't an empty string, and if it isn't the same expression as the 
    	//previously added expression (the element at the stored at the very end of the list).
        if (!currentExpression.isEmpty() && (history.isEmpty() || !currentExpression.equals(history.get(history.size() - 1)))) {
            //Add the expression to the list:
        	history.add(currentExpression);
        }
        //Reset index to the latest entry
        historyPointer = history.size();
    }

    /**
     * Updates the pointer to the current expression in the 
     * history list.
     * 
     * @param int direction
     * */
    private void navigateHistory(int direction) {
    	
    	//If there isn't anything in the history (you haven't executed any statements)
    	//Just return and do nothing.
        if (history.isEmpty()) {
            return;
        }
        
        //Otherwise, update the current index:
        historyPointer += direction;
        
        //If the history index tries to move earlier than the first entry,
        //just force the index to remain at the first expression in the history:
        if (historyPointer < 0) {
            historyPointer = 0;
        } 
        
        //If the history index tries to move past the end of the list, just
        //forceably set the index to the index of the latest expression in the list:
        else if (historyPointer >= history.size()) {
            historyPointer = history.size(); 
            
            //Clear the textbox once the user attempts to move past the latest 
            //expression in history. This allows them to return to a clean box
            //to enter new expressions (like the command line).
            textField.setText("");
            return;
        }
        
        //After navigating to the correct position in the list of expressions,
        //display the expression in the list at that index in the text box:
        textField.setText(history.get(historyPointer));
    }

}
