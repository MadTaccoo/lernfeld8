package Euclidean_Algorithm.Euclidean_Algorithm_Test;

import Database.MySqlCon;
import Euclidean_Algorithm.EuclideanAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author JanFr
 * File to test the different sorting algorithms
 */
public class EuclideanAlgorithmTest {
    //greatest common divisor of given tupels
    private final int[] gct = {21, 6, 57};
    //values to check
    private final int[][] values = {
            {1071, 1029},
            {270, 192},
            {285, 741}
    };

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
        Assertions.assertEquals(val,result);
        MySqlCon.query("SELECT addEuclidResult("+ (val==result) +",1)");
    }

    /**
     * tests the recursive version of the euclidean Algorithm
     * @param x get index of values to check
     */
    @ParameterizedTest
    @DisplayName("Test Euclidean Recursive Version")
    @ValueSource(ints = {0, 1, 2})
    public void test2(int x) {
        int val = EuclideanAlgorithm.euclidRec(values[x][0], values[x][1]);
        int result = gct[x];
        Assertions.assertEquals(val,result);
        MySqlCon.query("SELECT addEuclidResult("+ (val==result) +",0)");    }
}
