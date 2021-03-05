package NumericalMathematics.Gauss.Testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static NumericalMathematics.Gauss.GaussSeidel.gaussSeidel;
import static NumericalMathematics.Gauss.Gauss_Jordan.gaussJ;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GaussSeidelTest {
    public static double[][][] matrices = {
            {
                    {1, 2, 3},
                    {1, 3, 4},
                    {1, 4, 6}
            },
            {
                    {2.3, 0, 1},
                    {1, 5.8, 0},
                    {0, 1, 2.3}
            },
            {
                    {3, -2, 4},
                    {5, 2, -3},
                    {3, 4, -7}
            }
    };

    public static double[][] matrixResV = {
            {-5, -7, -8},
            {-6.6, -13.6, -6.6},
            {6, -4, -2},
    };

    public static double[][] matricesRes = {
            {-2, -3, 1},
            {-2, -2, -2},
            {8, -115, -62},
    };

    public static double[] guess = {1,1,1};

    @ParameterizedTest
    @DisplayName("Test Gauss Jordan")
    @ValueSource(ints = {0, 1, 2})
    public void testGS(int i) {
        assertArrayEquals( matricesRes[i], gaussSeidel(matrices[i],matrixResV[i],1000, guess), 0.1);
    }
}
