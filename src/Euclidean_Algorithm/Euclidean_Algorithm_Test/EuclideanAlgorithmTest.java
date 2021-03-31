package Euclidean_Algorithm.Euclidean_Algorithm_Test;

import Database.MySqlCon;
import Euclidean_Algorithm.EuclideanAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author JanFr
 * File to test the different sorting algorithms
 */
public class EuclideanAlgorithmTest {
    //greatest common divisor of given tupels
    private static int[] gct;
    //values to check
    private static int[][] values;
    private String version = "1.0";
    @BeforeAll
    public static void setUp() {
        //retrieves data from database
        ArrayList<String> gctLs = MySqlCon.query("SELECT result FROM tbl_euclideanManualResult");
        ArrayList<String> valuesLs = MySqlCon.query("SELECT val1,val2 FROM tbl_euclideanRawData");

        //removes header
        valuesLs.remove(0);
        gctLs.remove(0);

        //converts string values to integer values
        gct = gctLs.stream().mapToInt(Integer::parseInt).toArray();
        values = new int[valuesLs.size()][2];
        for (int i = 0; i < valuesLs.size(); i++) {
            values[i] = Arrays.stream(valuesLs.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    /**
     * tests the euclidean Algorithm
     * @param x get index of values to check
     */
    @ParameterizedTest
    @DisplayName("Test Euclidean")
    @ValueSource(ints = {0, 1, 2})
    public void test(int x) {
        int val = EuclideanAlgorithm.euclid(values[x][0], values[x][1]);
        int result = gct[x];
        Assertions.assertEquals(val, result);
        MySqlCon.query("SELECT addEuclidResult(" + (val == result) + ",1,"+version+")");
    }

    /**
     * tests the recursive version of the euclidean Algorithm
     *
     * @param x get index of values to check
     */
    @ParameterizedTest
    @DisplayName("Test Euclidean Recursive Version")
    @ValueSource(ints = {0, 1, 2})
    public void test2(int x) {
        int val = EuclideanAlgorithm.euclidRec(values[x][0], values[x][1]);
        int result = gct[x];
        Assertions.assertEquals(val, result);
        MySqlCon.query("SELECT addEuclidResult(" + (val == result) + ",0,"+version+")");
    }
}
