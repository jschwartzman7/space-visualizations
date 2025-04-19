package spacevisuals.enums;

import java.util.function.Function;
import spacevisuals.functions.*;
import spacevisuals.utils.Constants;

/*
 * Built in functions the user can specify after an animations instead of writing their own function
 */
public enum FunctionsEnum {

    // endomorphisms
    identity(u -> u),
    zero(u -> new double[u.length]),
    essentialsingularity(C_C::essentialSingularity),
    etothexsquared(C_C::eToTheXSquared),
    sin(u -> Rn_Rn.sin(u)),
    cos(u -> Rn_Rn.cos(u)),
    squared(u -> Rn_Rn.square(u)),
    cubed(u -> Rn_Rn.cube(u)),
    squareroot(u -> Rn_Rn.power(u, 0.5)),

    // real valued
    magnitude(u -> new double[]{Rn_R.magnitude(u)}),
    hyperbolicparabaloid(u -> new double[]{Rn_R.hyperbolicParabaloid(u, 0.3, 0.3)}),
    dotproduct(u -> new double[]{Rn_R.dotProduct(u, new double[]{1, 1})}),
    sumsquares(u -> new double[]{Rn_R.sumOfSquares(u)}),
    sumvector(u -> new double[]{Rn_R.sumOfVector(u)}),
    sumsines(u -> new double[]{Rn_R.sumOfSines(u)}),
    sumcosines(u -> new double[]{Rn_R.sumOfCosines(u)}),
    productsquares(u -> new double[]{Rn_R.productOfSquares(u)}),
    productvector(u -> new double[]{Rn_R.productOfVector(u)}),
    productsines(u -> new double[]{Rn_R.productOfSines(u)}),
    productcosines(u -> new double[]{Rn_R.productOfCosines(u)}),
    
    cossin(u -> new double[]{Math.sin(u[0])+Math.cos(u[1]), Math.cos(u[1])+Math.sin(u[0])}),
    parametriccircle(u -> new double[]{Math.sin(u[0]), Math.cos(u[0])}),
    parametric(u -> new double[]{Math.sin(6*u[0]), Math.cos(5*u[0])}),
    logmulti(u -> C_C.logMultivalue(u, 3));

    public final Function<double[], double[]> function;

    FunctionsEnum(Function<double[], double[]> function) {
        this.function = function;
    }
}
