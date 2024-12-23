/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

/**
 * Tests of the evaluation of expression trees
 * 
 * @author Sara Sprenkle
 * 
 */
public class EvaluatorTests {

	private static final double DELTA = 0.05;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testConstantEvaluation() {
		ExpressionTreeNode e = new RGBColor(1, -1, 1);
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(1, -1, 1), e.evaluate(i, i));
		}
	}

	@Test
	public void testXEvaluation() {
		X x = new X();
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), x.evaluate(i, i));
		}
	}
	
	@Test
	public void testFloorEvaluation() {
		Floor myTree = new Floor(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double floorOfTestVal = Math.floor(testVal);
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	@Test 
	public void testWrapEvaluation() {
		Wrap myTree = new Wrap(new X());
		
		//numerical tests. Remember Y values do not matter in this
		assertEquals(new RGBColor(-0.5, -0.5,-0.5), myTree.evaluate(1.5, -1)); // may not work because 1.5 is outside the bounds of X(). may need to develop binary "+" first
		assertEquals(new RGBColor(0.25, 0.25, 0.25) , myTree.evaluate(-1.75, 0.5));
		assertEquals(new RGBColor(0,0,0), myTree.evaluate(0, 0)); // no change when inside the bounds of [-1,1]
		
		//edge cases
		for (int i = -1; i < 1; i++) { //test x= -1, 0, 1
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, 0));
		}
		
	}

	@Test
    public void testExpEvaluation() {
        Exp expTree = new Exp(new X());

        // some straightforward tests
        assertEquals(new RGBColor(Math.exp(0), Math.exp(0), Math.exp(0)), expTree.evaluate(0, 0));
        assertEquals(new RGBColor(Math.exp(1), Math.exp(1), Math.exp(1)), expTree.evaluate(1, 1));
        assertEquals(new RGBColor(Math.exp(-1), Math.exp(-1), Math.exp(-1)), expTree.evaluate(-1, -1));

        // test some specific values
        double[] tests = { 0, 1, -1, 0.5, -0.5 };

        for (double testVal : tests) {
            double expVal = Math.exp(testVal);
            assertEquals(new RGBColor(expVal, expVal, expVal), expTree.evaluate(testVal, testVal));
        }
    }
	
	@Test
    public void testLogEvaluation() {
        Log logTree = new Log(new X());

        // some straightforward tests
        //Log(0) is an undefined number in mathematics.
        //assertEquals(new RGBColor(Math.log(0), Math.log(0), Math.log(0)), logTree.evaluate(0, 0));
        assertEquals(new RGBColor(Math.log(1), Math.log(1), Math.log(1)), logTree.evaluate(1, 1));
        //This is also not a real number.
        //assertEquals(new RGBColor(Math.log(-1), Math.log(-1), Math.log(-1)), logTree.evaluate(-1, -1));

        // test some specific values
        double[] tests = {1.0, 0.5, 0.1};

        for (double testVal : tests) {
            RGBColor actual = logTree.evaluate(testVal, testVal);
            double logVal = Math.log(testVal);

            assertEquals(logVal, actual.getRed(), DELTA);
            assertEquals(logVal, actual.getGreen(), DELTA);
            assertEquals(logVal, actual.getBlue(), DELTA);
        }


    }

	  public void testSinEvaluation() {
		Sin myTree = new Sin(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate((Math.PI/2), 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(Math.PI, Math.PI/2));
		assertNotEquals(new RGBColor(0, 0, 0), myTree.evaluate(Math.PI/2, Math.PI/2));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(Math.sin(i), Math.sin(i), Math.sin(i)), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(Math.sin(i), Math.sin(i), Math.sin(i)), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5, Math.PI/4 };

		for (double testVal : tests) {
			double sinOfTestVal = Math.sin(testVal);
			assertEquals(new RGBColor(sinOfTestVal, sinOfTestVal, sinOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(sinOfTestVal, sinOfTestVal, sinOfTestVal), myTree.evaluate(testVal, testVal));
		}
	}
	  public void testCosEvaluation() {
		  Cos cosTree = new Cos(new X());
		  
		  //some straightforward tests
		  assertEquals(new RGBColor(1, 1, 1), cosTree.evaluate((Math.PI/2), 1));
		  assertEquals(new RGBColor(0, 0, 0), cosTree.evaluate(Math.PI, Math.PI/2));
		  assertNotEquals(new RGBColor(0, 0, 0), cosTree.evaluate(Math.PI/2, Math.PI/2));
		  
		  // test the ints; remember that y's value doesn't matter
		  for (int i = -1; i <= 1; i++) {
			  assertEquals(new RGBColor(i, i, i), cosTree.evaluate(i, -i));
			  assertEquals(new RGBColor(i, i, i), cosTree.evaluate(i, i));
		  }
		  
		  double[] tests = {-.7, -.00001, .000001, .5, Math.PI/4};

		  for (double testVal : tests) {
			  double cosOfTestVal = Math.cos(testVal);
			  assertEquals(new RGBColor(cosOfTestVal, cosOfTestVal, cosOfTestVal), cosTree.evaluate(testVal, -1));
			  assertEquals(new RGBColor(cosOfTestVal, cosOfTestVal, cosOfTestVal), cosTree.evaluate(testVal, testVal));
		  }
	  }
	  


	// TODO: More tests of evaluation

}
