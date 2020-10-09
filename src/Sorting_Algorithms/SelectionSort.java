package Sorting_Algorithms;

public abstract class SelectionSort {
    public static void main(String[] args) {
        double[] ls = {412,46231,2,253532,1231,3};
        SelectionSort(ls);
        for (double l : ls) {
            System.out.println(l);
        }
    }

    public static double[] SelectionSort(double[] arr){
        return SelectionSortRec(arr,0);
    }

    public static double[] SelectionSortRec(double[] arr,int start){
        if(start>=arr.length)
            return arr;
        int indexMin = start;
        for (int i = start; i < arr.length; i++)
            if(arr[i]<arr[indexMin])
                indexMin = i;
        swap(arr,start,indexMin);
        return SelectionSortRec(arr,++start);
    }

    private static void swap(double[] doubles, int x, int y) {
        double doubleX = doubles[x];
        double doubleY = doubles[y];
        doubles[x] = doubleY;
        doubles[y] = doubleX;
    }
}
