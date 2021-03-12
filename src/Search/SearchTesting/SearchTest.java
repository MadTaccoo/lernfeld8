package Search.SearchTesting;

import Database.MySqlCon;
import Search.BinarySearch;
import Search.LinearSearch;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;

/**
 * @author JanFr
 * class which allows the test of the Binary/Linear search
 */
public class SearchTest {
    /**
     * listToSearchIn stores the given double values
     * valuesToFind stores the values the algorithm is supposed to find
     * indexes stores the indexes which the algorithm should return
     */
    static String version = "1.0";
    static double[] listToSearchIn;
    static double[] valuesToFind;
    static int[] indexes;

    /**
     * retrieves and processes data needed for the algorithm
     * gets executed once before the tests
     */
    @BeforeAll
    public static void setUp(){
        //gets data from the database
        ArrayList<String> directData = MySqlCon.query("SELECT * FROM tbl_searchTest ORDER BY data");
        ArrayList<String> results = MySqlCon.query("SELECT data FROM tbl_searchResults");
        ArrayList<String> indexData = MySqlCon.query("SELECT indexVal FROM tbl_searchResults");

        //asserts the lists are not null
        assert directData != null;
        assert results != null;
        assert indexData != null;

        //delete the first row which contain the header and is not needed
        directData.remove(0);
        results.remove(0);
        indexData.remove(0);

        //converts the arraylists to double arrays
        listToSearchIn = directData.stream().mapToDouble(Double::parseDouble).toArray();
        valuesToFind = results.stream().mapToDouble(Double::parseDouble).toArray();
        indexes = indexData.stream().mapToInt(Integer::parseInt).toArray();
    }

    /**
     * Tests the recursive binary search Algorithm and pushes
     * the result to the database which then determines if it is correct
     * @param i index of the number which you want to find
     */
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    @DisplayName("Test Binary Search")
    public void testBinarySearch(int i){
        //get the index of wanted number
        int index1 = BinarySearch.binarySearchR(listToSearchIn,valuesToFind[i]);
        Assertions.assertEquals(indexes[i],index1);
        //pushes the result to the database
        MySqlCon.query("SELECT addSearchTestRes(0,"+ valuesToFind[i] +","+ index1 +","+ indexes[i] +","+version+");");
    }

    /**
     * Tests the linear search Algorithm and pushes
     * the result to the database which then determines if it is correct
     * @param i index of the number which you want to find
     */
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3})
    @DisplayName("Test Linear Search")
    public void testLinearSearch(int i){
        //get the index of wanted number
        int index = LinearSearch.searchTarget(listToSearchIn,valuesToFind[i]);
        Assertions.assertEquals(indexes[i],index);
        //pushes the result to the database
        MySqlCon.query("SELECT addSearchTestRes(1,"+ valuesToFind[i] +","+ index +","+ indexes[i] +","+version+");");
    }
}
