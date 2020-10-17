package Sorting_Algorithms;

/**
 * An implementation of a commonly known version of
 * the Bubble Sort and an optimized version of The Bubble Sort
 * @author Wutthichai Laphutama
 */
public abstract class BubbleSort {
    public static void bubbleSort(double[] arr) {
        /**
         * The initial array is stepped through from left to right.
         * In each step the current element is compared with an adjacent
         * element and then swapped if the criteria, in which they are to be sorted, is wrong.
         *
         */
        for(int i = arr.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void bubbleSortOptimized(double[] arr) {
        /**
         * Works the same as the previous version, but in case that some
         * or all elements in the given array are already in their final position, i.e.
         * already sorted, they do not need to be checked again.
         *
         * @param arr initial unsorted array, which is going to be sorted
         */
        int n = arr.length;
        boolean swapped;    /* checks whether elements in the array need to be swapped */
        do {
            swapped = false;
            for (int i = 0; i < n - 1; ++i) {   /* steps through the elements, which are to be sorted */
                if (arr[i] > arr[i+1]) {
                    double temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    swapped = true;
                }
            }
            n -= 1;
        } while (swapped);
    }


    /* For testing purposes */
    private static void main(String[] args) {

        double[] testArr = new double[]{2.3, 2, 6, 5.4, 7.8, 80, 4.5, 9};

        System.out.println("Unsorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }

        bubbleSort(testArr);

        System.out.println("\nSorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }

        double[] testArr2 = new double[]{2.3, 2, 6, 5.4, 7.8, 80, 4.5, 9, 10, 42.3, 4, 2};

        System.out.println("\nUnsorted array: ");
        for (double item : testArr2) {
            System.out.print(item + " ");
        }

        bubbleSortOptimized(testArr2);

        System.out.println("\nSorted array optimized: ");
        for (double item : testArr2) {
            System.out.print(item + " ");
        }
    }
}
