package spacevisuals.functions.functionhandling;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class FunctionBuilder {

    public static HashSet<String> binaryOperations = Arrays.stream(BinaryOperationEnum.values())
                                                            .map(BinaryOperationEnum::getSymbol)
                                                            .collect(Collectors.toCollection(HashSet::new));
    public static HashMap<String, String> binaryOperationMap = new HashMap<String, String>()
        {{
            put("+", "add");
            put("-", "subtract");
            put("*", "multiply");
            put("/", "divide");
            put("^", "power");
        }}
    ;
    public static HashSet<String> unaryOperations = Arrays.stream(UnaryOperationEnum.values())
                                                            .map(UnaryOperationEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));
    public static HashSet<String> constants = Arrays.stream(MathConstantEnum.values())
                                                            .map(MathConstantEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));
    public static HashSet<String> variables = Arrays.stream(FunctionVariableEnum.values())
                                                            .map(FunctionVariableEnum::toString)
                                                            .collect(Collectors.toCollection(HashSet::new));
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
        Stack<BinaryOperationEnum> operations = new Stack<BinaryOperationEnum>();
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
            // current is a binary operation
            if(binaryOperations.contains(curToken)){
                while(!operations.isEmpty()){
                    BinaryOperationEnum previousOperation = operations.peek();
                    if(previousOperation.precedence >= BinaryOperationEnum.valueOf(binaryOperationMap.get(curToken)).precedence){
                        popAndApply(operations, values);
                    }
                    else{
                        break;
                    }
                }
                operations.push(BinaryOperationEnum.valueOf(binaryOperationMap.get(curToken)));
                index++;
                continue;
            }
            
            // current is a function variable
            if(variables.contains(curToken)){
                values.push((double[] input) -> input[usedVariables.indexOf(curToken)]);
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
                values.push(unaryOperation.apply(parseSingleFunction(nextFunction.toArray(new String[nextFunction.size()]))));
                index += toEndingParenthesis(tokenizedFunction, index + 1);
                continue;
            }
            
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
                System.out.println("ending parenthesis");
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
            if(current == ""){
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
        // returns "" if no enum matches
        String variableReturn = "";

        // x, y, z, w, t, u, v
        // one char length
        for(FunctionVariableEnum variable: FunctionVariableEnum.values()){
            if(variable.toString().equals(functionStringArray[index])){
                return functionStringArray[index];
            }
        }
        // +, -, *, /, ^
        // one char length
        for(BinaryOperationEnum binaryOperation: BinaryOperationEnum.values()){
            if(binaryOperation.symbol.equals(functionStringArray[index])){
                return functionStringArray[index];
            }
        }
        // parenthesis
        // one char length
        if(functionStringArray[index].equals("(") || functionStringArray[index].equals(")")){
            return functionStringArray[index];
        }
        // e, pi
        for(MathConstantEnum constant: MathConstantEnum.values()){
            if(constant.toString().startsWith(functionStringArray[index])){
                return constant.toString();
            }
        }
        // sin, cos, exp
        for(UnaryOperationEnum unaryOperation: UnaryOperationEnum.values()){
            if(unaryOperation.toString().startsWith(functionStringArray[index])){
                return unaryOperation.toString();
            }
        }
        // 0123456789 or decimal point
        while(isNumeric(functionStringArray[index]) || functionStringArray[index].equals(".")){
            variableReturn += functionStringArray[index];
            index++;
            if(index >= functionStringArray.length){break;}
        }
        return variableReturn;
    }

    public static void main(String[] args) {
        String[] function = {"cos(pi/4)", "y/2"};
        Function<double[], double[]> parsedFunction = parseFunction(function);
        System.out.println(parsedFunction.apply(new double[]{5, 6})[0]);
    }
}
