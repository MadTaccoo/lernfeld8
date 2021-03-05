package NumericalMathematics.Newton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NewtonTest {
    ArrayList<FunctionHelper> test = new ArrayList<>(Arrays.asList(
            new FunctionHelper(2, 3),
            new FunctionHelper(5, 2),
            new FunctionHelper(-5, 1),
            new FunctionHelper(-5, 0)
    ));
    ArrayList<FunctionHelper> test2 = new ArrayList<>(Arrays.asList(
            new FunctionHelper(2, 2),
            new FunctionHelper(-5, 0)
    ));
    ArrayList<FunctionHelper> test3 = new ArrayList<>(Arrays.asList(
            new FunctionHelper(2, 1),
            new FunctionHelper(6, 0)
    ));

    double[][] roots = {
            {-3.051, -0.671,1.222},
            {-1.581, 1.581},
            {-3},
    };

    double[][] interval = {
            {-4, -1,1},
            {-2, 1},
            {-4},
    };
    ArrayList[] ls = new ArrayList[]{test, test2, test3};

    @ParameterizedTest
    @ValueSource(ints = {0,1,2})
    public void test(int i){
        Function f = new Function(ls[i]);
        for (int j = 0; j < roots[i].length; j++) {
            Assertions.assertEquals(Newton.newton(f,interval[i][j],100),roots[i][j],0.1);
        }

    }
}
