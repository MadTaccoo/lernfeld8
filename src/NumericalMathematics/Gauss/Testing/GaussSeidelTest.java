package NumericalMathematics.Gauss.Testing;

import Database.MySqlCon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public static double[] guess = {1, 1, 1};

    public double[][] convert(ArrayList<String> sqlData) {
        if (sqlData.size() == 0)
            return null;
        double[][] ret = new double[sqlData.size() - 1][sqlData.get(0).split(" ").length - 1];
        for (int i = 1; i < sqlData.size(); i++) {
            String[] part = sqlData.get(i).split(" ");
            for (int j = 0; j < part.length - 1; j++) {
                ret[i - 1][j] = Double.parseDouble(part[j]);
            }
        }
        return ret;
    }

    public double[] getRes(ArrayList<String> sqlData) {
        double[] ret = new double[3];
        for (int i = 1; i < sqlData.size(); i++) {
            String[] parts = sqlData.get(i).split(" ");
            ret[i - 1] = Double.parseDouble(parts[parts.length - 1]);
        }
        return ret;
    }

    public double[] getSol(ArrayList<String> sqlData) {
        String[] parts = sqlData.get(sqlData.size() - 1).split(" ");
        double[] ret = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            ret[i] = Double.parseDouble(parts[i]);
        }
        return ret;
    }

    @ParameterizedTest
    @DisplayName("Test Gauss Jordan")
    @ValueSource(ints = {1, 2, 3})
    public void testGS(int i) {
        ArrayList<String> matrix =
                MySqlCon.query("SELECT x,y,z,res " +
                        "FROM tbl_matrix " +
                        "WHERE matrixID = " + i + ";");

        ArrayList<String> res =
                MySqlCon.query("SELECT x,y,z " +
                        "FROM tbl_matrixRes " +
                        "where matrixID = " + i + ";");
        assertArrayEquals(getSol(res), gaussSeidel(convert(matrix), getRes(matrix), 1000, guess), 0.1);
    }
}
