package picasso.view.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import picasso.model.Pixmap;
import picasso.util.FileCommand;
import picasso.view.Canvas;

/**
 * Open the chosen image file and display in the Pixmap target.
 * 
 * @author Robert C Duvall
 * @author Tim Johns
 */
public class Reader extends FileCommand<Pixmap> {

	private Canvas canvas;
	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	public Reader(Canvas canvas) {
		super(JFileChooser.OPEN_DIALOG);
	}

	/**
	 * Displays the image file on the given target.
	 */
	public void execute(Pixmap target) {
		//Get the path to the file:
		String fileName = getFileName();
		//Check if the specified file is a .txt file (not an image)
		if (fileName != null && fileName.endsWith(".txt")) {
			
	        StringBuilder fileContents = new StringBuilder();
	        
	        //Try creating a bufferedReader using the Files convenience class that uses
	        //the path to the fileName.
	        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
	            
	        	//Initialize a variable for the current line:
	        	String line;
	        	
	        	//While the current line isn't empty (haven't reached the end) loop
	        	//through the file:
	            while ((line = reader.readLine()) != null) {
	            	
	                //Look for any comments within the current line:
	                int commentIndex = line.indexOf("//");
	                
	                //If an occurrence is found, only use the substring of the line
	                //ranging from the beginning of the line up until the comment's index:
	                if (commentIndex != -1) {
	                    line = line.substring(0, commentIndex);
	                }
	                
	                //Then, trim the line before appending.
	                line = line.trim();

	                //Append non-empty lines to the fileContents:
	                if (!line.isEmpty()) {
	                    fileContents.append(line).append("\n");
	                }
	            }
	        }
	 
	        //Catch block to handle the exceptions
	        catch (IOException err) {
	            //Print the exception along with line number
	            err.printStackTrace();
	        }
	        
	      //Now, finally, create an evaluator and execute on the target:
            Evaluator evaluator = new Evaluator(fileContents.toString(), canvas);
            evaluator.execute(target);
	 
		}
		
		//If it isn't a text file, try to open the image:
		else if (fileName != null) {
			target.read(fileName);
		}
	}
}
