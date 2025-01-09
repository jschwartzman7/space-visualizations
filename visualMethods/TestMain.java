public class TestMain {
    public static void main(String[] args) {
        FunctionC_C func = new FunctionC_C();
        double[] z = new double[]{0.5, 0.32};
        double[] w = func.divide(func.one, func.subtract(z, func.one));
        System.out.println(w[0]);
        System.out.println(w[1] + "i");
    }
}
