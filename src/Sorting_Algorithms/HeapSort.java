package Sorting_Algorithms;

/**
 * Implementation of Heap Sort, which uses the binary heap data structure.
 * The goal is to find the largest value in the array/list and store it at the end.
 * These steps are repeated until every element is sorted
 *
 *
 * @author Wutthichai Laphutama
 */
public abstract class HeapSort {
    private static void maxHeapify(double[] arr, int heapSize, int parent) {
        int largest = parent;
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;

        if (leftChild < heapSize && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        if (rightChild < heapSize && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        if (largest != parent) {
            double temp = arr[parent];
            arr[parent] = arr[largest];
            arr[largest] = temp;

            maxHeapify(arr, heapSize, largest);
        }
    }

    private static void createHeap(double[] arr) {
        int heapLength = arr.length;
        for (int i = heapLength / 2 - 1; i >= 0 ; --i) {
            maxHeapify(arr, heapLength, i);
        }
    }

    public static void heapSort(double[] arr) {
        int heapLength = arr.length;
        createHeap(arr);

        for (int i = heapLength - 1; i >= 0; --i) {
            double temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            maxHeapify(arr, i, 0);
        }
    }

