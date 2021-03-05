package NumericalMathematics.Gauss;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static NumericalMathematics.Gauss.Gauss_Jordan.gaussJ;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GaussJordanTest {
    public static double[][][] matrices = {
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

    public static double[][] matricesRes = {
            {-2, -3, 1},
            {-2, -2, -2},
            {8, -115, -62},
    };

    @ParameterizedTest
    @DisplayName("Test Gauss Jordan")
    @ValueSource(ints = {0,1,2})
    public void testGJ(int i) {
        assertArrayEquals(gaussJ(matrices[i]),matricesRes[i],0.1);
    }
}
