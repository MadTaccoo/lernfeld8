package Search;

import java.util.ArrayList;

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
        if(!isSorted(arr))
            return -1;
        /*the first index to check is the middle of the array*/
        int index = arr.length/2;
        /*gets the item at the selected index*/
        double itemAtIndex = arr[index];
        int count = 0;
        /*while the itemAtIndex is not the one we want*/
        while (itemAtIndex != target){
            /*in case the count is outside of the array length*/
            if(count >=arr.length)
                return -1;
            /*if the item is smaller than the target the index is added by the index divided by 2
            * else is is subtracted*/
            if(itemAtIndex <= target)
                index += index/2;
            else if(itemAtIndex > target)
                index -= index/2;
            /*step gets added to the arraylist for the steps*/
            ls.add(index);
            itemAtIndex = arr[index-1];
            count++;
        }
        return index-1;
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
