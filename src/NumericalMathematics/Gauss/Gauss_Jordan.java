package NumericalMathematics.Gauss;

import java.util.Arrays;

public abstract class Gauss_Jordan {

    public static void main(String[] args) {
        double[][] arr = new double[3][4];
        arr[0] = new double[]{1,4,5,7};
        arr[1] = new double[]{0,1,1,8};
        arr[2] = new double[]{0,0,1,2};
        for (double d:
                gaussJ(arr)) {
            System.out.println(d);
        }
    }

    public static double[] gaussJ(double[][] arr){
        normalize(arr);
        int topRow = 0;
        while (topRow < arr.length-1) {
            for (int i = topRow + 1; i < arr.length; i++) {
                if(getFirstNot0(arr,i) == getFirstNot0(arr,topRow))
                    subtractRow(arr,topRow,i);
            }
            normalize(arr);
            topRow++;
        }
        if(!stillPossible(arr))
            return null;
        int index = arr[0].length-2;
        while(topRow!=0){
            normalize(arr,index);
            for (int i = topRow-1; i >= 0;i--){
                int first = getFirstNot0(arr,topRow);
                if(arr[i][first] != 0)
                    subtractRow(arr,topRow,i); //i = to Subtract from

            }
            normalize(arr);
            index--;
            topRow--;
        }
        normalize(arr);

        if(!stillPossible(arr))
            return null;
        double[] ret = new double[arr.length];
        for (int i = 0; i < ret.length; i++)
            ret[i] = arr[i][arr.length];
        return ret;
    }

    private static boolean stillPossible(double[][] arr){
        for (double[] doubles : arr) {
            boolean allZero = true;
            for (int j = 0; j < doubles.length - 1; j++) {
                if (doubles[j] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero && doubles[doubles.length - 1] != 0)
                return false;
        }
        return true;
    }

    private static int getFirstNot0(double[][] arr,int row){
        for (int j = 0; j < arr[row].length; j++)
            if(arr[row][j] != 0)
                return j;
        return Integer.MIN_VALUE;
    }

    private static void subtractRow(double[][] arr,int row0,int toSubFrom){
        for (int i = 0; i < arr[toSubFrom].length; i++) {
            arr[toSubFrom][i] -= arr[row0][i];
        }
    }

    private static void normalize(double[][] arr){
        for (int i = 0; i < arr.length; i++) {
            int firstIndex = 0;
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != 0){
                    firstIndex = j;
                    break;
                }
            }
            if(arr[i][firstIndex] != 1) {
                double div = arr[i][firstIndex];
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = arr[i][j] / div;
                }
            }
        }
    }
    private static void normalize(double[][] arr,int index){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i][index] != 1) {
                double div = arr[i][index];
                if(div == 0)
                    continue;
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = arr[i][j] / div;
                }
            }
        }
    }
}

