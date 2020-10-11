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

    public static void main(String[] args) {
        double[] testArr = new double[]{2.3, 2, 6, 5.4, 7.8, 80, 4.5, 9};

        System.out.println("Unsorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }

        insertionSort(testArr);

        System.out.println("\nSorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }
    }
}
