package NumericalMathematics.Newton;

/**
 * just a help class to store mathematical functions
 */
public class FunctionHelper {
    public double a,c,b,n;
    public int selection;

    public FunctionHelper(double a, double c, double b, double n, int selection) {
        this.a = a;
        this.c = c;
        this.b = b;
        this.n = n;
        this.selection = selection;
    }
    public FunctionHelper(double a,double n) {
        this.a = a;
        this.n = n;
        selection = Integer.MIN_VALUE;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}