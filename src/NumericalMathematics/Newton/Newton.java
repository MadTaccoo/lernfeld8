package NumericalMathematics.Newton;

public abstract class Newton
{

    private static double fun_0(double x_value)
    {
        double f = 0;
        double a = 0;

        f = a;

        return f;
    }

    // [-3, 0]
    private static double fun_1(double x_value)
    {
        double f = 0;
        double a = 7;
        double b = 4;

        f = a * x_value + b;

        return f;
    }

    // [-3, 0]
    private static double fun_2(double x_value)
    {
        double f = 0;
        double a = 4;
        double b = 7;
        double c = -10;

        f = a * Math.pow(x_value, 2) + b * x_value + c;

        return f;
    }

    // [-0.2, 0]
    private static double fun_cos(double x_value)
    {
        double f = 0;
        double a = 1;
        double b = 17;
        double c = 0;

        f = a * Math.cos(b * (x_value - c));

        return f;
    }

    //[-2, 0]
    private static double fun_5(double x_value)
    {
        double f = 0;
        double a = -7;
        double b = 8;
        double c = 12;
        double d = 1;
        double e = 4;
        double g = -9;

        f = a * Math.pow(x_value, 5) + b * Math.pow(x_value, 4) + c * Math.pow(x_value, 3) + d * Math.pow(x_value, 2) + e * x_value + g;

        return f;
    }

    // [-1, -0.5]
    private static double fun_tan(double x_value)
    {
        double f = 0;
        double a = 1;
        double b = -4;
        double c = 0;

        f = a * Math.tan(b * (x_value - c));

        return f;
    }


    private static double solve(double x_abscissa_n, double x_abscissa_p, int chosen)
    {
        double abscissa_nod = 0;
        double ordinate_n = 0;
        double ordinate_p = 0;
        double abscissa_mid = 0;
        double ordinate_mid = 0;
        double temp = 0;

        ordinate_n = get_ordinate(x_abscissa_n, chosen);
        ordinate_p = get_ordinate(x_abscissa_p, chosen);

        if ((ordinate_n + ordinate_p) > 0)
        {
            System.out.println("No intersection with x-axis!");
        }


        return abscissa_nod;
    }

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
}