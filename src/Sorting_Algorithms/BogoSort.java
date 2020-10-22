package Sorting_Algorithms;

/**
 * Implementation of the Bogo Sort,
 * where the array is shuffled at random until the array are in order
 * @author Wutthichai Laphutama
 */
public abstract class BogoSort {
    public static void bogoSort(double[] arr) {
        /**
         * Checks whether array is sorted with isSorted-function
         * if not call shuffleAndSwap
         * @param arr array of type double, which needs to be sorted
         */
        while (!isSorted(arr)) {
            shuffleAndSwap(arr);
        }
    }

    public static void shuffleAndSwap(double[] arr) {
        /**
         * Step through array, while shuffling and swapping its elements
         * @param arr array of type double
         */
        for (int r = arr.length - 1; r > 0; --r) {
            int i = (int) (Math.random() * r);
            double temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }
    }

    public static boolean isSorted(double[] arr){
        /**
         * Check if given array is sorted
         * @param arr array of type double
         * @return false if value at index i is smaller than the preceding index i-1
         * otherwise return true
         */
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }
}
