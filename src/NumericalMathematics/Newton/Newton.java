package NumericalMathematics.Newton;

/**
 * @author Daniel Wilke
 * @version 1.0
 * @category numerical mathematics
 * @description Class to solve a function for zero
 */
public abstract class Newton
{

    private static final int PLOT_SCALE = 75;
    private static Double x_abscissa_n;
    private static Double x_abscissa_p;

    /**
     * @param x_value X-Coordinate to calculate the y-coordinate of the function.
     * @return f Y-Coordinate of the function for given x.
     */
    private static double fun_0(double x_value)
    {
        double f, a = 0;

        f = a + x_value * 0;

        return f;
    }

    /**
     * @param x_value X-Coordinate to calculate the y-coordinate of the function.
     * @return f Y-Coordinate of the function for given x.
     */
    private static double fun_1(double x_value)
    {
        double f;
        double a = 7;
        double b = 4;

        f = a * x_value + b;

        return f;
    }

    /**
     * @param x_value X-Coordinate to calculate the y-coordinate of the function.
     * @return f Y-Coordinate of the function for given x.
     */
    private static double fun_2(double x_value)
    {
        double f;
        double a = 4;
        double b = 7;
        double c = -10;

        f = a * Math.pow(x_value, 2) + b * x_value + c;

        return f;
    }

    /**
     * @param x_value X-Coordinate to calculate the y-coordinate of the function.
     * @return f Y-Coordinate of the function for given x.
     */
    private static double fun_5(double x_value)
    {
        double f;
        double a = -7;
        double b = 8;
        double c = 12;
        double d = 1;
        double e = 4;
        double g = -9;

        f = a * Math.pow(x_value, 5) + b * Math.pow(x_value, 4) + c * Math.pow(x_value, 3) + d * Math.pow(x_value, 2) + e * x_value + g;

        return f;
    }

    /**
     * @param x_value X-Coordinate to calculate the y-coordinate of the function.
     * @return f Y-Coordinate of the function for given x.
     */
    private static double fun_cos(double x_value)
    {
        double f;
        double a = 1;
        double b = 17;
        double c = 0;

        f = a * Math.cos(b * (x_value - c));

        return f;
    }

    /**
     * @param x_value X-Coordinate to calculate the y-coordinate of the function.
     * @return f Y-Coordinate of the function for given x.
     */
    private static double fun_tan(double x_value)
    {
        double f;
        double a = 1;
        double b = -4;
        double c = 0;

        f = a * Math.tan(b * (x_value - c));

        return f;
    }

    /**
     * @param chosen Contains the choice which function should be calculated.
     * @return cSolve Returns the x-coordinate for the intersection with the x-axis in a given interval.
     */
    public static double controller(int chosen)
    {
        double cSolve;

        x_abscissa_n = 0.0;
        x_abscissa_p = 0.0;

        switch (chosen)
        {
            // func_0 [-3, 3]
            case 0:
                x_abscissa_n = -3.0;
                x_abscissa_p = 3.0;
                break;
            // func_1 [-3, 0]
            case 1:
                // func_2 [-3, 0]
            case 2:
                x_abscissa_n = -3.0;
                x_abscissa_p = 0.0;
                break;
            // func_5 [-2, 0]
            case 3:
                x_abscissa_n = -2.0;
                x_abscissa_p = 0.0;
                break;
            // func_cos [-0.2, 0]
            case 4:
                x_abscissa_n = -0.2;
                x_abscissa_p = 0.0;
                break;
            // func_tan [-1, -0.5]
            case 5:
                x_abscissa_n = -1.0;
                x_abscissa_p = -0.5;
                break;
            default:
                break;
        }

        cSolve = solve(x_abscissa_n, x_abscissa_p, chosen);

        return cSolve;
    }

    /**
     * @param x_abscissa_n X-coordinate for the negative interval side.
     * @param x_abscissa_p X-coordinate for the positive interval side.
     * @param chosen       Contains the choice which function should be calculated.
     * @return Returns abscissa_nod the x-coordinate for the intersection with the x-axis in a given interval.
     */
    private static double solve(double x_abscissa_n, double x_abscissa_p, int chosen)
    {
        double abscissa_nod;
        double ordinate_n;
        double ordinate_p;
        double abscissa_mid;
        double ordinate_mid;
        double temp;
        double x_tol = 1e-15;

        abscissa_nod = 0;
        abscissa_mid = 0;
        ordinate_n = get_ordinate(x_abscissa_n, chosen);
        ordinate_p = get_ordinate(x_abscissa_p, chosen);

        if ((ordinate_n + ordinate_p) > 0)
        {
            System.out.println("No intersection with x-axis!");
        }

        if ((ordinate_n * ordinate_p) == 0)
        {
            if (Math.abs(ordinate_n) == 0)
            {
                abscissa_nod = ordinate_n;
            }
            if (Math.abs(ordinate_p) == 0)
            {
                abscissa_nod = ordinate_p;
            }

        } else
        {
            while (Math.abs(x_abscissa_n - x_abscissa_p) > x_tol)
            {
                // calculating the median of x_abscissa_n and x_abscissa_p
                abscissa_mid = (x_abscissa_n + x_abscissa_p) / 2.0;

                // calculating the ordinate
                ordinate_mid = get_ordinate(abscissa_mid, chosen);
                ordinate_n = get_ordinate(x_abscissa_n, chosen);
                ordinate_p = get_ordinate(x_abscissa_p, chosen);

                if (ordinate_n > ordinate_p)
                {
                    temp = x_abscissa_n;
                    x_abscissa_n = x_abscissa_p;
                    x_abscissa_p = temp;
                }

                if (ordinate_mid > 0)
                {
                    x_abscissa_p = abscissa_mid;
                } else
                {
                    x_abscissa_n = abscissa_mid;
                }
            }
            abscissa_nod = abscissa_mid;
        }

        return abscissa_nod;
    }

    /**
     * @param x_value X-Coordinate to calculate the y-coordinate of the function.
     * @param chosen  Contains the choice which function should be calculated.
     * @return ordinate Returns the calculated ordinate for a given x-coordinate.
     */
    private static double get_ordinate(double x_value, int chosen)
    {
        double ordinate = 0;

        switch (chosen)
        {
            case 0:
                ordinate = fun_0(x_value);
                break;
            case 1:
                ordinate = fun_1(x_value);
                break;
            case 2:
                ordinate = fun_2(x_value);
                break;
            case 3:
                ordinate = fun_5(x_value);
                break;
            case 4:
                ordinate = fun_cos(x_value);
                break;
            case 5:
                ordinate = fun_tan(x_value);
                break;
            default:
                break;
        }
        return ordinate;
    }

    /**
     * @param chosen Contains the choice which function should be calculated.
     * @return coord[][] Returns a table with x-coordinates and the corresponding y-coordinates for a function in a given interval.
     */
    public static double[][] plottingTable(int chosen)
    {
        double steps;
        double counter;

        counter = x_abscissa_n * 2;

        double[][] cord = new double[2][PLOT_SCALE]; // cord[x-axis][y-axis]

        steps = ((x_abscissa_p - x_abscissa_n) * 2) / PLOT_SCALE;

        for (int i = 0; i < PLOT_SCALE; i++)
        {
            cord[0][i] = counter;
            cord[1][i] = get_ordinate(counter, chosen);
            counter += (2 * steps);
        }
        return cord;
    }

    //Remove main method after testing!
    public static void main(String[] args)
    {
        System.out.printf("%.4f%n", controller(2));
        double[][] test = plottingTable(2);
    }
}