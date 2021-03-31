package Backtracking.BacktrackingTest;

import Backtracking.Sudoku;
import Database.MySqlCon;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BacktrackingTest
{
    static int[][] grid;
    static int[][] grid_II;

    static int[][] grid3x;
    static int[][] grid_II3x;

    @BeforeAll
    public static void setUp(){
        ArrayList<String> matrix = MySqlCon.query("SELECT data FROM tbl_backtrackDataSource;");
        ArrayList<String> sol = MySqlCon.query("SELECT data FROM tbl_backtrackTestRest;");
        matrix.remove(0);
        sol.remove(0);

        grid = stringToMatrix(matrix.get(0));
        grid_II = stringToMatrix(sol.get(0));

        grid3x = stringToMatrix(matrix.get(1));
        grid_II3x = stringToMatrix(sol.get(1));

    }

    @Test
    public void test2x2grid()
    {
        Sudoku.grid = grid;
        Sudoku.solve();
        Assertions.assertArrayEquals(grid,grid_II);
        MySqlCon.query("SELECT addBacktrackRes(1,1,"+ Arrays.deepEquals(grid,grid_II) +");");
    }

    @Test
    public void test3x3grid() {
        Sudoku.grid = grid3x;
        Sudoku.solve();
        Assertions.assertArrayEquals(grid3x,grid_II3x);
        MySqlCon.query("SELECT addBacktrackRes(2,2,"+ Arrays.deepEquals(grid3x,grid_II3x) +");");

    }

    public static int[][] stringToMatrix(String source){
        String[] rows = source.split("\n");
        int[][] ret = new int[rows.length][rows.length];
        for (int i = 0; i < rows.length; i++) {
            String[] cells = rows[i].split(",");
            for (int j = 0; j < cells.length; j++) {
                ret[i][j] = Integer.parseInt(cells[j].replace(" ","").replace("\r",""));
            }
        }
        return ret;
    }
}
