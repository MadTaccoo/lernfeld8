package Hashing_algorithm.HashingTest;

import Hashing_algorithm.Hashing;
import Database.MySqlCon;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HashingTest
{

    static String one;
    static String sOne;
    static String sTwo;

    static String solution;


    @BeforeAll
    public static void setUp()
    {
        ArrayList<String> matrix = MySqlCon.query("SELECT data FROM tbl_hashingDataSource;");
        ArrayList<String> sol = MySqlCon.query("SELECT data FROM tbl_hashingTestResults;");
        matrix.remove(0);
        sol.remove(0);

        one = matrix.get(0);
        solution = sol.get(0);

    }

    @Test
    public void testing()
    {

        sOne = Hashing.simpleHash(one);
        sTwo = Hashing.simpleHash(sOne);

        Assertions.assertEquals(solution,one);
        Assertions.assertNotEquals(sOne,sTwo);

        MySqlCon.query("SELECT addHashingRes("+one+","+sOne+","+(solution.equals(one)&&!sOne.equals(sTwo))+");");
    }

}
