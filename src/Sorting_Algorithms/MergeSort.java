package Sorting_Algorithms;

/**
 * Implementation of the Merge Sort, where you divide an unsorted list/array
 * into sublists, each containing one element.
 *
 * Then repeatedly merge the sublists into new sorted sublists until only one
 * sublist is remaining. As a result, you then get the sorted list.
 *
 * Complexity of Merge sort:
 * Division: It takes O(1) to divide the problem into two parts
 *
 * Solving the sublists: Two equally large sublists are created. Each part
 * takes T(n/2) time to solve. Thus solving the sublists takes 2T(n/2) time in total
 *
 * Merging the sublists: This step takes O(n) time, since the merging does a number of operations
 * for each element in the list/array and n is the size of the list.
 *
 * Thus the runtime is T(n) = 2T(n/2) + O(n).
 * By using the master theorem we then get a time complexity of O(n log n)
 *
 * @author Wutthichai Laphutama
 */
public abstract class MergeSort {
    /**
     * Helper function in order to recursively call mergeSort-function
     * @param arr array of type double
     */
    public static void mergeSort(double[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * Divide the array into subarray from
     * arr[start...mid] and from arr[mid + 1...end] by recursively calling mergeSort()
     * Then merges the subarray into one sorted subarray by calling merge()
     * @param arr array of type double
     * @param start starting position of type int in the array
     * @param end last position of type int in the array
     */
    private static void mergeSort(double[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    /**
     * Merges the subarrays into sorted subarray
     * @param arr array of type double
     * @param start starting position of type int in the array
     * @param mid center position of type int in the array
     * @param end last position of type int in the array
     */
    private static void merge(double[] arr, int start, int mid, int end) {
        /* Creating the sizes of the subarrays*/
        int size1 = mid - start + 1;
        int size2 = end - mid;

        /* Creating subarrays */
        double[] left = new double[size1];
        double[] right = new double[size2];

        /* Copying the elements of the initial array into the subarrays*/
        for (int i = 0; i < size1; ++i) {
            left[i] = arr[start + i];
        }
        for (int j = 0; j < size2; ++j) {
            right[j] = arr[mid + j + 1];
        }

        /* Indices of the subarrays left and right */
        int i = 0;
        int j = 0;

        /* Index of the merged subarray*/
        int k = start;

        /* This is where the merging happens */
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

        /* If there are elements remaining in 'left' copy them into the merged subarray */
        while (i < size1) {
            arr[k] = left[i];
            ++i;
            ++k;
        }

        /* If there are elements remaining in 'right' copy them into the merged subarray */
        while (j < size2) {
            arr[k] = right[j];
            ++j;
            ++k;
        }
    }
}
