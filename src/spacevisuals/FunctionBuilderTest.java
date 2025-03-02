package spacevisuals;

import java.util.Map;
import java.util.function.Function;

class FunctionBuilderTest {

    static void testFunctionParsingAndEvaluation() {
        FunctionBuilder parser = new FunctionBuilder();

        // Define test cases with expression inputs and expected outputs
        testExpression(parser, "5*x+y-2", Map.of("x", 2.0, "y", 3.0), 5 * 2.0 + 3.0 - 2);
        testExpression(parser, "x*x+2*y", Map.of("x", 3.0, "y", 4.0), 3.0 * 3.0 + 2 * 4.0);
        testExpression(parser, "10-x/y", Map.of("x", 8.0, "y", 2.0), 10 - 8.0 / 2.0);
        testExpression(parser, "x+y-3", Map.of("x", 1.0, "y", 5.0), 1.0 + 5.0 - 3);
    }

    private static void testExpression(FunctionBuilder parser, String expression, Map<String, Double> variables, double expected) {
        Function<double[], double[]> function = parser.parseFunction(new String[]{expression});
        double result = function.apply(variables.values().stream().mapToDouble(Double::doubleValue).toArray())[0];
        if(Math.abs(result - expected) < 1e-6) {
            System.out.println("Test passed for expression: " + expression);
        } else {
            System.out.println("Test failed for expression: " + expression + ". Expected: " + expected + ", but got: " + result);
        }
    }

    public static void main(String[] args) {
        testFunctionParsingAndEvaluation();
    }
}
