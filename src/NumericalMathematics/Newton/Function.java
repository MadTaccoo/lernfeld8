package NumericalMathematics.Newton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Function {
    public static void main(String[] args) {
        Function f = new Function();
        f.insertInto(4,1); //x^4+5x^2
        f.insertInto(2,5);
        f.insertInto(0,10);

        double[][] ls = f.intervall(-1000,1000,f,0.001F);
        for (int i = 0; i < ls[0].length; i++) {
            System.out.println(ls[0][i]+" "+ls[1][i]);
            //System.out.println(f.newton3(f,ls[0][i],ls[1][i],1));
        }
        //System.out.println("0 und 2/3");
    }

    //ax^n
    //map(n,a)
    private Map<Double,Double> map;
    private Map<Double,SpecialFunctionHelper> specialFunc;

    public Function() {
        map = new HashMap<>();
        specialFunc = new HashMap<>();
    }

    //Ableitung Basic
    public Function(Function f){
        map = new HashMap<>();
        double a,n;
        for (Map.Entry<Double, Double> entry : f.getMap().entrySet()) {
            a = entry.getValue()*entry.getKey();
            n = entry.getKey()-1;
            if(a>0)
                map.put(n,a);
        }
    }

    public double newtonEz(Function f,double inteval,int prec){
        Function df = new Function(f);
        for (int i = 0; i < prec; i++) {
            inteval = inteval -f.sum(inteval)/df.sum(inteval);
        }
        return inteval;
    }

    double newton3(Function fkt, double x1, double x2, double eps) {
        double x=0.5*(x1+x2);
        Function df = new Function(fkt);
        for (int j=0; j<10000; j++) {
            double dx=fkt.sum(x)/df.sum(x);
            x -= dx;
            if ((x1-x)*(x-x2) < 0.0) {
                return 0.0;
            }
            if (Math.abs(dx) < eps) return x;
        }
        return 0.0;
    }

    //insert more data
    public void insertInto(double n, double a) {
        if (!map.containsKey(n))
            map.put(n, a);
        else
            throw new IllegalArgumentException();
    }
    public void insertInto(double n, double a,double c,double b, int cos_tan_sin, int arc) {
        if (!specialFunc.containsKey(n))
            specialFunc.put(n, new SpecialFunctionHelper(a,c,b,n,cos_tan_sin*arc));
        else
            throw new IllegalArgumentException();
        if (!map.containsKey(n))
            map.put(n, a);
        else
            throw new IllegalArgumentException();
    }

    //sum value at x
    public double sum(double x){
        if(specialFunc.size() == 0){
            double sum = 0;
            for (Map.Entry<Double, Double> entry : map.entrySet()) {
                sum += Math.pow(x,entry.getKey())*entry.getValue();
            }
            return sum;
        }else {
            double sum = 0;
            for (Map.Entry<Double, Double> entry : map.entrySet()) {
                double a = entry.getValue(),n = entry.getKey();
                if(specialFunc.containsKey(entry.getKey())){
                    SpecialFunctionHelper selection = specialFunc.get(n);
                    switch (selection.selection){
                        case 1:
                            sum += selection.a*Math.cos(selection.getB()*(x-selection.getC()));
                            break;
                        case 2:
                            sum += selection.a*Math.tan(selection.getB()*(x-selection.getC()));
                            break;
                        case 3:
                            sum += selection.a*Math.sin(selection.getB()*(x-selection.getC()));
                            break;
                        case -1:
                            sum += selection.a*Math.acos(selection.getB()*(x-selection.getC()));
                            break;
                        case -2:
                            sum += selection.a*Math.atan(selection.getB()*(x-selection.getC()));
                            break;
                        case -3:
                            sum += selection.a*Math.asin(selection.getB()*(x-selection.getC()));
                            break;
                    }

                }
            }
            return sum;
        }

    }

    public double[][] intervall(int from,int to,Function func,Float precision){
        float lastIndex = from;
        char cL = '+';
        ArrayList<Float> interV1 = new ArrayList<>();
        ArrayList<Float> interV2 = new ArrayList<>();

        for (float i = from; i < to; i+=precision) {
            char c = func.sum(i) > 0 && i != 0 ? '+' : '-';
            if(cL != c){
                interV1.add(lastIndex);
                interV2.add(i);
            }
            if(func.sum(lastIndex)>func.sum(i) || func.sum(lastIndex)<func.sum(i))
                lastIndex = i;
            cL = func.sum(i) >= 0 ? '+' : '-';
        }



        if (interV1.size()>0)
            interV1.remove(0);
        if(interV2.size()>0)
            interV2.remove(0);
        if(func.sum(0) == 0){
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

    public Map<Double, Double> getMap() {
        return map;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Double, Double> entry : map.entrySet()) {
            str.append((entry.getValue() > 0) ? "+" : "").append(entry.getValue()).append("x^").append(entry.getKey());
        }
        return str.toString();
    }
}
