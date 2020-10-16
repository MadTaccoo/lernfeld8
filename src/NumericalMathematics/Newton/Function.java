package NumericalMathematics.Newton;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Map;

public class Function {
    //1=cos, 2=tan, 3=sin, 4=acos, 5=atan, 6=asin
    public static void main(String[] args) {
        Function f = new Function();

        f.insertInto(1,2,3,4,1,-1);

        System.out.println(f.sum(3));
        System.out.println(f.toString());
    }

    private ArrayList<FunctionHelper> map;

    public Function() {
        map = new ArrayList<>();
    }

    //Ableitung Basic
    public Function(Function f) {
        map = new ArrayList<>();
        double a, n;
        for (FunctionHelper fh:f.map) {
            if(fh.selection == Integer.MIN_VALUE){
                a = fh.a * fh.n;
                n = fh.n-1;
                if (a > 0)
                    map.add(new FunctionHelper(n, a));
            }else{

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
                throw new NotImplementedException();
            }

        }
    }

    //insert more data
    public void insertInto(double n, double a) {
        map.add(new FunctionHelper(a, n));
    }

    public void insertInto(double n, double a, double c, double b, int cos_tan_sin, int arc) {
        map.add(new FunctionHelper(a, c, b, n, cos_tan_sin * arc));
    }

    //1=cos, 2=tan, 3=sin, 4=acos, 5=atan, 6=asin
    public double sum(double x) {
        double sum = 0;
        for (FunctionHelper entry : map) {
            if (entry.selection == Integer.MIN_VALUE) {
                sum += Math.pow(entry.a, entry.n);
                continue;
            } else {
                switch (entry.selection) {
                    case 1:
                        sum += entry.a * Math.pow(Math.cos(entry.getB() * (x - entry.getC())),entry.n);
                        break;
                    case 2:
                        sum += entry.a * Math.pow(Math.tan(entry.getB() * (x - entry.getC())),entry.n);
                        break;
                    case 3:
                        sum += entry.a * Math.pow(Math.sin(entry.getB() * (x - entry.getC())),entry.n);
                        break;
                    case -1:
                        sum += entry.a * Math.pow(Math.acos(entry.getB() * (x - entry.getC())),entry.n);
                        break;
                    case -2:
                        sum += entry.a * Math.pow(Math.atan(entry.getB() * (x - entry.getC())),entry.n);
                        break;
                    case -3:
                        sum += entry.a * Math.pow(Math.asin(entry.getB() * (x - entry.getC())),entry.n);
                        break;
                    default:
                        System.out.println("Error");
                }
            }
        }
        return sum;
    }

    public double newtonEz(Function f, double inteval, int prec) {
        Function df = new Function(f);
        for (int i = 0; i < prec; i++) {
            inteval = inteval - f.sum(inteval) / df.sum(inteval);
        }
        return inteval;
    }

    double newton3(Function fkt, double x1, double x2, double eps) {
        double x = 0.5 * (x1 + x2);
        Function df = new Function(fkt);
        for (int j = 0; j < 10000; j++) {
            double dx = fkt.sum(x) / df.sum(x);
            x -= dx;
            if ((x1 - x) * (x - x2) < 0.0) {
                return 0.0;
            }
            if (Math.abs(dx) < eps) return x;
        }
        return 0.0;
    }


    public double[][] intervall(int from, int to, Function func, Float precision) {
        float lastIndex = from;
        char cL = '+';
        ArrayList<Float> interV1 = new ArrayList<>();
        ArrayList<Float> interV2 = new ArrayList<>();

        for (float i = from; i < to; i += precision) {
            char c = func.sum(i) > 0 && i != 0 ? '+' : '-';
            if (cL != c) {
                interV1.add(lastIndex);
                interV2.add(i);
            }
            if (func.sum(lastIndex) > func.sum(i) || func.sum(lastIndex) < func.sum(i))
                lastIndex = i;
            cL = func.sum(i) >= 0 ? '+' : '-';
        }


        if (interV1.size() > 0)
            interV1.remove(0);
        if (interV2.size() > 0)
            interV2.remove(0);
        if (func.sum(0) == 0) {
            interV1.add(-0.1F);
            interV2.add(0.1F);
        }

        double[][] lst = new double[2][interV1.size()];
        for (int i = 0; i < lst[0].length; i++) {
            lst[0][i] = interV1.get(i);
            lst[1][i] = interV2.get(i);
        }
        return lst;
    }
    //1=cos, 2=tan, 3=sin, 4=acos, 5=atan, 6=asin

    //(a, c, b, n, cos_tan_sin * arc));
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (FunctionHelper f: map) {
            String operation = "";
            if(f.selection == Integer.MIN_VALUE){
                str.append(f.a).append("x^").append(f.n).append(" ");
            }else{
                switch (f.selection) {
                    case 1: operation = "cos"; break;
                    case 2: operation = "tan"; break;
                    case 3: operation = "sin"; break;
                    case -1: operation = "acos"; break;
                    case -2: operation = "atan"; break;
                    case -3: operation = "asin"; break;
                    default: System.out.println("Error");
                }
                str.append(f.a).append(operation).append("(").append(f.b).append("(").append("x-").append(f.c).append(") ");
            }
        }
        return str.toString();
    }
}
