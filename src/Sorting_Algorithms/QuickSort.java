package Sorting_Algorithms;

/**
 * Implementation of Quicksort, which works as follows:
 * 1. Divide
 * We choose a pivot element in an array and divide it into subarrays.
 * The elements smaller than the pivot are placed to its left,
 * while the elements greater than the pivot are placed to its right.
 * 2. Conquer
 * Sorting the subarrays by recursively calling the function
 *
 * Complexity of Quick Sort:
 * Assuming the subarrays are of the same length, it would take 2T(n-1/2) to solve it
 * Since we recursively call the function, each call would take O(n) time.
 * Thus, it would take T(n) = 2T(n-1/2) + O(n) time or O(n log n) by using the master theorem.
 *
 * @author Jan-Frederik
 */
public abstract class QuickSort {

    public static void quickSort(double[] list){
        quickSortRec(list,0,list.length-1);
    }

    /**
     * implementation of quicksort
     * @param list of unsorted doubles
     * @param from start index of subarray
     * @param to end index of subarray
     */
    public static void quickSortRec(double[] list, int from, int to) {
        int i1 = from;
        int i2 = to;
        //gets the pivot that is roughly in the (index)middle
        double pivot = list[(i1 + i2) / 2];
        //moves all values in range i1 to i2 and sorts them around the pivot
        while (i1 <= i2) {
            //iterates to find the closest to i1 which is bigger than the pivot
            while (list[i1] < pivot)
                i1++;
            //iterates to find the closest to i2 which is smaller than the pivot
            while (list[i2] > pivot)
                i2--;
            if (i1 <= i2) {
                swap(list, i1, i2);
                i1++;
                i2--;
            }
        }
        //continues to sort subarray in the same way
        if (from < i2)
            quickSortRec(list, from, i2);
        if (to > i1)
            quickSortRec(list, i1, to);
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

    public static boolean isSortedOwn(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }

}
