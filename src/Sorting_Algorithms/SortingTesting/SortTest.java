package Sorting_Algorithms.SortingTesting;

import Database.MySqlCon;
import Sorting_Algorithms.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author JanFr
 * File to test the different sorting algorithms
 */
public class SortTest {
    /**
     * retrieving data from database and saving the result in a ArrayList
     */
    private ArrayList<String> rawData = MySqlCon.query("SELECT * FROM tbl_SortingRawData;");
    private ArrayList<String> resData = MySqlCon.query("SELECT * FROM tbl_SortingManualResult;");
    private String version = "1.0";

    /**
     * @param whichAlgorithm (int) gives the function a number between 1 and 7
     *                       selects the different Sorting algorithm we dont test number 5 because
     *                       its mapped to Bogosort which takes way to long with that many values
     */
    @ParameterizedTest
    @DisplayName("Check if Sorting works")
    @ValueSource(ints = {1, 2, 3, 4, 6, 7})
    public void test(int whichAlgorithm) {
        //deletes the header row of the dataset
        rawData.remove(0);
        resData.remove(0);
        //converts arraylists to arrays
        double[] rawArr = rawData.stream().mapToDouble(Double::parseDouble).toArray();
        double[] resArr = resData.stream().mapToDouble(Double::parseDouble).toArray();
        //used to measure time needed for execution
        long from = 0, to = 0;
        //saves starting time
        from = System.nanoTime();
        //selects wanted sorting algorithm
        switch (whichAlgorithm) {
            case 1:
                InsertionSort.insertionSort(rawArr);
                break;
            case 2:
                QuickSort.quickSort(rawArr);
                break;
            case 3:
                BubbleSort.bubbleSortOptimized(rawArr);
                break;
            case 4:
                MergeSort.mergeSort(rawArr);
                break;
            case 5:
                BogoSort.bogoSort(rawArr);
                break;
            case 6:
                SelectionSort.SelectionSort(rawArr);
                break;
            case 7:
                HeapSort.heapSort(rawArr);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + whichAlgorithm);
        }
        //saves end time
        to = System.nanoTime();
        //pushes result to the database
        MySqlCon.query("SELECT addSortingRes(" + Arrays.equals(rawArr, resArr) + "," + whichAlgorithm + ",\'" + (to - from) + "\'," + version + ");");
        //asserting if result is correct
        assertArrayEquals(rawArr, resArr, 0.1);
    }
}
