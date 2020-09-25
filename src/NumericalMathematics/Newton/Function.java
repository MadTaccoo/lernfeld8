package NumericalMathematics.Newton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Function {
    public static void main(String[] args) {
        Function f = new Function();
        f.insertInto(3,1);
        f.insertInto(2,5);
        f.insertInto(0,-5);
        Function fI = new Function(f);
        double[][] ls = f.intervall(-1000,1000,f,0.0001);
        for (int i = 0; i <= ls.length; i++) {
            System.out.println(ls[0][i] +" "+ls[1][i]);
        }
    }

    //ax^n
    //map(n,a)
    private Map<Double,Double> map;

    public Function() {
        map = new HashMap<>();
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

    //insert more data
    public void insertInto(double n, double a){
       if(!map.containsKey(n))
           map.put(n,a);
       else
           throw new IllegalArgumentException();
    }

    //sum value at x
    public double sum(double x){
        double sum = 0;
        for (Map.Entry<Double, Double> entry : map.entrySet()) {
            sum += Math.pow(x,entry.getKey())*entry.getValue();
        }
        return sum;
    }

    public double[][] intervall(int from,int to,Function func,double precision){
        double lastIndex = from;
        char cL = '+';
        ArrayList<Double> interV1 = new ArrayList<>();
        ArrayList<Double> interV2 = new ArrayList<>();
        for (double i = from; i < to; i+=) {
            if(cL != ((func.sum(i)>0 && i != 0)?'+':'-')){
                interV1.add(lastIndex);
                interV2.add(i);
            }
            if(func.sum(lastIndex)>func.sum(i) || func.sum(lastIndex)<func.sum(i))
                lastIndex = i;
            cL = (func.sum(i)>0)?'+':'-';
        }
        interV1.remove(0);
        interV2.remove(0);

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
