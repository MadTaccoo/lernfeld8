package NumericalMathematics.Newton;

import java.util.ArrayList;

public class Newton {
    /**
     * simple "Newton's method" function based on
     * x1 = x0-(f(x0)/f'(x0))
     *
     * @param f       function of which we want to find the roots
     * @param inteval start interval right before the root (As close as possible)
     * @param prec    repetitions of for loop with increase precision
     * @return approximation of root
     */
    public static double newton(Function f, double inteval, int prec) {
        Function df = new Function(f);
        for (int i = 0; i < prec; i++) {
            inteval = inteval - f.sum(inteval) / df.sum(inteval);
        }
        return inteval;
    }

    /**
     * "Newton's method" function based on
     * x1 = x0-(f(x0)/f'(x0))
     * but this time it is optimized with a check for convergence
     * only downside we need to know the complete interval x1 to x2
     *
     * @param fkt function of which we want to approximation the roots
     * @param x1  start interval
     * @param x2  end interval
     *            this function will search for roots between x1 and x2
     * @param eps acceptable difference between f(x) and f'(x)
     * @return approximated root
     */
    public static double newton(Function fkt, double x1, double x2, double eps) {
        if(round(x1,1)==-0.1 && round(x2,1) ==0.1)
            return 0;
        double x = 0.5 * (x1 + x2);
        Function df = new Function(fkt);
        for (int j = 0; j < 10000; j++) {
            double dx = fkt.sum(x) / df.sum(x);
            x -= dx;
            if (Math.abs(dx) < eps) return x;
        }
        System.out.println("Error");
        return Integer.MIN_VALUE;
    }

    /**
     * function to get the root interval of given function
     * @param from      x index from which the function is checked
     * @param to        x index to which the function is checked
     * @param func      function of which we want he interval of its roots
     * @param precision how precise you want the interval to be
     * @return double[][] array with x and x1 arr[0][x] is always the start and arr[1][x] is the end
     */
    public static double[][] interval(int from, int to, Function func, Float precision) {
        float lastIndex = from;
        //math sign '+'|'-'
        char LastMathSign = func.sum(from) > 0 ? '+' : '-';
        ArrayList<Float> interV1 = new ArrayList<>();
        ArrayList<Float> interV2 = new ArrayList<>();
        //iterate from -> to
        for (float i = from; i < to; i += precision) {
            //detects if the function in f(i) is falling or rising
            char MathSign = func.sum(i) > 0 && i != 0 ? '+' : '-';
            //if the sign changes there is an root nearby i can smell it
            if (LastMathSign != MathSign) {
                interV1.add(lastIndex);
                interV2.add(i);
            }
            //sets the last index to i
            if (func.sum(lastIndex) > func.sum(i) || func.sum(lastIndex) < func.sum(i))
                lastIndex = i;
            LastMathSign = func.sum(i) >= 0 ? '+' : '-';
        }
        //if the function has a root at (0|0)
        if (func.sum(0) == 0) {
            interV1.add(-0.1F);
            interV2.add(0.1F);
        }
        //create array to return both arraylists
        double[][] retLst = new double[2][interV1.size()];
        for (int i = 0; i < retLst[0].length; i++) {
            retLst[0][i] = interV1.get(i);
            retLst[1][i] = interV2.get(i);
        }
        return retLst;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
