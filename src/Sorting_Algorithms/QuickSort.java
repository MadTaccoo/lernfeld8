package Sorting_Algorithms;

import java.util.Arrays;

public abstract class QuickSort {
    public static void quickSort(double[] list, int from, int to) {
        int i1 = from;
        int i2 = to;
        double pivot = list[(i1 + i2) / 2];
        while (i1 <= i2) {
            while (list[i1] < pivot)
                i1++;
            while (list[i2] > pivot)
                i2--;
            if (i1 <= i2) {
                swap(list, i1, i2);
                i1++;
                i2--;
            }
        }
        if (from < i2)
            quickSort(list, from, i2);
        if (to > i1)
            quickSort(list, i1, to);
    }

    private static void swap(double[] doubles, int x, int y) {
        double doubleX = doubles[x];
        double doubleY = doubles[y];
        doubles[x] = doubleY;
        doubles[y] = doubleX;
    }

    public static boolean isSortedOwn(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]){
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

        quickSort(testArr,0, testArr.length-1);

        System.out.println("\nSorted array: ");
        for (double item : testArr) {
            System.out.print(item + " ");
        }
    }
}
