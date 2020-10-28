package Sorting_Algorithms;

/**
 * Implementation of the Bogo Sort,
 * where the array is shuffled at random until the array are in order
 *
 * Time Complexity of Bogo Sort:
 * unbounded, since the shuffling is randomized; Best performance would be O(n),
 * assuming the array is already sorted
 *
 * @author Wutthichai Laphutama
 */
public abstract class BogoSort {
    /**
     * Runs as long as array is not sorted yet
     * @param arr (double[] arr) array that is going to be sorted
     */
    public static void bogoSort(double[] arr) {
        while (!isSorted(arr)) {
            shuffleAndSwap(arr);
        }
    }

    /**
     * Step through array, while shuffling and swapping its elements
     * @param arr array of type double
     */
    public static void shuffleAndSwap(double[] arr) {
        for (int r = arr.length - 1; r > 0; --r) {
            int i = (int) (Math.random() * r);
            double temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }
    }

    /**
     * Check if given array is sorted
     * @param arr array of type double
     * @return false if value at index i is smaller than the preceding index i-1
     * otherwise return true
     */
    public static boolean isSorted(double[] arr){
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }
}
