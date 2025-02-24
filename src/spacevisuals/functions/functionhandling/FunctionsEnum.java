package spacevisuals.functions.functionhandling;

import java.util.function.Function;
import spacevisuals.functions.*;

/*
 * Built in functions the user can specify after an animations instead of writing their own function
 */
public enum FunctionsEnum {

    identity(u -> u),
    zero(u -> new double[]{0}),
    hyperbolicparaboloid(u -> new double[]{Rn_R.hyperbolicParabaloid(u, 0.3, 0.3)}),
    innerproduct(u -> new double[]{Rn_R.dotProduct(u, new double[]{1, 1})}),
    sumsquares(u -> new double[]{Rn_R.sumOfSquares(u)}),
    sumvector(u -> new double[]{Rn_R.sumOfVector(u)}),
    sumsines(u -> new double[]{Rn_R.sumOfSines(u)}),
    sumcosines(u -> new double[]{Rn_R.sumOfCosines(u)}),
    productsquares(u -> new double[]{Rn_R.productOfSquares(u)}),
    productvector(u -> new double[]{Rn_R.productOfVector(u)}),
    productsines(u -> new double[]{Rn_R.productOfSines(u)}),
    productcosines(u -> new double[]{Rn_R.productOfCosines(u)}),
    add(u -> Rn_Rn.pairwiseAdd(u, new double[]{1, 1})),
    subtract(u -> Rn_Rn.pairwiseSubtract(u, new double[]{1, 1})),
    essentialsingularity(C_C::essentialSingularity),
    etothexsquared(C_C::eToTheXSquared),
    sin(u -> Rn_Rn.sin(u)),
    cos(u -> Rn_Rn.cos(u)),
    parametriccircle(u -> new double[]{Math.sin(u[0]), Math.cos(u[0])}),
    parametric(u -> new double[]{Math.sin(6*u[0]), Math.cos(5*u[0])});

    public final Function<double[], double[]> function;

    FunctionsEnum(Function<double[], double[]> function) {
        this.function = function;
    }

    public static FunctionsEnum from(String text) {
        FunctionsEnum function = FunctionsEnum.identity;
        try {
            function = valueOf(text);
        }
        catch (Exception ignore) {
        }
        return function;
    }

}
