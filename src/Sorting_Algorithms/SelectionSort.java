package Sorting_Algorithms;

/**
 * @author Jan-Frederik
 */
public abstract class SelectionSort {
    /**
     * help function to execute Selectionsort
     * @param arr
     * @return
     */
    public static double[] SelectionSort(double[] arr){
        return SelectionSortRec(arr,0);
    }

    /**
     *
     * @param arr of unsorted doubles
     * @param start index of iteration start
     * @return partially sorted array and allows recursion to sort the array
     */
    private static double[] SelectionSortRec(double[] arr,int start){
        //in case the start index is equal or higher to the array.length
        if(start>=arr.length)
            return arr;
        //allows to safe the index
        int indexMin = start;
        //iterates from start to arr.length to find the smallest value in the right subarray
        for (int i = start; i < arr.length; i++)
            if(arr[i]<arr[indexMin])
                indexMin = i;
        //swaps values at start and the index of the smallest value within the subarray
        swap(arr,start,indexMin);
        //recursively continues with start +1 subarray
        return SelectionSortRec(arr,++start);
    }

    /**
     *
     * @param doubles array
     * @param x index of a value
     * @param y index of b value
     * the function swaps both indexes
     */
    private static void swap(double[] doubles, int x, int y) {
        double doubleX = doubles[x];
        double doubleY = doubles[y];
        doubles[x] = doubleY;
        doubles[y] = doubleX;
    }
}
