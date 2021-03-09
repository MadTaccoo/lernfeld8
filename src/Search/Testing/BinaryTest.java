package Search.Testing;

import Database.MySqlCon;
import Search.BinarySearch;
import Search.LinearSearch;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;

public class BinaryTest {
    static double[] listtoSearchIn;
    static double[] valuesToFind;
    static int[] indexes;

    @BeforeAll
    public static void setUp(){

        ArrayList<String> directData = MySqlCon.query(
                "SELECT * FROM tbl_searchTest ORDER BY data");

        ArrayList<String> results = MySqlCon.query(
                "SELECT data FROM tbl_searchResults");

        ArrayList<String> indexData = MySqlCon.query(
                "SELECT indexVal FROM tbl_searchResults");

        assert directData != null;
        assert results != null;
        assert indexData != null;

        directData.remove(0);
        results.remove(0);
        indexData.remove(0);

        listtoSearchIn = directData.stream().mapToDouble(Double::parseDouble).toArray();
        valuesToFind = results.stream().mapToDouble(Double::parseDouble).toArray();
        indexes = indexData.stream().mapToInt(Integer::parseInt).toArray();

        System.out.println("..");
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    @DisplayName("Test Binary Search")
    public void testBinarySearch(int i){
        Assertions.assertEquals(indexes[i],BinarySearch.binarySearch(listtoSearchIn,valuesToFind[i]));
        Assertions.assertEquals(indexes[i],BinarySearch.binarySearchR(listtoSearchIn,valuesToFind[i]));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    @DisplayName("Test Linear Search")
    public void testLinearSearch(int i){
        Assertions.assertEquals(indexes[i],LinearSearch.searchTarget(listtoSearchIn,valuesToFind[i]));
    }
}
