import calculator.Calculator;
import calculator.CalculatorImpl;
import subsequence.Subsequence;
import subsequence.SubsequenceImpl;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Subsequence s = new SubsequenceImpl();
        boolean b = s.find(Arrays.asList("A", "B", "C", "D"),
                Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D"));
        System.out.println(b); // Result: true

    }
}
