package Sorting_Algorithms;

public abstract class InsertionSort {
    /**
     * Insertion Sort
     * @param sortieren
     */
    public static void insertionSort(double[] sortieren) {
        double temp;
        for (int i = 1; i < sortieren.length; i++) {
            //saves the value at index i E i<=1
            temp = sortieren[i];
            int j = i;
            //shifts the array. To place the current value at the correct place
            while (j > 0 && sortieren[j - 1] > temp) {
                sortieren[j] = sortieren[j - 1];
                j--;
            }
            sortieren[j] = temp;
        }
    }
}
