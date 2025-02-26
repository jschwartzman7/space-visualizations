package spacevisuals.functionhandling;

public class FunctionBuilderTest {

    // write test cases for FunctionBuilder parse function string

    public static boolean testFunctionBuilder(){
        String[] functionStringArray = {"7+x*3", "2*e^(z)*3^z-x", "(1+y)/(z*6)"};
        FunctionBuilder functionBuilder = new FunctionBuilder();
        for(double x = -3; x <= 3; x++){
            for(double y = -3; y <= 3; y++){
                for(double z = 1; z <= 4; z++){
                    double[] input = {x, y, z};
                    double[] output = functionBuilder.parseFunction(functionStringArray).apply(input);
                    System.out.println("output: x=" + (7+x*3) + ", y=" + (2*Math.pow(Math.E, z)*Math.pow(3,z)-x) + ", z=" + ((1+y)/(z*6)));
                    System.out.println("Output: " + output[0] + ", " + output[1] + ", " + output[2]);
                    if((Math.abs(output[0] - 7+(x+Math.sin(y))*3)<0.01 &&
                        Math.abs(2*Math.pow(Math.E, z)*Math.pow(3,z)-x-output[1])<0.01 &&
                        Math.abs((1+y)/(z*6)-output[2])<0.01)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(testFunctionBuilder());
    }
    
}
