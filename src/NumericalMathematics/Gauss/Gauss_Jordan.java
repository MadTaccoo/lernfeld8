package NumericalMathematics.Gauss;

/**
 * !!! not numerical but easier for access to save it in Numerical/Gauss !!!
 */
public abstract class Gauss_Jordan {
    /** SLE = System of linear Equations
     * takes a SLE and solves it according to the Gauss Jordan algorithm
     * @param arr SLE
     * @return result vector
     */
    public static double[] gaussJ(double[][] arr){
        /*normalizes the SLE*/
        normalize(arr);
        int topRow = 0;
        /*subtracts the topRow from all below that to reach the wanted triangular form*/
        while (topRow < arr.length-1) {
            for (int i = topRow + 1; i < arr.length; i++) {
                if(getFirstNot0(arr,i) == getFirstNot0(arr,topRow))
                    subtractRow(arr,topRow,i);
            }
            normalize(arr);
            topRow++;
        }
        /*checks if it is still possible to solve*/
        if(!stillPossible(arr))
            return null;
        /*subtracts the lowest line 0 0 1 | X from the upper lines to reach the the final solution
        * 1 0 0|X
        * 0 1 0|Y
        * 0 0 1|Z
        * */
        int index = arr[0].length-2;
        while(topRow!=0){
            normalize(arr,index);
            for (int i = topRow-1; i >= 0;i--){
                int first = getFirstNot0(arr,topRow);
                if(arr[i][first] != 0)
                    subtractRow(arr,topRow,i); //i = to Subtract from

            }
            normalize(arr);
            index--;
            topRow--;
        }
        normalize(arr);
        /*creates a double array to return the solution to the SLE*/
        if(!stillPossible(arr))
            return null;
        double[] ret = new double[arr.length];
        for (int i = 0; i < ret.length; i++)
            ret[i] = arr[i][arr.length];
        return ret;
    }

    /**
     * check if one row is false
     * 0 0 0 | 1 for example
     * @param arr SLE
     * @return if its possible to solve
     */
    private static boolean stillPossible(double[][] arr){
        for (double[] doubles : arr) {
            boolean allZero = true;
            for (int j = 0; j < doubles.length - 1; j++) {
                if (doubles[j] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero && doubles[doubles.length - 1] != 0)
                return false;
        }
        return true;
    }

    private static int getFirstNot0(double[][] arr,int row){
        for (int j = 0; j < arr[row].length; j++)
            if(arr[row][j] != 0)
                return j;
        return Integer.MIN_VALUE;
    }

    /**
     *
     * @param arr SLE
     * @param row0
     * @param toSubFrom
     */
    private static void subtractRow(double[][] arr,int row0,int toSubFrom){
        for (int i = 0; i < arr[toSubFrom].length; i++) {
            arr[toSubFrom][i] -= arr[row0][i];
        }
    }

    /**
     * sets the first index which is not 0 to 1 and adjusts the following values accordingly
     * @param arr takes the SLE
     */
    private static void normalize(double[][] arr){
        for (int i = 0; i < arr.length; i++) {
            int firstIndex = 0;
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != 0){
                    firstIndex = j;
                    break;
                }
            }
            if(arr[i][firstIndex] != 1) {
                double div = arr[i][firstIndex];
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = arr[i][j] / div;
                }
            }
        }
    }
    /**
     * sets the selected index to 1 and adjusts the following values accordingly
     * @param arr takes the SLE
     */
    private static void normalize(double[][] arr,int index){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i][index] != 1) {
                double div = arr[i][index];
                if(div == 0)
                    continue;
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = arr[i][j] / div;
                }
            }
        }
    }
}

