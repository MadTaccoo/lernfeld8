package Search.Testing;

import Database.MySqlCon;
import Search.BinarySearch;
import Search.LinearSearch;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Random;

public class BinaryTest {
    static double[] listtoSearchIn;
    static double valueToFind;
    static int index;

    @BeforeAll
    public static void setUp(){
        ArrayList<String> directData = MySqlCon.query("SELECT * FROM tbl_searchTest ORDER BY data");
        assert directData != null;
        directData.remove(0);
        listtoSearchIn = directData.stream().mapToDouble(Double::parseDouble).toArray();
        index = 10;
        valueToFind = listtoSearchIn[index];
        System.out.println("..");
    }

    @RepeatedTest(10)
    @DisplayName("Test Binary Search")
    public void testBinarySearch(){
        Assertions.assertEquals(index,BinarySearch.binarySearch(listtoSearchIn,valueToFind));
        Assertions.assertEquals(index,BinarySearch.binarySearchR(listtoSearchIn,valueToFind));
    }

    @RepeatedTest(10)
    @DisplayName("Test Linear Search")
    public void testLinearSearch(){
        Assertions.assertEquals(index,LinearSearch.searchTarget(listtoSearchIn,valueToFind));
    }
}
