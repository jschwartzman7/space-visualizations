package spacevisuals.utils;

import java.util.function.Function;
import java.util.stream.Collectors;

import spacevisuals.enums.BinaryOperationEnum;
import spacevisuals.enums.VariableEnum;
import spacevisuals.enums.MathConstantEnum;
import spacevisuals.enums.UnaryOperationEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;
/*
 * Class for parsing a user input multivariable function string into a Function<double[], double[]>
 * Operations and function variable names are defined in enums
 * parseFunction can return null if the input is invalid
 */
public class FunctionBuilder {

    // +, -, *, /, ^
    public static HashSet<String> binaryOperations = Arrays.stream(BinaryOperationEnum.values())
                                                            .map(op -> op.symbol)
                                                            .collect(Collectors.toCollection(HashSet::new));
    // sin, cos                                             
    public static HashSet<String> unaryOperations = Arrays.stream(UnaryOperationEnum.values())
                                                            .map(UnaryOperationEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));
    // e, pi
    public static HashSet<String> constants = Arrays.stream(MathConstantEnum.values())
                                                            .map(MathConstantEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));
    // x, y, z, w, t, u, v
    public static HashSet<String> variables = Arrays.stream(VariableEnum.values())
                                                            .map(VariableEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));

    /**
     * 
     * @param str -1.2345
     * @return if str is a number
     */
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * returns key from the above sets that match the current string index
     */
    public static String getVariable(String[] functionStringArray, int index){
        if(functionStringArray[index] == ""){
            System.out.println("Empty string in function string");
        }
        // x, y, z, w, t, u, v
        // one char length
        if(variables.contains(functionStringArray[index])){
            return functionStringArray[index];
        }
        // +, -, *, /, ^
        // one char length
        if(binaryOperations.contains(functionStringArray[index])){
            return functionStringArray[index];
        }
        // ( )
        // one char length
        if(functionStringArray[index].equals("(") || functionStringArray[index].equals(")")){
            return functionStringArray[index];
        }
        // e, pi
        for(String constant: constants){
            if(constant.startsWith(functionStringArray[index])){
                return constant;
            }
        }
        // sin, cos
        for(String unaryOperation: unaryOperations){
            if(unaryOperation.startsWith(functionStringArray[index])){
                return unaryOperation;
            }
        }
        // 1.023456789 or decimal point
        StringBuilder variable = new StringBuilder();
        while(functionStringArray[index].matches("[0-9]") || functionStringArray[index].equals(".")){
            variable.append(functionStringArray[index++]);
            if(index >= functionStringArray.length){
                break;
            }
        }
        try {
            if(isNumeric(variable.toString())){
                return variable.toString();
            }
        }
        catch (NumberFormatException e) {
        }
        return null;
    }

    public static String[] tokenize(String functionString){
        if(functionString == null || functionString.isEmpty() || functionString.isBlank()){
            return null;
        }
        String[] functionSplit = functionString.split(" ");
        ArrayList<String> tokenizedFunction = new ArrayList<String>();
        for(String splitFunction : functionSplit){
            if(splitFunction == ""){
                continue;
            }
            int index = 0;
            String[] splitFunctionArray = splitFunction.split("");
            while(index < splitFunctionArray.length){
                String token = getVariable(splitFunctionArray, index);
                if(token == null){
                    return null;
                }
                tokenizedFunction.add(token);
                index += token.length();
            }
        }
        return tokenizedFunction.toArray(new String[tokenizedFunction.size()]);
    }

    /**
     * @param functionInput ["5*x" "-y", "2+cos(x)", "x*y*z"]
     * @return VariableEnum ordered list of variables used throughout the function
     */
    public static ArrayList<VariableEnum> getFunctionVariables(String[] functionInput){
        ArrayList<VariableEnum> functionVariables = new ArrayList<VariableEnum>();
        for(String singleFunction : functionInput){
            if(singleFunction == null || singleFunction.length() == 0){
                continue;
            }
            String[] singleFunctionStringArray = FunctionBuilder.tokenize(singleFunction);
            if(singleFunctionStringArray == null || singleFunctionStringArray.length == 0){
                continue;
            }
            for(String token: singleFunctionStringArray){
                VariableEnum variable = VariableEnum.from(token);
                if(variable != null){
                    if(!functionVariables.contains(variable)){
                        functionVariables.add(variable);
                    }
                }
            }
        }
        functionVariables.sort(Comparator.comparing(x->x.precedence));
        return functionVariables;
    }

    public static String[] toEndingParenthesis(String[] functionString, int indexAfterOpening){
        ArrayList<String> parenthesizedFunction = new ArrayList<String>();
        int parenthesesStatus = 1;
        int i = indexAfterOpening;
        while(i < functionString.length && parenthesesStatus > 0){
            switch (functionString[i]) {
                case "(":
                    parenthesesStatus++;
                    parenthesizedFunction.add("(");
                    break;
                case ")":
                    parenthesesStatus--;
                    if(parenthesesStatus > 0){
                        parenthesizedFunction.add(")");
                    }
                    break;
                default:
                    parenthesizedFunction.add(functionString[i]);
                    break;
            }
            i++;
        }
        return parenthesizedFunction.toArray(new String[parenthesizedFunction.size()]);
    }
    
    public static void popAndApply(Stack<BinaryOperationEnum> operations, Stack<Function<double[], Double>> values){
        if(operations.isEmpty()){
            return;
        }
        BinaryOperationEnum operation = operations.pop();
        if(values.isEmpty()){
            return;
        }
        Function<double[], Double> recentValue = values.pop();
        if(values.isEmpty()){
            if(operation == BinaryOperationEnum.subtract){// handle negative number
                values.push((double[] input) -> -recentValue.apply(input));
            }
            return;
        }
        Function<double[], Double> previousValue = values.pop();
        values.push((double[] input) -> operation.function.apply(previousValue.apply(input), recentValue.apply(input)));
    }

    /*
     * Performs modified Dijkstra's Two Stack Algorithm to parse a single valued, multivariable function string
     */
    public static Function<double[], Double> parseSingleFunctionRecursive(String[] tokenizedFunction, ArrayList<VariableEnum> usedVariables){
        if(tokenizedFunction == null){
            System.out.println("Invalid function input - parseSingleFunction");
            return null;
        }
        Stack<BinaryOperationEnum> operations = new Stack<BinaryOperationEnum>();
        Stack<Function<double[], Double>> values = new Stack<Function<double[], Double>>();
        int tokenIdx = 0;
        while(tokenIdx < tokenizedFunction.length){
            String curToken = tokenizedFunction[tokenIdx];
            // current is a number
            if(isNumeric(curToken)){
                Function<double[], Double> value = (double[] input) -> Double.parseDouble(curToken);
                values.push(value);
                tokenIdx++;
                continue;
            }
            // current is a binary operation
            if(binaryOperations.contains(curToken)){
                while(!operations.isEmpty()){
                    BinaryOperationEnum previousOperation = operations.peek();
                    if(previousOperation.precedence >= BinaryOperationEnum.from(curToken).precedence){
                        popAndApply(operations, values);
                    }
                    else{
                        break;
                    }
                }
                operations.push(BinaryOperationEnum.from(curToken));
                tokenIdx++;
                continue;
            }
            // current is a function variable
            if(variables.contains(curToken)){
                values.push((double[] input) -> input[usedVariables.indexOf(VariableEnum.valueOf(curToken))]);
                tokenIdx++;
                continue;
            }
            // check if current is a constant
            if(constants.contains(curToken)){
                values.push((double[] input) -> MathConstantEnum.valueOf(curToken).value);
                tokenIdx++;
                continue;
            }
            // current is an unary operation
            if(unaryOperations.contains(curToken)){
                String[] nextFunction = toEndingParenthesis(tokenizedFunction, tokenIdx + 2);
                values.push(UnaryOperationEnum.valueOf(curToken).function.apply(parseSingleFunctionRecursive(nextFunction, usedVariables)));
                tokenIdx += nextFunction.length + 3;
                continue;
            }
            if(curToken.equals("(")){
                String[] nextFunction = toEndingParenthesis(tokenizedFunction, tokenIdx + 1);
                values.push(parseSingleFunctionRecursive(nextFunction, usedVariables));
                tokenIdx += nextFunction.length + 2;
                continue;
            }
        }
        while(!operations.isEmpty()){
            popAndApply(operations, values);
        }
        if(!values.isEmpty()){
            return values.pop();
        }
        return null;
    }

    /*
     * Parses a multivalued, multivariable function string into a Function<double[], double[]>
     * ex: String[]"5*x", "(79*(cos(x)+1))/3"} -> Function<double[], double[]> = f(x, y) = <5x, (79(cos(x)+1))/3>
     */
    public static Function<double[], double[]> parseFunction(String[] functionInput){
        ArrayList<VariableEnum> usedVariables = getFunctionVariables(functionInput);
        Function<double[], double[]> parsedFunction = (double[] input) -> {
            double[] output = new double[functionInput.length];
            for(int i = 0; i < functionInput.length; ++i){
                if(functionInput[i] == null || functionInput[i].length() == 0){
                    continue;
                }
                output[i] = parseSingleFunctionRecursive(tokenize(functionInput[i]), usedVariables).apply(input);
            }
            return output;
        };
        return parsedFunction;
    }
}
