import calculator.Calculator;
import calculator.CalculatorImpl;
import pyramidBuilder.PyramidBuilder;
import subsequence.Subsequence;
import subsequence.SubsequenceImpl;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Calculator c = new CalculatorImpl();
        System.out.println(c.evaluate("(1+38)*4-5")); // Result: 151
        System.out.println(c.evaluate("7*6/2+8")); // Result: 29
        System.out.println(c.evaluate("-12)1//(")); // Result: null

    }
}
