package picasso.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import picasso.parser.language.BuiltinFunctionsReader;

public class RandomExpressionGenerator {

	public static String randomExpression(int numThings, boolean hasIW) {
		//Sets up output and random object
		String expression = "";
        Random rand = new Random();
		
        int IWorNah = rand.nextInt(2);
        //Handles if they want an imageWrap, but makes sure we don't have too many nested ImageWraps.
		if((IWorNah == 1) & (hasIW)) {
			expression = expression + "imageWrap(";
			List<String> images = new ArrayList<String>();
			try {
				images = GetImagesFromFolder.getImages();
			} catch (IOException e) {
				e.printStackTrace();
			}
			expression += "\"" + images.get(rand.nextInt(images.size())) + "\", ";
			expression += RandomExpressionGenerator.randomExpression(2, false) + ", ";
			expression += RandomExpressionGenerator.randomExpression(2, false) + ")";
			return expression;
		}		
        
        //Figure out how many operations and functions you are doing
		int numFunctions = numThings;
		int numOperations = numFunctions - 1;
		
		//Get all the functions
		List<String> myFunctionsList = new ArrayList<String>(BuiltinFunctionsReader.getFunctionsList());
		myFunctionsList.remove("imageWrap");
		myFunctionsList.remove("perlinColor");
		myFunctionsList.remove("perlinBW");

		int numfunctions = myFunctionsList.size();
		
		//Get all the operations
		Properties opProps = new Properties();
		try {
			opProps.load(new FileReader("conf/operations.prop"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Set<Object> operationSet = opProps.keySet();
		int numoperations = operationSet.size();
		String[] operationArray = operationSet.toArray(new String[numoperations]);
		
		for (int i = 0; i < numOperations; i++) {
			//Generate random function and add it to expression
			//Use a boolean to make a color every other time
			if(rand.nextBoolean()) {
				String func = myFunctionsList.get(rand.nextInt(numfunctions-1));
				expression = expression + func;
				if(rand.nextBoolean()) {
					expression = expression + "(x) ";
				}
				else {
					expression = expression + "(y) ";
				}
			}
			//Generate random Color and add it to expression
			else {
				expression += "[";
				for (int j = 0; j < 2; j++) {
					expression += rand.nextDouble(-1, 1) + ",";
				}
				expression += rand.nextDouble(-1, 1) + "]";
			}
			//Generate random operator and add it to expression
			String op = operationArray[rand.nextInt(numoperations)];
			expression = expression + op + ' ';
		}
		//Finish with a function
		String func = myFunctionsList.get(rand.nextInt(numfunctions-1));
		expression = expression + func;
		if(rand.nextBoolean()) {
			expression = expression + "(x) ";
		}
		else {
			expression = expression + "(y) ";
		}
		
		//Return answer
		return expression;
	}

}
