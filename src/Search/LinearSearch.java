package Search;

/**
 * Implementation of the Linear Search, which searches
 * for a given element in an array
 * @author Wutthichai Laphutama
 */
public class LinearSearch {
    public static int searchTarget(double[] arr, double target){
        /**
         * @param arr inital array of type double
         * @param target double value that needs to be found
         * @return location of the target otherwise @return -1
         * if target is not in the array
         */
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == target)
                return i;
        }
        return -1;
    }
}
