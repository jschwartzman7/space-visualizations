package spacevisuals.functions.functionhandling;

import java.util.function.Function;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class FunctionBuilder {

    public static FunctionBinaryOperationEnum[] binaryOperations =  FunctionBinaryOperationEnum.values();
    public static FunctionUnaryOperationEnum[] unaryOperations = FunctionUnaryOperationEnum.values();
    public static FunctionConstantEnum[] constants = FunctionConstantEnum.values();
    public static FunctionVariableEnum[] variables = FunctionVariableEnum.values();
    
    // tracks order of function variables
    public static ArrayList<String> usedVariables = new ArrayList<String>();
    public static void fillUsedVariables(String[] functionInput){
        ArrayList<FunctionVariableEnum> variables = new ArrayList<FunctionVariableEnum>();
        for(String singleFunction : functionInput){
            String[] singleFunctionStringArray = tokenize(singleFunction);
            if(singleFunctionStringArray == null){
                return;
            }
            for(String token: singleFunctionStringArray){
                for(FunctionVariableEnum var: FunctionVariableEnum.values()){
                    if(var.toString().equals(token) && !variables.contains(var)){
                        variables.add(var);
                    }
                }
            }
        }
        variables.sort(Comparator.comparing(FunctionVariableEnum::getPrecedence));
        for(FunctionVariableEnum variable: variables){
            System.out.println("HHH"+variable);
            usedVariables.add(variable.toString());
        }
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
    public static Function<double[], double[]> parseFunction(String[] functionInput){
        fillUsedVariables(functionInput);
        Function<double[], double[]> parsedFunction = (double[] input) -> {
            double[] output = new double[functionInput.length];
            for(int i = 0; i < functionInput.length; ++i){
                output[i] = parseSingleFunction(tokenize(functionInput[i])).apply(input);
                System.out.println("parsed a single function");
            }
            return output;
        };
        return parsedFunction;
    }

    /*
     * Performs modified Dijkstra's Two Stack Algorithm to parse a single valued, multivariable function string
     */
    public static Function<double[], Double> parseSingleFunction(String[] tokenizedFunction){
        if(tokenizedFunction == null){
            System.out.println("Invalid function input - parseSingleFunction");
            return null;
        }
        Stack<FunctionBinaryOperationEnum> operations = new Stack<FunctionBinaryOperationEnum>();
        Stack<Function<double[], Double>> values = new Stack<Function<double[], Double>>();
        int index = 0;
        while(index < tokenizedFunction.length){
            String curToken = tokenizedFunction[index];
            //System.out.println("token: "+curToken);

            // current is a number
            
            if(isNumeric(curToken)){
                Function<double[], Double> value = (double[] input) -> Double.parseDouble(curToken);
                values.push(value);
                index++;
                continue;
            }
            // check if current is a binary operation
            for(FunctionBinaryOperationEnum binaryOperation: binaryOperations){
                if(binaryOperation.getSymbol().equals(curToken)){
                    while(!operations.isEmpty()){
                        FunctionBinaryOperationEnum previousOperation = operations.peek();
                        if(previousOperation.getPrecedence() >= binaryOperation.getPrecedence()){
                            popAndApply(operations, values);
                        }
                        else{
                            break;
                        }
                    }
                    operations.push(binaryOperation);
                    index++;
                    break;
                }
            }
            
            // check if current is a function variable
            for(FunctionVariableEnum variable: variables){
                if(variable.toString().equals(curToken)){
                    values.push((double[] input) -> input[usedVariables.indexOf(curToken)]);
                    index++;
                    break;
                }
            }
            
            // check if current is a constant
            for(FunctionConstantEnum constant: constants){
                if(constant.toString().equals(curToken)){
                    values.push((double[] input) -> constant.getValue());
                    index++;
                    continue;
                }
            }
            
            // check if current is a unary operation
            /*FunctionUnaryOperationEnum unaryOperation;
            try{
                unaryOperation = FunctionUnaryOperationEnum.valueOf(current);
            }
            catch(IllegalArgumentException e){
                unaryOperation = null;
            }
            finally{
                if(unaryOperation != null){
                    operations.push(current);
                    //Function<double[], Double> value = (double[] input) -> unaryOperations.get(current).apply((input));
                }
            }*/
            if((curToken).equals("(")){
                ArrayList<String> nextFunction = new ArrayList<String>();
                for(int i = index + 1; i < tokenizedFunction.length; ++i){
                    nextFunction.add(tokenizedFunction[i]);
                }
                values.push(parseSingleFunction(nextFunction.toArray(new String[nextFunction.size()])));
                index += toEndingParenthesis(tokenizedFunction, index + 1);
                continue;
            }
            if((curToken).equals(")")){
                System.out.println("ending");
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
        int parenthesesStaus = 1;
        int length = 0;
        for(int i = indexAfterOpening; i < functionString.length; ++i){
            String value = functionString[i];
            switch (value) {
                case "(":
                    parenthesesStaus++;
                    break;
                case ")":
                    parenthesesStaus--;
                    break;
                default:
                    break;
            }
            length++;
            if(parenthesesStaus == 0){
                break;
            }
        }
        return length;
    }
    
    public static void popAndApply(Stack<FunctionBinaryOperationEnum> operations, Stack<Function<double[], Double>> values){
        FunctionBinaryOperationEnum operation = operations.pop();
        Function<double[], Double> recentValue = values.pop();
        Function<double[], Double> previousValue = values.pop();
        values.push((double[] input) -> operation.getFunction().apply(previousValue.apply(input), recentValue.apply(input)));
    }

    public static String[] tokenize(String functionString){
        String[] functionStringArray = functionString.split("");
        ArrayList<String> tokenizedFunction = new ArrayList<String>();
        int index = 0;
        while(index < functionStringArray.length){
            String current = getVariable(functionStringArray, index);
            if(current == ""){
                System.out.println("Invalid function input - tokenize");
                return null;
            }
            tokenizedFunction.add(current);
            index += current.length();
        }
        return tokenizedFunction.toArray(new String[tokenizedFunction.size()]);
    }

    public static String getVariable(String[] functionStringArray, int index){
        String variableReturn = "";

        // x, y, z, w, t, u, v
        for(FunctionVariableEnum variable: FunctionVariableEnum.values()){
            if(variable.toString().equals(functionStringArray[index])){
                return functionStringArray[index];
            }
        }
        // +, -, *, /, ^
        for(FunctionBinaryOperationEnum binaryOperation: FunctionBinaryOperationEnum.values()){
            if(binaryOperation.getSymbol().equals(functionStringArray[index])){
                return functionStringArray[index];
            }
        }
        // e, pi
        for(FunctionConstantEnum constant: FunctionConstantEnum.values()){
            if(constant.toString().startsWith(functionStringArray[index])){
                return constant.toString();
            }
        }
        // sin, cos, exp
        for(FunctionUnaryOperationEnum unaryOperation: FunctionUnaryOperationEnum.values()){
            if(unaryOperation.toString().startsWith(functionStringArray[index])){
                return unaryOperation.toString();
            }
        }
        // parenthesis
        if(functionStringArray[index].equals("(") || functionStringArray[index].equals(")")){
            return functionStringArray[index];
        }
        // 0123456789
        while(functionStringArray[index].matches("[0-9.]")){
            variableReturn += functionStringArray[index];
            if(index + 1 < functionStringArray.length){
                index++;
            }
            else{
                break;
            }
        }
        return variableReturn;
    }

    public static void main(String[] args) {
        String[] function = {"2*x+y/3", "y/2"};
        Function<double[], double[]> parsedFunction = parseFunction(function);
        System.out.println(parsedFunction.apply(new double[]{5, 6})[1]);
    }
}
