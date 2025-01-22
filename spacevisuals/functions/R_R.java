import java.util.function.*;
public class R_R{
    
    static double[] zero(){
        return new double[]{0};
    }

    static BiFunction<Double, Double, Double> power = (x, y) -> Math.pow(x, y);
    static BiFunction<Double, Double, Double> add = (x, y) -> x + y;

    static Function<Double, Double> negative = (x) -> -x;
    static Function<Double, Double> squareRoot = (x) -> Math.sqrt(x);

    static Function<Double, Double> squareRootThenNegate = squareRoot.andThen(negative);
    static BiFunction<Double, Double, Double> powerThenSquareRoot = power.andThen(squareRoot);
    static Function<Double, Double> negativeSquareRoot = negative.compose(squareRoot);

    public static void main(String[] args) {
        System.out.println(powerThenSquareRoot.apply(2.0, 4.0));
        System.out.println(negativeSquareRoot.apply(64.0));
        
    }
}
