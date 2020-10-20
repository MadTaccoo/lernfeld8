package Search;

import java.util.ArrayList;

public class BinarySearch {
    public static ArrayList<Integer> ls = new ArrayList<>();
    //Important sorted Array
    public static int binarySearch(double[] arr,double target) {
        ls.clear();
        if(!isSorted(arr))
            return -1;
        int index = arr.length/2;
        double itemAtIndex = arr[index];
        int count = 0;
        while (itemAtIndex != target){
            if(count >=arr.length)
                return -1;
            if(itemAtIndex <= target)
                index += index/2;
            else if(itemAtIndex > target)
                index -= index/2;
            ls.add(index);
            itemAtIndex = arr[index-1];
            count++;
        }
        return index-1;
    }

    private static int binarySearchRec(double arr[], int l, int r, double x) {
        if (!(r >= l))
            return -1;
        int mid = l + (r - l) / 2;
        if (arr[mid] == x)
            return mid;
        ls.add(mid);
        return binarySearchRec(arr,(arr[mid] > x) ? l : mid+1,(arr[mid]>x) ? mid-1 : r , x);
    }

    public static int binarySearchR(double[] arr,double target) {
        ls.clear();
        if(!isSorted(arr))
            return Integer.MIN_VALUE;
        return binarySearchRec(arr,0,arr.length-1,target);
    }

    public static boolean isSorted(double[] arr){
        for (int i = 0; i < arr.length-1; i++)
            if(arr[i]>arr[i+1])
                return false;
        return true;
    }
}
