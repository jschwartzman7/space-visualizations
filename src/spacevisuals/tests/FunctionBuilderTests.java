package spacevisuals.tests;

import java.util.HashMap;
import java.util.function.Function;
import spacevisuals.utils.FunctionBuilder;


public class FunctionBuilderTests {

    public static HashMap<String, Function<double[], double[]>> singleFunctions = new HashMap<String, Function<double[], double[]>>()
        {{
            put("", null);
            put(" ", null);
            put("  ", null);
            put("j", null);
            put(" xx -  3", null);
            put("x -  3", null);
            put("-k x*3", null);
            put("x/ 3.0=0", null);
            put("x ^*3", null);
            put("x", input -> new double[]{input[0]});
            put("x+3", input -> new double[]{input[0] + 3});
            put("x-3", input -> new double[]{input[0] - 3});
            put("x*3", input -> new double[]{input[0] * 3});
            put("x/3", input -> new double[]{input[0] / 3});
            put("x^3", input -> new double[]{Math.pow(input[0], 3)});
            put(" x -  3", input -> new double[]{input[0] - 3});
            put("- x*3", input -> new double[]{-input[0] * 3});
            put("x/ 3.000", input -> new double[]{input[0] / 3});
            put("x ^ 3", input -> new double[]{Math.pow(input[0], 3)});
            put("sin(x)", input -> new double[]{Math.sin(input[0])});
            put("cos(x)", input -> new double[]{Math.cos(input[0])});
        
        }};
    public static double[][] inputs = new double[][]{
        {0},
        {0, 0},
        {0, 0, 0},
        {1},
        {1, 1},
        {0, 1, 12},
        {3.14, 2.718, -1},
        {-0.43, -0.08, 999},
        {Math.PI, Math.PI/2, 0, Math.PI*2},
    };
    public static boolean testParseFunction(){
        for(String function : singleFunctions.keySet()){
            System.out.println();
            System.out.println();
            try {
                System.out.println("Testing function " + function);
                Function<double[], double[]> parsedFunction = FunctionBuilder.parseFunction(new String[]{function});
                if(parsedFunction == null){
                    if(singleFunctions.get(function) == null){
                        System.out.println("Parsed function is correctly null");
                    }
                    else{
                        System.out.println("Error: Parsed function is null and shouldn't be");
                    }
                }
                else{
                    if(singleFunctions.get(function) == null){
                        System.out.println("Error: Parsed function is not null and should be null");
                    }
                    else{
                        System.out.println("Parsed function is correctly not null");
                        for(double[] input: inputs){
                            double[] labelResult = singleFunctions.get(function).apply(input);
                            double[] result = parsedFunction.apply(input);
                            for(int idx = 0; idx < result.length; ++idx){
                                //System.out.println("f(" + input[idx] + ") should equal " + result[idx]);
                                assert result[idx] == labelResult[idx];
                            }
                        }
                    }
                    
                }
                
            } catch (Exception e) {
                System.out.println("Caught exception: Failed to parse " + function);
                e.printStackTrace();
                continue;
            }
        }
        return true;

    }

    public static boolean testTokenize(){
        for(String function : singleFunctions.keySet()){
            System.out.println();
            try {
                System.out.println("Tokenizing function " + function);
                String[] tokenized = FunctionBuilder.tokenize(function);
                if(tokenized == null){
                    System.out.println("Tokenized is null");
                    
                }
                else{
                    System.out.println("Tokenized is not null");
                    for(String token: tokenized){
                        System.out.println("Token: " + token);
                    }
                }
                
            } catch (Exception e) {
                System.out.println("Caught exception: Failed to tokenize " + function);
                e.printStackTrace();
                continue;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println("Testing FunctionBuilder...");
        if(!testTokenize()){
            System.out.println("Failed to tokenize function");
        }
        /*if(!testParseFunction()){
            System.out.println("Failed to parse function");
        }*/
        System.out.println("Finished testing FunctionBuilder");
    }
}
