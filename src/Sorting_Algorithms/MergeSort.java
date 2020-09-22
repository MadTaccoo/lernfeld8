package Sorting_Algorithms;

public abstract class MergeSort {
    public static void mergeSort(double[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(double[] arr, int start, int end) {
        if (start <end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(double[] arr, int start, int mid, int end) {
        int size1 = mid - start + 1;
        int size2 = end - mid;

        double[] left = new double[size1];
        double[] right = new double[size2];

        for (int i = 0; i < size1; ++i) {
            left[i] = arr[start + i];
        }

        for (int j = 0; j < size2; ++j) {
            right[j] = arr[mid + j + 1];
        }

        int i = 0;
        int j = 0;
        int k = start;

        while (i < size1 && j < size2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                ++i;
            } else {
                arr[k] = right[j];
                ++j;
            }
            ++k;
        }

        while (i < size1) {
            arr[k] = left[i];
            ++i;
            ++k;
        }

        while (j < size2) {
            arr[k] = right[j];
            ++j;
            ++k;
        }
    }

    private static void main(String[] args) {
        double[] testArr = new double[]{2.3, 2, 6, 5.4, 7.8, 80, 4.5, 9};

        System.out.println("Unsorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }

        mergeSort(testArr);

        System.out.println("\nSorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }
    }
}
