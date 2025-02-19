package spacevisuals.helpers;

import java.util.function.Function;
import spacevisuals.functions.*;

public enum FunctionHandler {

    identity(u -> u),
    zero(u -> new double[]{0}),
    hyperbolicparaboloid(u -> new double[]{Rn_R.hyperbolicParabaloid(u, .5, 0.5)}),
    innerproduct(u -> new double[]{Rn_R.dotProduct(u, new double[]{1, 1})}),
    sumsquares(u -> new double[]{Rn_R.sumOfSquares(u)}),
    sumvector(u -> new double[]{Rn_R.sumOfVector(u)}),
    sumsines(u -> new double[]{Rn_R.sumOfSines(u)}),
    sumcosines(u -> new double[]{Rn_R.sumOfCosines(u)}),
    productsquares(u -> new double[]{Rn_R.productOfSquares(u)}),
    productvector(u -> new double[]{Rn_R.productOfVector(u)}),
    productsines(u -> new double[]{Rn_R.productOfSines(u)}),
    productcosines(u -> new double[]{Rn_R.productOfCosines(u)}),
    add(u -> Rn_Rm.add(u, new double[]{1, 1})),
    subtract(u -> Rn_Rm.subtract(u, new double[]{1, 1})),
    essentialsingularity(C_C::essentialSingularity),
    etothexsquared(C_C::eToTheXSquared),
    sin(u -> Rn_Rm.sin(u)),
    cos(u -> Rn_Rm.cos(u)),
    parametriccircle(u -> new double[]{Math.sin(u[0]), Math.cos(u[0])}),
    parametric(u -> new double[]{Math.sin(6*u[0]), Math.cos(5*u[0])});

    private final Function<double[], double[]> function;

    FunctionHandler(Function<double[], double[]> function) {
        this.function = function;
    }

    public Function<double[], double[]> getFunction() {
        return function;
    }

    public static FunctionHandler from(String text) {
        FunctionHandler function = FunctionHandler.identity;
        try {
            function = valueOf(text);
        }
        catch (Exception ignore) {
        }
        return function;
    }

}
