package Sorting_Algorithms;

public abstract class BubbleSort {

    public static void bubbleSort(double[] arr) {
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
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; ++i) {
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


    public static void main(String[] args) {

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
