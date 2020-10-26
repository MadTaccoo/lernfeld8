package NumericalMathematics.Gauss;

public abstract class GaussSeidel
{
    public static double[] gaussseidel(double[][] matrix,double[] resVector,int precision,double[] guess){
        if(!converges(matrix))
            System.out.println("Could not converge!");
        int n = matrix.length;
        for (int t = 0;t<precision;t++){
            for (int i = 0; i < n; i++) {
                double temp = 0;
                for (int j = 0; j < n; j++)
                    if(j!=i)
                        temp+=matrix[i][j]*guess[j];
                guess[i] = (1/matrix[i][i])*(resVector[i]-temp);
            }
        }
        return guess;
    }

    public static boolean converges(double[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            double middle = Math.abs(matrix[i][i]);
            double rowVal = 0;
            for (int j = 0; j < matrix.length; j++) {
                if(j!=i){
                    rowVal+=Math.abs(matrix[i][j]);
                }
            }
            if(rowVal>=middle){
                return false;
            }
        }
        return true;
    }
}
