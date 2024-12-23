/**
 * 
 */
package picasso.view.commands;

import picasso.Main;
import picasso.view.Frame;

import picasso.model.Pixmap;
import picasso.util.*;


/**
 * Creates a new window for users to view multiple images at once.
 * 
 * @author Cailen Graham
 */
public class NewWindow extends NamedCommand<Pixmap>{
	
	private static Command<Pixmap> myCommand;

	public NewWindow() {
		super("NewTab", myCommand);
	}
	
	public void execute(Pixmap target) {
		Frame frame = new Frame(Main.SIZE);
		frame.pack();
		frame.setVisible(true);
	}
}
