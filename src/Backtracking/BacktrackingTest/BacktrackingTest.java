package Backtracking.BacktrackingTest;

import Backtracking.Sudoku;

public abstract class BacktrackingTest
{

    private static int[][] grid;
    private static int[][] grid_II;

    public static int test2x2grid()
    {
        int ret = 0;
        grid = new int[][]{
                {0, 2, 4, 0},
                {1, 0, 0, 3},
                {4, 0, 0, 2},
                {0, 1, 3, 0}
        };
        grid_II = new int[][]{
                {3, 2, 4, 1},
                {1, 4, 2, 3},
                {4, 3, 1, 2},
                {2, 1, 3, 4}
        };
        Sudoku.grid = grid;
        Sudoku.solve();

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid.length; j++)
            {
                if (grid[i][j] != grid_II[i][j])
                {
                    ret = -1;
                }
            }
        }
        if (ret == 0)
        {
            ret = 1;
        }

        return ret;
    }

    public static int test3x3grid()
    {
        int ret = 0;
        grid = new int[][]{
                {0, 7, 0, 0, 2, 0, 0, 4, 6},
                {0, 6, 0, 0, 0, 0, 8, 9, 0},
                {2, 0, 0, 8, 0, 0, 7, 1, 5},
                {0, 8, 4, 0, 9, 7, 0, 0, 0},
                {7, 1, 0, 0, 0, 0, 0, 5, 9},
                {0, 0, 0, 1, 3, 0, 4, 8, 0},
                {6, 9, 7, 0, 0, 2, 0, 0, 8},
                {0, 5, 8, 0, 0, 0, 0, 6, 0},
                {4, 3, 0, 0, 8, 0, 0, 7, 0}
        };
        grid_II = new int[][]{
                {8, 7, 5, 9, 2, 1, 3, 4, 6},
                {3, 6, 1, 7, 5, 4, 8, 9, 2},
                {2, 4, 9, 8, 6, 3, 7, 1, 5},
                {5, 8, 4, 6, 9, 7, 1, 2, 3},
                {7, 1, 3, 2, 4, 8, 6, 5, 9},
                {9, 2, 6, 1, 3, 5, 4, 8, 7},
                {6, 9, 7, 4, 1, 2, 5, 3, 8},
                {0, 5, 8, 3, 7, 9, 2, 6, 4},
                {1, 3, 2, 5, 8, 6, 9, 7, 1}
        };

        Sudoku.grid = grid;
        Sudoku.solve();

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid.length; j++)
            {
                if (grid[i][j] != grid_II[i][j])
                {
                    ret = -1;
                }
            }
        }
        if (ret == 0)
        {
            ret = 1;
        }
        return ret;
    }

    public static void main(String[] args)
    {
        System.out.println(test2x2grid());
        System.out.println(test3x3grid());
    }
}
