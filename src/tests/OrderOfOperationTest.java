package tests;

import org.junit.jupiter.api.Test;

import picasso.parser.tokens.operations.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrderOfOperationTest {

    @Test
    public void testOrderOfOperation() {
        ExponentToken exponent = new ExponentToken();
        MultiplicationToken multiply = new MultiplicationToken();
        AdditionToken addition = new AdditionToken();

        // Verify order of operations
        assertTrue(exponent.getOrderValue() > multiply.getOrderValue(),
                "Exponent should have higher precedence than multiplication");
        assertTrue(multiply.getOrderValue() > addition.getOrderValue(),
                "Multiplication should have higher precedence than addition");


        // Verify actual values
        assertEquals(3, exponent.getOrderValue(), "Exponent should have an order value of 3");
        assertEquals(2, multiply.getOrderValue(), "Multiplication should have an order value of 2");
        assertEquals(1, addition.getOrderValue(), "Addition should have an order value of 1");
    }
}
