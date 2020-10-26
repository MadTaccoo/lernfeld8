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
                }
            }
        }
        return sum;
    }

    /**
     * calculates the interval of roots and than the roots themself and store them in a arraylist
     * @param x start interval
     * @param x1 end interval
     * @param prec
     */
    public void setIntervalAndRoots(int x, int x1, float prec) {
        roots.clear();
        intervalAr = Newton.interval(x, x1, this, prec);
        for (int i = 0; i < intervalAr[0].length; i++)
            roots.add(Newton.newton(this, intervalAr[0][i],intervalAr[1][i], prec));
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