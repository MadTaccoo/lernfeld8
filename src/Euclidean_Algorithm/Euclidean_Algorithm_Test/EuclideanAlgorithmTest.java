package Euclidean_Algorithm.Euclidean_Algorithm_Test;

import Euclidean_Algorithm.EuclideanAlgorithm;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EuclideanAlgorithmTest {
    private int[] ggt = {21, 6, 57};
    private int[][] values = {
            {1071, 1029},
            {270, 192},
            {285, 741}
    };


    @ParameterizedTest
    @DisplayName("Test Euclidean")
    @ValueSource(ints = {0, 1, 2})
    public void test(int x) {
        Assertions.assertEquals(EuclideanAlgorithm.euclid(values[x][0], values[x][1]), ggt[x]);
    }

    @ParameterizedTest
    @DisplayName("Test Euclidean Recursive Version")
    @ValueSource(ints = {0, 1, 2})
    public void test2(int x) {
        Assertions.assertEquals(EuclideanAlgorithm.euclidRec(values[x][0], values[x][1]), ggt[x]);
    }
}
