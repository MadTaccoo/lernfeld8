package Euclidean_Algorithm;

/**
 * Implementation of the Euclidean Algorithm, for finding
 * the greatest common divisor (largest number that divides two numbers without
 * an remainder) of two integers
 *
 * @author Wutthichai Laphutama
 */
public abstract class EuclideanAlgorithm {
    /**
     * Calculate the greatest commond divisor
     * by repeatedly dividing the divisor by the remainder
     * until the remainder is 0.
     * @param a (int) value
     * @param b (int) value
     * @return greatest common divisor
     */
    public static int euclid(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    /**
     * Recursive method of the algorithm
     * @param a (int) value
     * @param b (int) value
     * @return greatest common divisor
     */
    public static int euclidRec(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return euclidRec(b, a % b);
        }
    }
}
