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
}
