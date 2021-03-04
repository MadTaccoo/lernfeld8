package NumericalMathematics.Gauss;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import Database.MySqlCon;
import Sorting_Algorithms.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;

import static NumericalMathematics.Gauss.GaussSeidel.gaussSeidel;
import static org.junit.jupiter.api.Assertions.*;
import static NumericalMathematics.Gauss.Gauss_Jordan.gaussJ;

public class GaussJordanTest {
    public static double[][][] matrizes = {
            {
                    {1, 2, 3, -5},
                    {1, 3, 4, -7},
                    {1, 4, 6, -8}
            }, {
                    {2.3, 0, 1, -6.6},
                    {1, 5.8, 0, -13.6},
                    {0, 1, 2.3, -6.6}
            }, {
                    {3, -2, 4, 6},
                    {5, 2, -3, -4},
                    {3, 4, -7, -2}
            }
    };

    public static double[][] matrizesRes = {
            {-2, -3, 1},
            {-2, -2, -2},
            {8, -115, -62},
    };

    @ParameterizedTest
    @DisplayName("Test Gauss Jordan")
    @ValueSource(ints = {0,1,2})
    public void testGJ(int i) {
        assertArrayEquals(gaussJ(matrizes[i]),matrizesRes[i],0.1);
    }
}
