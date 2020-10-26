package Sorting_Algorithms;

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
