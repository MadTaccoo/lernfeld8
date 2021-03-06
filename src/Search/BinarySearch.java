package Search;

import java.util.ArrayList;

/**
 * @author Jan-Frederik
 */
public class BinarySearch {
    /*List of steps take by the algorithm*/
    public static ArrayList<Integer> ls = new ArrayList<>();

    /**
     * simple implementation of binary search
     * @param arr sorted array
     * @param target value of target
     * @return index of target
     */
    public static int binarySearch(double[] arr,double target) {
        /*clears step list*/
        ls.clear();
        /*if the arr isnt sorted it returns -1*/
        if(!isSorted(arr)) {
            System.out.println("--");
            return -1;
        }
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // Check if x is present at mid
            if (arr[m] == target)
                return m;
            // If x greater, ignore left half
            if (arr[m] < target)
                l = m + 1;
                // If x is smaller, ignore right half
            else
                r = m - 1;
        }
        // if we reach here, then element was
        // not present
        return -1;
    }

    /**
     * implementation of binarySearch recursive
     * @param arr sorted array
     * @param l from
     * @param r to
     * @param x target value
     * @return index of value
     */
    private static int binarySearchRec(double[] arr, int l, int r, double x) {
        /*return -1 of the index is out if bounds*/
        if (!(r >= l))
            return -1;
        /*get the new middle index*/
        int mid = l + (r - l) / 2;
        /*returns the index if the target is reached*/
        if (arr[mid] == x)
            return mid;
        ls.add(mid);
        /*recursively calls the following half of the array which has to be searched*/
        return binarySearchRec(arr,(arr[mid] > x) ? l : mid+1,(arr[mid]>x) ? mid-1 : r , x);
    }

    /**
     * helper function to call the binarySearchRec
     * @param arr sorted array
     * @param target target value
     * @return index of target
     */
    public static int binarySearchR(double[] arr,double target) {
        ls.clear();
        if(!isSorted(arr))
            return Integer.MIN_VALUE;
        return binarySearchRec(arr,0,arr.length-1,target);
    }

    /**
     * function to check if array is sorted
     * @param arr array to check
     * @return if the array is sorted true else false
     */
    public static boolean isSorted(double[] arr){
        for (int i = 0; i < arr.length-1; i++)
            if(arr[i]>arr[i+1])
                return false;
        return true;
    }
}
