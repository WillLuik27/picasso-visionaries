package picasso.view;
import picasso.view.commands.FractalEvaluator;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import picasso.view.commands.TextBox;
import picasso.model.Pixmap;
import picasso.parser.RandomExpressionGenerator;
import picasso.util.ThreadedCommand;
import picasso.view.commands.*;

/**
 * Main container for the Picasso application
 *
 * @author Robert Duvall (rcd@cs.duke.edu)
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	
	private Canvas canvas;
	
	public Frame(Dimension size) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Add our team name:
		setTitle("Picasso by team Visionaries");

		// create GUI components
		this.canvas = new Canvas(this);
		canvas.setSize(size);
		
		// add commands to test here
		ButtonPanel commands = new ButtonPanel(canvas);
		ButtonPanel bottomCommands = new ButtonPanel(canvas);
		bottomCommands.add("Open", new Reader(canvas));
		bottomCommands.add("Save", new Writer());
		
		bottomCommands.add("New Window", new NewWindow());
		
		//Create the text box to input expressions into:
		TextBox textBox = new TextBox();
		
		//Create an action listener to execute the textbox's expression when
		//the enter box is clicked:
		textBox.getTextBox().addActionListener(e -> {
			//If the user executes the textbox,
			//collect whatever is currently stored in the text box:
		    String expression = textBox.getText();
		    //If there is an expression (the box isn't empty), add the text to the 
		    //textbox's history.
		    if (!expression.trim().isEmpty()) {
		        textBox.addToHistory(expression);
		        //Then evaluate as normal:
		        Evaluator evaluator = new Evaluator(expression, canvas);
		        evaluator.execute(canvas.getPixmap());
		        canvas.refresh();
		    }
		});

		commands.add("Enter", textBox.getTextBox());
		
		commands.add("Evaluate", new ThreadedCommand<Pixmap>(canvas, pixmap -> {	
			String expression = textBox.getText();
			if (textBox.getText().equals("random()")) {
				expression = RandomExpressionGenerator.randomExpression(5, true);
				textBox.setText(expression);
			}
			new Evaluator(expression, canvas).execute(pixmap);    
		}));
		
		commands.add("Random", new ThreadedCommand<Pixmap>(canvas, pixmap -> {
			String expression = RandomExpressionGenerator.randomExpression(5, true);
			new Evaluator(expression, canvas).execute(pixmap);
			textBox.setText(expression);
		}));
		
		commands.add("Fractal", new ThreadedCommand<Pixmap>(canvas, pixmap -> {
		    String expression = textBox.getText();
		    int iterations = 50; 
		    new FractalEvaluator(expression, canvas, iterations).execute(pixmap);
		}));


		// add our container to Frame and show it
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(commands, BorderLayout.NORTH);
		getContentPane().add(bottomCommands, BorderLayout.SOUTH);
		pack();
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
}
