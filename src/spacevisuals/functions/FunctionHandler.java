package spacevisuals.functions;

import java.util.HashMap;
import java.util.function.Function;

public class FunctionHandler {
    public static HashMap<String, Function<double[], double[]>> functions = new HashMap<String, Function<double[], double[]>>(){{
        put("id", u -> u);
        put("zero", u -> new double[]{0});
        put("hyperbolicparabaloid", u -> new double[]{Rn_R.hyperbolicParabaloid(u, .5, 0.5)});
        put("innerproduct", u -> new double[]{Rn_R.dotProduct(u, new double[]{1, 1})});
        put("sumsquares", u -> new double[]{Rn_R.sumOfSquares(u)});
        put("sumvector", u -> new double[]{Rn_R.sumOfVector(u)});
        put("sumsines", u -> new double[]{Rn_R.sumOfSines(u)});
        put("sumcosines", u -> new double[]{Rn_R.sumOfCosines(u)});
        put("productsquares", u -> new double[]{Rn_R.productOfSquares(u)});
        put("productvector", u -> new double[]{Rn_R.productOfVector(u)});
        put("productsines", u -> new double[]{Rn_R.productOfSines(u)});
        put("add", u -> Rn_Rm.add(u, new double[]{1, 1}));
        put("subtract", u -> Rn_Rm.subtract(u, new double[]{1, 1}));
        put("essentialsingularity", C_C::essentialSingularity);
        put("etothexsquared", C_C::eToTheXSquared);
        put("sin", u -> Rn_Rm.sin(u));
        put("cos", u -> Rn_Rm.cos(u));
    }};
    
}
