package Sorting_Algorithms;

/**
 *  Implementation of Insertion Sort
 *  The algorithm iterates through an input array and removes one element per
 *  iteration, then searches for the place where it belongs and puts it there.
 *
 *  Complexity of Insertion Sort:
 *  Assuming the input array is sorted in an ascending order, and we want it to sort in
 *  an descending order, each element has to be compared with every other element in the array.
 *  This means that for every element n, we need (n-1) numbers of comparisons.
 *  Therefore the complexity of the insertion sort is n*(n-1) or roughtly O(n^2)
 *
 *  @author Jan-Frederik Reuter
 */
public abstract class InsertionSort {
    /**
     * Insertion Sort
     * @param arr
     */
    public static void insertionSort(double[] arr) {
        double temp;
        for (int i = 1; i < arr.length; i++) {
            //saves the value at index i E i<=1
            temp = arr[i];
            int j = i;
            //shifts the array. To place the current value at the correct place
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
