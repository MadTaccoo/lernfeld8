package Sorting_Algorithms.SortingTesting;

import Database.MySqlCon;
import Sorting_Algorithms.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest {
    ArrayList<String> rawData = MySqlCon.query("SELECT * FROM tbl_rawData;");
    ArrayList<String> resData = MySqlCon.query("SELECT * FROM tbl_resultsSortingManual;");

    @ParameterizedTest
    @DisplayName("Check if Sorting works")
    @ValueSource(ints = {1,2,3,4,6,7})
    public void test(int whichAlgorithm) {
        double[] rawArr = new double[rawData.size()];
        double[] resArr = new double[resData.size()];
        for (int j = 1; j < rawData.size(); j++) {
            rawArr[j] = Double.parseDouble(rawData.get(j));
            resArr[j] = Double.parseDouble(resData.get(j));
        }
        long from = 0, to = 0;
        switch (whichAlgorithm) {
            case 1:
                from = System.nanoTime();
                //InsertionSort.insertionSort(rawArr);
                to = System.nanoTime();
                break;
            case 2:
                from = System.nanoTime();
                //QuickSort.quickSort(rawArr);
                to = System.nanoTime();
                break;
            case 3:
                from = System.nanoTime();
                //BubbleSort.bubbleSortOptimized(rawArr);
                to = System.nanoTime();
                break;
            case 4:
                from = System.nanoTime();
                //MergeSort.mergeSort(rawArr);
                to = System.nanoTime();
                break;
            case 5:
                from = System.nanoTime();
                BogoSort.bogoSort(rawArr);
                to = System.nanoTime();
                break;
            case 6:
                from = System.nanoTime();
                SelectionSort.SelectionSort(rawArr);
                to = System.nanoTime();
                break;
            case 7:
                from = System.nanoTime();
                HeapSort.heapSort(rawArr);
                to = System.nanoTime();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + whichAlgorithm);
        }
        //MySqlCon.query("SELECT addSortingRes(" + Arrays.equals(rawArr, resArr) + "," + i + ",\'" + (to - from) + "\');");
        assertArrayEquals(rawArr, resArr, 0.1);

    }
}
