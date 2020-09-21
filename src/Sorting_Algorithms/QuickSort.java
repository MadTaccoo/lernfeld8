package Sorting_Algorithms;

import java.util.Arrays;

public abstract class QuickSort {
    public static void quickSort(double[] ints, int von, int bis) {
        int i1 = von;
        int i2 = bis;
        //selection of pivot
        //pivot = item rough in the middel of the splitlist
        int teilstelle = (i1 + i2) / 2;

        double teilelement = ints[teilstelle];
        while (i1 <= i2) {
            while (ints[i1] < teilelement) {
                i1++;
            }
            while (ints[i2] > teilelement) {
                i2--;
            }
            if (i1 <= i2) {
                swap(ints, i1, i2);
                i1++;
                i2--;
            }
        }
        if (von < i2) {
            quickSort(ints, von, i2);
        }
        if (bis > i1) {
            quickSort(ints, i1, bis);
        }
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
