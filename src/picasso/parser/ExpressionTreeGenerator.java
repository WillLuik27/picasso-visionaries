package picasso.parser;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.*;
import picasso.parser.tokens.chars.*;
import picasso.parser.tokens.functions.*;
import picasso.parser.tokens.operations.*;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions)
 * @author Sara Sprenkle modified for Picasso
 */
public class ExpressionTreeGenerator {

	// TODO: Do these belong here?
	private static final int CONSTANT = 0;

	

	/**
	 * Converts the given string into expression tree for easier manipulation.
	 * 
	 * @param infix - a non-empty expression to parse.
	 * 
	 * @return ExpressionTreeNode representing the root node of the given infix
	 *         formula
	 */
	public ExpressionTreeNode makeExpression(String infix) {
		Stack<Token> postfix = infixToPostfix(infix);

		System.out.println("Postfix: " + postfix);
		if (postfix.isEmpty()) {
			return null;
		}
		
		//Added check to ensure that - sign isn't being incorrectly used:
		if (infix.startsWith("- ") || infix.startsWith("-.")) {
	        throw new ParseException("Incorrect usage of - sign");
	    }

		
		//System.out.println("Process postfix expression");
		SemanticAnalyzer semAnalyzer = SemanticAnalyzer.getInstance();

		ExpressionTreeNode root = semAnalyzer.generateExpressionTree(postfix);

		// Is this the best place to put this check?
		if (!postfix.isEmpty()) {
			throw new ParseException("Extra operands without operators or functions");
		}
		
		System.out.println("FINAL: " + root);
		return root;
	}

	/**
	 * This method converts the String infix expression to a Stack of tokens, which
	 * are in postfix.
	 * 
	 * @param infix the String to parse, as we would typically write it
	 * @return a stack of tokens, in postfix order
	 */
	public Stack<Token> infixToPostfix(String infix) {

		Tokenizer tokenizer = new Tokenizer();
		List<Token> tokens = tokenizer.parseTokens(infix);
		System.out.println(tokens);
		

		return infixToPostfix(tokens);
	}

	/**
	 * This method converts the List of tokens (in infix order) to a Stack of
	 * tokens, which are in postfix.
	 * 
	 * @param tokens the Tokens, in infix order
	 * @return a stack of tokens, in postfix order
	 */
	private Stack<Token> infixToPostfix(List<Token> tokens) {
		// Algorithm for converting infix to postfix was adapted from
		// http://en.wikipedia.org/wiki/Shunting_yard_algorithm
		// Needed to handle identifiers and colors, which aren't in the original
		// algorithm.
		// May need to modify/update further...

		Stack<Token> operators = new Stack<Token>();
		Stack<Token> postfixResult = new Stack<Token>();

		Iterator<Token> iter = tokens.iterator();

		// TO DISCUSS: Is this the correct way to design this code?
		// What is the code smell? What is the alternative?

		while (iter.hasNext()) {
			Token token = iter.next();
			
			if (token instanceof NumberToken) {
				postfixResult.push(token);
			} else if (token instanceof ColorToken) {
				postfixResult.push(token);
			} else if (token instanceof IdentifierToken) {
				postfixResult.push(token);
			} else if (token instanceof FunctionToken) {
				operators.push(token);
			} else if (token instanceof EqualsToken) {
				operators.push(token);
			}else if (token instanceof OperationInterface) {

				/*
				 * while there is an operator, o2, at the top of the stack (this excludes left
				 * parenthesis), and either
				 * 
				 * o1 is left-associative and its precedence is less than (lower precedence) or
				 * equal to that of o2, or o1 is right-associative and its precedence is less
				 * than (lower precedence) that of o2 (SS: second case is not reflected in below
				 * code),
				 * 
				 * pop o2 off the stack, onto the output queue;
				 */
				while (!operators.isEmpty() && !(operators.peek() instanceof LeftParenToken)
						&& orderOfOperation(token) <= orderOfOperation(operators.peek())) {
					postfixResult.push(operators.pop());
				}

				operators.push(token);

			} else if (token instanceof PathToken) {
			    //Want just the path token and not "Quotes
			    operators.push(token);
			    
			} else if (token instanceof CommaToken) {
				// Until the token at the top of the stack is a left
				// parenthesis, pop operators off the stack onto the output
				// queue.

				while (!operators.isEmpty() && !(operators.peek() instanceof LeftParenToken)) {
					postfixResult.push(operators.pop());
				}

				// If no left parentheses are encountered, either the
				// separator was misplaced or parentheses were mismatched.
				if (operators.isEmpty() || !(operators.peek() instanceof LeftParenToken)) {
					throw new ParseException("Parentheses were mismatched.");
				}

			} else if (token instanceof LeftParenToken) {
				operators.push(token);
			} else if (token instanceof RightParenToken) {
				// Until the token at the top of the stack is a left
				// parenthesis, pop operators off the stack onto the output
				// queue.
				while (operators.size() > 0 && !(operators.peek() instanceof LeftParenToken)) {
					postfixResult.push(operators.pop());
				}

				// Pop the left parenthesis from the stack, but not onto the
				// output queue.
				if (operators.isEmpty()) {
					throw new ParseException("Missing (");
				}
				operators.pop();

				// If the token at the top of the stack is a function token, pop
				// it onto the output queue.
				if (operators.size() > 0 && operators.peek() instanceof FunctionToken) {
					postfixResult.push(operators.pop());
				}
				

			
			} else if (token instanceof QuoteToken) {
			    // Error if getting a Quote Token
			    throw new ParseException("Unexpected QuoteToken");
			}
		
		
			
			
			else {
				System.out.println("ERROR: No match: " + token);
			}
		}
		while (!operators.isEmpty()) {

			// If the operator token on the top of the stack is a parenthesis,
			// then there are mismatched parentheses.

			Token top = operators.peek();

			if (top.equals(CharTokenFactory.getToken('(')) || top.equals(CharTokenFactory.getToken(')'))) {
				throw new ParseException("Mismatched Parentheses");
			}
			postfixResult.push(operators.pop());
		}

		return postfixResult;

	}

	/**
	 * 
	 * @param token
	 * @return
	 * @author josephholden
	 */
	private int orderOfOperation(Token token) {
	    if (token instanceof OperationInterface) {
	        return ((OperationInterface) token).getOrderValue();
	    }
	    return CONSTANT; // Default for tokens that don't have an order value
	}

}
