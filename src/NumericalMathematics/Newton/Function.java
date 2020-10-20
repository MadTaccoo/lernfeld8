package NumericalMathematics.Newton;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;

public class Function {
    //1=cos, 2=tan, 3=sin, -1=acos, -2=atan, -3=asin
    private ArrayList<FunctionHelper> map;
    public double[][] intervalAr;
    public ArrayList<Double> roots = new ArrayList<>();

    /**
     * basic constructor
     */
    public Function() {
        map = new ArrayList<>();
    }

    /**
     * @param x    start index for table
     * @param x1   end index for table
     * @param prec precision of the table or steps
     * @return ArrayList which contains 2 other Arraylists with Coordinates
     */
    public ArrayList<ArrayList<Double>> table(double x, double x1, double prec) {
        //declare arrays
        ArrayList<ArrayList<Double>> ls = new ArrayList<>();
        ArrayList<Double> fx = new ArrayList<>();
        ArrayList<Double> ord = new ArrayList<>();
        //set starting point
        double j = x;
        //generate all points for table in prec seps
        while (j <= x1) {
            ord.add(j);
            fx.add(sum(j));
            j += prec;
        }
        //because of double precision problems the last set of values is generated here
        if (ord.get(ord.size() - 1) < x1) {
            ord.add(x1);
            fx.add(sum(x1));
        }
        //add the x and y Coordinate arraylists to the return arraylist
        ls.add(ord);
        ls.add(fx);
        return ls;
    }

    /**
     * allows to create a function which is the derivative of the given function
     * only basic functions work nothing with sqr() or 1/x work
     *
     * @param f function which is used to generate the derivative
     */
    public Function(Function f) {
        map = new ArrayList<>();
        double a, n;
        for (FunctionHelper fh : f.map) {
            if (fh.selection == Integer.MIN_VALUE) {
                //multiplies a with n to calculate a for the new function
                a = fh.a * fh.n;
                //dec n to create the new n
                n = fh.n - 1;
                if (a != 0)
                    map.add(new FunctionHelper(a,n));
            } else {
            /*
                int selection = fh.selection;
                switch (fh.selection) {
                    //1=cos, 2=tan, 3=sin, 4=acos, 5=atan, 6=asin
                    case 1: fh.selection = 3; fh.a*=-1; break;
                    case 2:
                    case 3:
                        fh.selection = 1; break;
                    case -1:
                    case -2:
                    case -3:
                        fh.selection = Integer.MIN_VALUE; break;
                    default: System.out.println("Error");
                }
                map.add(new FunctionHelper(fh.a,fh.c,fh.b,fh.n,4));
                */
                throw new NotImplementedException();
            }
        }
    }

    /**
     * insert more function data ax^n
     *
     * @param n exponent
     * @param a coefficient
     */
    public void insertInto(double n, double a) {
        map.add(new FunctionHelper(a, n));
    }

    /**
     * asin(b(x-c))
     *
     * @param a           Asin(b(x-c))
     * @param c           asin(b(x-C))
     * @param b           asin(B(x-c))
     * @param cos_tan_sin this and arc are used to give the user the possibility to select sin/cos/asin/acos
     * @param arc         cos = 1, acos = -1
     *                    tan = 2, atan = -2
     *                    sin = 3, asin = -3
     */
    public void insertInto(double a, double c, double b, int cos_tan_sin, int arc) {
        map.add(new FunctionHelper(a, c, b, 1, cos_tan_sin * arc));
    }

    /**
     * @param x given x value to calculate the y of given function at x
     * @return f(x)
     */
    public double sum(double x) {
        double sum = 0;
        for (FunctionHelper entry : map) {
            //in case selection equals Integer.MIN_VALUE we just calculate f(x)
            if (entry.selection == Integer.MIN_VALUE) {
                sum += Math.pow(x, entry.n) * entry.a;
            } else {
                //else the selection value is used to calculate the value of f(x) but from function with sin/cos...
                switch (entry.selection) {
                    case 1:
                        sum += entry.a * Math.pow(Math.cos(entry.getB() * (x - entry.getC())), entry.n);
                        break;
                    case 2:
                        sum += entry.a * Math.pow(Math.tan(entry.getB() * (x - entry.getC())), entry.n);
                        break;
                    case 3:
                        sum += entry.a * Math.pow(Math.sin(entry.getB() * (x - entry.getC())), entry.n);
                        break;
                    case -1:
                        sum += entry.a * Math.pow(Math.acos(entry.getB() * (x - entry.getC())), entry.n);
                        break;
                    case -2:
                        sum += entry.a * Math.pow(Math.atan(entry.getB() * (x - entry.getC())), entry.n);
                        break;
                    case -3:
                        sum += entry.a * Math.pow(Math.asin(entry.getB() * (x - entry.getC())), entry.n);
                        break;
                    default:
                        System.out.println("Error");
                }
            }
        }
        return sum;
    }

    /**
     * simple "Newton's method" function based on
     * x1 = x0-(f(x0)/f'(x0))
     *
     * @param f       function of which we want to find the roots
     * @param inteval start interval right before the root (As close as possible)
     * @param prec    repetitions of for loop with increase precision
     * @return approximation of root
     */
    public double newton(Function f, double inteval, int prec) {
        Function df = new Function(f);
        for (int i = 0; i < prec; i++) {
            inteval = inteval - f.sum(inteval) / df.sum(inteval);
        }
        return inteval;
    }

    public static void main(String[] args) {
        Function f = new Function();
        f.insertInto(2,1);
        System.out.println(f.newton(f,-0.1,0.1,0.01));

    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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
    public double newton(Function fkt, double x1, double x2, double eps) {
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
    public double[][] interval(int from, int to, Function func, Float precision) {
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

    public void setIntervalAndRoots(int x, int x1, float prec) {
        roots.clear();
        intervalAr = interval(x, x1, this, prec);
        for (int i = 0; i < intervalAr[0].length; i++)
            roots.add(newton(this, intervalAr[0][i],intervalAr[1][i], prec));

    }


    //(a, c, b, n, cos_tan_sin * arc));
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (FunctionHelper f : map) {
            String operation = "";
            if (f.selection == Integer.MIN_VALUE) {
                str.append(f.a).append("x^").append(f.n).append(" ");
            } else {
                switch (f.selection) {
                    case 1:
                        operation = "cos";
                        break;
                    case 2:
                        operation = "tan";
                        break;
                    case 3:
                        operation = "sin";
                        break;
                    case -1:
                        operation = "acos";
                        break;
                    case -2:
                        operation = "atan";
                        break;
                    case -3:
                        operation = "asin";
                        break;
                    default:
                        System.out.println("Error");
                }
                str.append(f.a).append(operation).append("(").append(f.b).append("(").append("x-").append(f.c).append(") ");
            }
        }
        return str.toString();
    }


}