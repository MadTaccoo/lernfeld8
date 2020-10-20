package Search;

public class LinearSearch {
    public static int getIndex(double[] ls,double target){
        for (int i = 0; i < ls.length; i++) {
            if(ls[i]==target)
                return i;
        }
        return Integer.MIN_VALUE;
    }
}
