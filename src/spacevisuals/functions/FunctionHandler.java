package spacevisuals.functions;

import java.util.HashMap;
import java.util.function.Function;

public class FunctionHandler {
    public static HashMap<String, Function<double[], double[]>> functions = new HashMap<String, Function<double[], double[]>>(){{
        put("id", u -> u);
        put("hyperbolicparabaloid", u -> new double[]{Rn_R.hyperbolicParabaloid(u, .5, 0.5)});
        put("innerproduct", u -> new double[]{Rn_R.dotProduct(u, new double[]{1, 1})});
        put("sumsquares", u -> new double[]{Rn_R.sumOfSquares(u)});
        put("sumsines", u -> new double[]{Rn_R.sumOfSines(u)});
        put("essentialsingularity", C_C::essentialSingularity);
        put("etothexsquared", C_C::eToTheXSquared);
        put("pairwisesine", u -> Rn_Rm.sin(u));
    }};
    
}
