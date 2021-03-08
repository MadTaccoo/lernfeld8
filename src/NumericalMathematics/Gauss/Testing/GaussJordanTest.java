package NumericalMathematics.Gauss.Testing;

import Database.MySqlCon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static NumericalMathematics.Gauss.Gauss_Jordan.gaussJ;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GaussJordanTest {

    public double[][] convert(ArrayList<String> sqlData){
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

    @ParameterizedTest
    @DisplayName("Test Gauss Jordan")
    @ValueSource(ints = {1,2,3})
    public void testGJ(int i) {
        ArrayList<String> matrix =
                MySqlCon.query(
                "SELECT x,y,z,res " +
                "FROM tbl_matrix " +
                "WHERE matrixID = "+ i +";");

        ArrayList<String> res =
                MySqlCon.query(
                "SELECT x,y,z " +
                "FROM tbl_matrixRes " +
                "where matrixID = "+i+";");
        assertArrayEquals(gaussJ(convert(matrix)),convert(res)[0],0.1);
    }
}
