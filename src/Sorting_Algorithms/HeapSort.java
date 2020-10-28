package Sorting_Algorithms;

/**
 * Implementation of Heap Sort, which uses the binary heap data structure.
 * The goal is to find the largest value in the array/list and store it at the end.
 * These steps are repeated until every element is sorted
 *
 * Complexity of Heap Sort:
 * Creating the Heap takes O(n) calls of the maxHeapify-function.
 * The maxHeapify-function in itself takes O(log n) times. Therefore
 * the total complexity is O(n log n).
 *
 * @author Wutthichai Laphutama
 */
public abstract class HeapSort {
    /**
     * When the root does not meet the heap property (root is larger than its children or
     * does not have children), swap until the criteria is met.
     * @param arr (double[]) array, that is going to be sorted
     * @param heapSize (int) size of the heap
     * @param parent (int) parent node
     */
    private static void maxHeapify(double[] arr, int heapSize, int parent) {
        /* Searching for the largest value in the parent node, left child and right child */
        int largest = parent;
        int leftChild = 2 * parent + 1; /* to access the left child from the parent node*/
        int rightChild = 2 * parent + 2; /* to access the right child from the parent node*/


        if (leftChild < heapSize && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        if (rightChild < heapSize && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        /* If root is not the largest value, swap until it is the largest value */
        if (largest != parent) {
            double temp = arr[parent];
            arr[parent] = arr[largest];
            arr[largest] = temp;

            maxHeapify(arr, heapSize, largest);
        }
    }

    /**
     * Creation of the heap
     * @param arr (double[]) array, that is going to be sorted
     */
    private static void createHeap(double[] arr) {
        int heapLength = arr.length;
        for (int i = heapLength / 2 - 1; i >= 0; --i) {
            maxHeapify(arr, heapLength, i);
        }
    }

    /**
     * Creates a heap and then sorts all elements of
     * the array
     * @param arr (double[]) array, that is going to be sorted
     */
    public static void heapSort(double[] arr) {
        int heapLength = arr.length;
        createHeap(arr);

        /* Stores the largest element at the end and decreases the range of values
        int the heap by one */
        for (int i = heapLength - 1; i >= 0; --i) {
            double temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            maxHeapify(arr, i, 0);
        }
    }
}