package spacevisuals.functionhandling;

import java.util.function.Function;
import java.util.stream.Collectors;

import spacevisuals.enums.BinaryOperationEnum;
import spacevisuals.enums.FunctionVariableEnum;
import spacevisuals.enums.MathConstantEnum;
import spacevisuals.enums.UnaryOperationEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;

public class FunctionBuilder {

    // +, -, *, /, ^
    public static HashSet<String> binaryOperations = Arrays.stream(BinaryOperationEnum.values())
                                                            .map(op -> op.symbol)
                                                            .collect(Collectors.toCollection(HashSet::new));
    // sin, cos, exp                                             
    public static HashSet<String> unaryOperations = Arrays.stream(UnaryOperationEnum.values())
                                                            .map(UnaryOperationEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));
    // e, pi
    public static HashSet<String> constants = Arrays.stream(MathConstantEnum.values())
                                                            .map(MathConstantEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));
    // x, y, z, w, t, u, v
    public static HashSet<String> variables = Arrays.stream(FunctionVariableEnum.values())
                                                            .map(FunctionVariableEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));

    public ArrayList<FunctionVariableEnum> usedVariables = new ArrayList<FunctionVariableEnum>();
    
    public void fillUsedVariables(String[] functionInput){
        usedVariables.clear();
        for(String singleFunction : functionInput){
            String[] singleFunctionStringArray = tokenize(singleFunction);
            if(singleFunctionStringArray == null){
                return;
            }
            for(String token: singleFunctionStringArray){
                for(FunctionVariableEnum var: FunctionVariableEnum.values()){
                    if(var.toString().equals(token) && !usedVariables.contains(var)){
                        usedVariables.add(var);
                    }
                }
            }
        }
        usedVariables.sort(Comparator.comparing(x->x.precedence));
    }

    public static boolean isNumeric(String str) {
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
     * Parses a multivalued, multivariable function string into a Function<double[], double[]>
     * ex: String[]"5*x", "(79*(cos(x)+1))/3"} -> Function<double[], double[]> = f(x, y) = <5x, (79(cos(x)+1))/3>
     */
    public Function<double[], double[]> parseFunction(String[] functionInput){
        fillUsedVariables(functionInput);
        Function<double[], double[]> parsedFunction = (double[] input) -> {
            double[] output = new double[functionInput.length];
            for(int i = 0; i < functionInput.length; ++i){
                output[i] = parseSingleFunctionRecursive(tokenize(functionInput[i])).apply(input);
            }
            return output;
        };
        return parsedFunction;
    }

    /*
     * Performs modified Dijkstra's Two Stack Algorithm to parse a single valued, multivariable function string
     */
    public Function<double[], Double> parseSingleFunctionRecursive(String[] tokenizedFunction){
        if(tokenizedFunction == null){
            System.out.println("Invalid function input - parseSingleFunction");
            return null;
        }
        Stack<BinaryOperationEnum> operations = new Stack<BinaryOperationEnum>();
        Stack<Function<double[], Double>> values = new Stack<Function<double[], Double>>();
        int index = 0;
        while(index < tokenizedFunction.length){
            String curToken = tokenizedFunction[index];

            // current is a number
            if(isNumeric(curToken)){
                Function<double[], Double> value = (double[] input) -> Double.parseDouble(curToken);
                values.push(value);
                index++;
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
                index++;
                continue;
            }
            
            // current is a function variable
            if(variables.contains(curToken)){
                values.push((double[] input) -> input[usedVariables.indexOf(FunctionVariableEnum.valueOf(curToken))]);
                index++;
                continue;
            }
            
            // check if current is a constant
            if(constants.contains(curToken)){
                values.push((double[] input) -> MathConstantEnum.valueOf(curToken).value);
                index++;
                continue;
            }

            // current is an unary operation
            if(unaryOperations.contains(curToken)){
                ArrayList<String> nextFunction = new ArrayList<String>();
                for(int i = index + 1; i < tokenizedFunction.length; ++i){
                    nextFunction.add(tokenizedFunction[i]);
                }
                Function<Function<double[], Double>, Function<double[], Double>> unaryOperation = UnaryOperationEnum.valueOf(curToken).function;
                values.push(unaryOperation.apply(parseSingleFunctionRecursive(nextFunction.toArray(new String[nextFunction.size()]))));
                index += toEndingParenthesis(tokenizedFunction, index + 1);
                continue;
            }
            
            if((curToken).equals("(")){
                ArrayList<String> nextFunction = new ArrayList<String>();
                for(int i = index + 1; i < tokenizedFunction.length; ++i){
                    nextFunction.add(tokenizedFunction[i]);
                }
                values.push(parseSingleFunctionRecursive(nextFunction.toArray(new String[nextFunction.size()])));
                index += toEndingParenthesis(tokenizedFunction, index + 1);
                continue;
            }
            if((curToken).equals(")")){
                while(!operations.isEmpty()){
                    popAndApply(operations, values);
                }
                return values.pop();
            }
        }
        while(!operations.isEmpty()){
            popAndApply(operations, values);
        }
        return values.pop();
    }

    public static int toEndingParenthesis(String[] functionString, int indexAfterOpening){
        int parenthesesStatus = 1;
        int length = 1;
        for(int i = indexAfterOpening; i < functionString.length; ++i){
            String value = functionString[i];
            switch (value) {
                case "(":
                    parenthesesStatus++;
                    break;
                case ")":
                    parenthesesStatus--;
                    break;
                default:
                    break;
            }
            length++;
            if(parenthesesStatus == 0){
                break;
            }
        }
        return length;
    }
    
    public static void popAndApply(Stack<BinaryOperationEnum> operations, Stack<Function<double[], Double>> values){
        BinaryOperationEnum operation = operations.pop();
        Function<double[], Double> recentValue = values.pop();
        Function<double[], Double> previousValue = values.pop();
        values.push((double[] input) -> operation.function.apply(previousValue.apply(input), recentValue.apply(input)));
    }

    public static String[] tokenize(String functionString){
        String[] functionStringArray = functionString.split("");
        ArrayList<String> tokenizedFunction = new ArrayList<String>();
        int index = 0;
        while(index < functionStringArray.length){
            String current = getVariable(functionStringArray, index);
            if(current == null){
                System.out.println("Invalid function input - tokenize");
                return null;
            }
            tokenizedFunction.add(current);
            index += current.length();
        }
        return tokenizedFunction.toArray(new String[tokenizedFunction.size()]);
    }

    /*
     * returns key from the above sets that match the current string index
     */
    public static String getVariable(String[] functionStringArray, int index){
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
        // parenthesis
        // one char length
        if(functionStringArray[index].equals("(") || functionStringArray[index].equals(")")){
            return functionStringArray[index];
        }
        // e, pi
        if(constants.contains(functionStringArray[index])){
            return functionStringArray[index];
        }
        // sin, cos
        for(String unaryOperation: unaryOperations){
            if(unaryOperation.toString().startsWith(functionStringArray[index])){
                return unaryOperation.toString();
            }
        }
        // 0123456789 or decimal point
        String variableReturn = "";
        while(isNumeric(functionStringArray[index]) || functionStringArray[index].equals(".")){
            variableReturn += functionStringArray[index];
            index++;
            if(index >= functionStringArray.length){break;}
        }
        if(variableReturn.length() > 0){
            return variableReturn;
        }
        return null;
    }

    public static void main(String[] args) {
        String[] function = {"(1+y)/(x*6)", ""};
        Function<double[], double[]> parsedFunction = new FunctionBuilder().parseFunction(function);
        System.out.println(parsedFunction.apply(new double[]{5, 3})[0]);
    }
}
