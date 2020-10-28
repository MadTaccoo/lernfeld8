package Backtracking;

public class Sudoku
{


    // field [row][column]
    public static boolean checkPos(int[][] grid, int row, int column, int number)
    {

        // checking if the row contains the number
        for (int col = 0; col < grid.length; col++)
        {
            if (grid[row][col] == number) return false;
        }
        // checking if the columns contains the number
        for (int r = 0; r < grid.length; r++)
        {
            if (grid[r][column] == number) return false;
        }

        int root = (int) Math.sqrt(grid.length);
        // checking if the box contains the number
        int boxRow = row - (row % root);
        int boxColumn = column - (column % root);
        for (int r = 0; r < root; r++)
        {
            for (int c = 0; c < root; c++)
            {
                if (grid[boxRow + r][boxColumn + c] == number) return false;
            }
        }

        // the number is safe to use in this position
        return true;
    }

    public static boolean solve(int[][] grid, int n)
    {
        int y = -1;
        int x = -1;

        boolean isEmpty = true;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == 0)
                {
                    y = i;
                    x = j;

                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty)
            {
                break;
            }
        }

        if (isEmpty)
        {
            return true;
        }

        for (int num = 1; num < n; num++)
        {
            if (checkPos(grid, y, x, num))
            {
                grid[y][x] = num;
                if (solve(grid, n))
                {
                    return true;
                } else
                {
                    grid[y][x] = 0;
                }
            }
        }
        return false;
    }

    public static void print(int[][] grid)
    {
        for (int row = 0; row < grid.length; row++)
        {
            for (int column = 0; column < grid.length; column++)
            {
                System.out.print(grid[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        /*grid = new int[][]
                {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};*/
        int[][] grid = new int[][]
                {{1,0,3,0},
                {0,0,2,1},
                {0,1,0,2},
                {2,4,0,0}};

        print(grid);
        System.out.println();
        solve(grid,grid.length);
        print(grid);
    }
}
