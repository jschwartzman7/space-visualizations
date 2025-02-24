package spacevisuals.functions;

public class Statistics {
    public static double mean(double[] data) {
        double sum = 0;
        for (double datum : data) {
            sum += datum;
        }
        return sum / data.length;
    }
    public static double mean(Double[] data) {
        double sum = 0;
        for (double datum : data) {
            sum += datum;
        }
        return sum / data.length;
    }

    public static double variance(double[] data) {
        double mean = mean(data);
        double sum = 0;
        for (double datum : data) {
            sum += Math.pow(datum - mean, 2);
        }
        return sum / data.length;
    }
    public static double variance(Double[] data) {
        double mean = mean(data);
        double sum = 0;
        for (double datum : data) {
            sum += Math.pow(datum - mean, 2);
        }
        return sum / data.length;
    }

    public static double standardDeviation(double[] data) {
        return Math.sqrt(variance(data));
    }
    public static double standardDeviation(Double[] data) {
        return Math.sqrt(variance(data));
    }

    public static double median(double[] data) {
        int n = data.length;
        if (n % 2 == 0) {
            return (data[n / 2 - 1] + data[n / 2]) / 2;
        } else {
            return data[n / 2];
        }
    }
    public static double median(Double[] data) {
        int n = data.length;
        if (n % 2 == 0) {
            return (data[n / 2 - 1] + data[n / 2]) / 2;
        } else {
            return data[n / 2];
        }
    }
    public static double max(double[] data) {
        double max = data[0];
        for (double datum : data) {
            if (datum > max) {
                max = datum;
            }
        }
        return max;
    }
    public static double max(Double[] data) {
        double max = data[0];
        for (double datum : data) {
            if (datum > max) {
                max = datum;
            }
        }
        return max;
    }

    public static double min(double[] data) {
        double min = data[0];
        for (double datum : data) {
            if (datum < min) {
                min = datum;
            }
        }
        return min;
    }

    public static double min(Double[] data) {
        double min = data[0];
        for (double datum : data) {
            if (datum < min) {
                min = datum;
            }
        }
        return min;
    }
    
}
