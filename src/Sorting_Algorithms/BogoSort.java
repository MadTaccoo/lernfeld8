package Sorting_Algorithms;

public class BogoSort {
    static void bogoSort(double[] arr) {
        while (!isSorted(arr)) {
            shuffleAndSwap(arr);
        }
    }

    static void shuffleAndSwap(double[] arr) {
        for (int r = arr.length - 1; r > 0; --r) {
            int i = (int) (Math.random() * r);
            double temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }
    }

    static boolean isSorted(double[] arr){
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        double[] testArr = new double[]{2.3, 2, 6, 5.4, 7.8, 80, 4.5, 9};

        System.out.println("Unsorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }

        bogoSort(testArr);

        System.out.println("\nSorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }
    }
}
