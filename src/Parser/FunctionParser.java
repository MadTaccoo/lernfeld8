package Parser;

import NumericalMathematics.Newton.Function;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Arrays;
import java.util.List;

/**
 * Simple not perfect function parser
 * should work for most functions care that you use the correct notation
 * in this case enter each part of the function like this x^3 0.5x^2 x
 * always use 0.5 instead of 1/2!!! otherwise it will not work
 * also the tan() and atan(x) are not implemented
 * sin/cos asin/acos work ether sin(x) or asin(b(x-c) just replace a,b,c with values and keep in mind that b can not be 0
 *
 */
public class FunctionParser {
    //Lists which contain all special functions
    final static String[] ls = new String[]{"sin", "cos", "tan", "asin", "atan", "acos"};
    final static List<String> Arrls = Arrays.asList(ls);
    public static Function parse(String s) {
        //Create function which will me returned
        Function f = new Function();
        //in case the whole function is just "x"
        if(s.equals("x")){
            f.insertInto(1,1);
            return f;
        }
        //splits the incoming string at ' '
        String[] parts = s.split(" ");
        //iterates all parts resulting from the split
        for (String part : parts) {
            String[] pt;
            //checks if part contains any of the above declared sin/cos array
            if (Arrays.stream(ls).anyMatch(part::contains)) {
                //gets the index of sin,cos,asin,acos
                int index = getSpec(part.substring(0,part.length()-3));
                int sel = 0;
                //1=cos, 2=tan, 3=sin, 4=acos, 5=atan, 6=asin
                switch (index){
                    case 0: sel = 3;break;
                    case 1: sel = 1;break;
                    case 2: sel = 2;break;

                    case 3: sel = -3;break;
                    case 4: sel = -2;break;
                    case 5: sel = -1;break;

                    default:sel = Integer.MIN_VALUE;
                }
                //splits around the sin/cos keep asin(b(x-c) syntax in mind
                pt = part.split(ls[index]);
                if(pt[0].equals(""))
                    if(pt[1].equals("(x)")){
                        f.insertInto(1,0,1,sel,1);
                        continue;
                    }else{
                        //only use parts of asin(b(x-c) syntax for example sin(x-c)
                        throw new NotImplementedException();
                    }
                //gets all needed data from string
                double a = Double.parseDouble(pt[0]);
                String[] split = pt[1].replace("(","").replace(")","").split("x");
                double b = Double.parseDouble(split[0]);
                double c = Double.parseDouble(split[1]);

                f.insertInto(a,c,b,sel,1);
            } else {
                //in case one part of the func is just 'x'
                if(part.equals("x")){
                    f.insertInto(1,1);
                    continue;
                }
                //splits the given part of the string at 'x^' ax^n
                //this leaves a and n free to read
                pt = part.split("x\\^");
                if (pt.length != 2) {
                    if(pt[0].contains("x")){
                        pt[0] = pt[0].replace("x","");
                        f.insertInto(1,Double.parseDouble(pt[0]));
                        continue;
                    }
                    f.insertInto(0,Double.parseDouble(pt[0]));
                    continue;
                }
                double n = Double.parseDouble(pt[1]);
                double a = Double.parseDouble(pt[0].equals("")?"1":pt[0]);
                f.insertInto(n, a);
            }
        }
        return f;
    }

    /**
     * gets index in array of given string
     * @param str input value like sin/cos...
     * @return index of string in array
     */
    private static int getSpec(String str){
        for (int i = 0; i < Arrls.size(); i++) {
            if(str.equals(Arrls.get(i))){
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }
}
