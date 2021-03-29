package NumericalMathematics.Gauss;

public abstract class GaussSeidel {

    /**
     * @param matrix
     * @param resVector
     * @param precision how many iterations
     * @param guess     vector which is needed to solve, the more accurate the guess the better the algorithm works
     * @return solution
     */
    public static double[] gaussSeidel(double[][] matrix, double[] resVector, int precision, double[] guess) {
        int n = matrix.length;
        for (int t = 0; t < precision; t++) {
            //iterates the row
            for (int i = 0; i < n; i++) {
                double temp = 0;
                //iterate the collum to calculate respective xi, yi, zi
                for (int j = 0; j < n; j++)
                    if (j != i)
                        temp += matrix[i][j] * guess[j];
                //updating the value of our solution
                guess[i] = (1 / matrix[i][i]) * (resVector[i] - temp);
            }
        }
        return guess;
    }
}