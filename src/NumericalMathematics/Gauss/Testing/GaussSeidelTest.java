package NumericalMathematics.Gauss.Testing;

import Database.MySqlCon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import static NumericalMathematics.Gauss.GaussSeidel.gaussSeidel;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * class which allows the testing of the GaussJordan Algorithm
 * @author JanFr
 */
public class GaussSeidelTest {
    //for the Gauss Seidel algorithm to work you have to guess a result vector
    public static double[] guess = {1, 1, 1};
    /**
     * converts the sql data to a {m*n} double matrix
     * @param sqlData (ArrayList) data which is converted to matrix
     * @return {m*n} double matrix
     */
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

    /**
     * @param sqlData
     * @return result vector of n*n matrix
     */
    public double[] getRes(ArrayList<String> sqlData) {
        double[] ret = new double[3];
        for (int i = 1; i < sqlData.size(); i++) {
            String[] parts = sqlData.get(i).split(" ");
            ret[i - 1] = Double.parseDouble(parts[parts.length - 1]);
        }
        return ret;
    }

    /**
     * get result vector from database values
     * @param sqlData
     * @return result vector
     */
    public double[] getSol(ArrayList<String> sqlData) {
        String[] parts = sqlData.get(sqlData.size() - 1).split(" ");
        double[] ret = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            ret[i] = Double.parseDouble(parts[i]);
        }
        return ret;
    }

    /**
     * tests if the algorithm is working
     * @param i which matrix to revive from database
     */
    @ParameterizedTest
    @DisplayName("Test Gauss Jordan")
    @ValueSource(ints = {1, 2, 3})
    public void testGS(int i) {
        //retrieves data from database
        ArrayList<String> matrix =
                MySqlCon.query("SELECT x,y,z,res " +
                        "FROM tbl_matrix " +
                        "WHERE matrixID = " + i + ";");

        ArrayList<String> res =
                MySqlCon.query("SELECT x,y,z " +
                        "FROM tbl_matrixRes " +
                        "where matrixID = " + i + ";");

        //saves the results in variables
        double[] sol = getSol(res);
        double[] result = gaussSeidel(convert(matrix), getRes(matrix),1000, guess);

        //checks if result is equal to the saved values
        assertArrayEquals(sol, result, 0.1);

        //pushes the result into the database
        MySqlCon.query("SELECT addGaussJordanTestResult("+ arrayEquals(sol,result,0.1)+","+ i +",1)");
    }

    /**
     * @param arr double[]
     * @param arr1 double[]
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
