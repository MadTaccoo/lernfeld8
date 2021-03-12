package NumericalMathematics.Gauss.Testing;

import Database.MySqlCon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static NumericalMathematics.Gauss.Gauss_Jordan.gaussJ;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * class which allows the testing of the GaussJordan Algorithm
 *
 * @author JanFr
 */
public class GaussJordanTest {
    private String version = "1.0";

    /**
     * converts the sql data to a {m*n} double matrix
     *
     * @param sqlData (ArrayList) data which is converted to matrix
     * @return {m*n} double matrix
     */
    public double[][] convert(ArrayList<String> sqlData) {
        sqlData.remove(0);
        if (sqlData.size() == 0)
            return null;
        double[][] ret = new double[sqlData.size()][sqlData.get(0).split(" ").length];
        for (int i = 0; i < sqlData.size(); i++) {
            String[] part = sqlData.get(i).split(" ");
            for (int j = 0; j < part.length; j++) {
                ret[i][j] = Double.parseDouble(part[j]);
            }
        }
        return ret;
    }

    /**
     * solves the given matrix with the Gauss Jordan Algorithm
     *
     * @param i defines which matrix to retrieve from the database
     */
    @ParameterizedTest
    @DisplayName("Test Gauss Jordan")
    @ValueSource(ints = {1, 2, 3})
    public void testGJ(int i) {
        //retrieves data from database
        ArrayList<String> matrix =
                MySqlCon.query(
                        "SELECT x,y,z,res " +
                                "FROM tbl_matrix " +
                                "WHERE matrixID = " + i + ";");

        ArrayList<String> res =
                MySqlCon.query(
                        "SELECT x,y,z " +
                                "FROM tbl_matrixRes " +
                                "where matrixID = " + i + ";");

        //asserts the lists are not null
        assert matrix != null;
        assert res != null;

        //saves the results in variables
        double[] arr = gaussJ(convert(matrix));
        double[] givenRes = convert(res)[0];

        //checks if result is equal to the saved values
        assertArrayEquals(arr, givenRes, 0.1);

        //pushes the result into the database
        MySqlCon.query("SELECT addGaussJordanTestResult(" + arrayEquals(arr, givenRes, 0.1) + "," + i + ",0," + version + ")");
    }

    /**
     * @param arr     double[]
     * @param arr1    double[]
     * @param epsilon to negate the double inaccuracy
     * @return if the arrays are equal returns true else false
     */
    public boolean arrayEquals(double[] arr, double[] arr1, double epsilon) {
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr1[i]) > epsilon)
                return false;
        }
        return true;
    }
}
