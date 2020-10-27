package Backtracking;

public class Sudoku
{
    private static int grid[][];
    private static int gridSize = 9;
    // field [row][column]
    public static boolean sudokuChecker(int row, int column, int number){

        // checking if the row contains the number
        for (int col = 0; col < gridSize; col++)
        {
            if(grid[row][col] == number) return false;
        }
        // checking if the columns contains the number
        for (int r = 0; r < gridSize; r++)
        {
            if (grid[r][column] == number) return false;
        }

        int root = (int) Math.sqrt(gridSize);
        // checking if the box contains the number
        int boxRow =  row - (row % root);
        int boxColumn = column - (column % root);
        for (int r = 0; r < root; r++)
        {
            for (int c = 0; c < root; c++)
            {
                if (grid[boxRow+r][boxColumn+c] == number) return false;
            }
        }

        // the number is safe to use in this position
        return true;
    }

    public static boolean sudokuSolver(){
        for (int row = 0; row < gridSize; row++)
        {
            for (int column = 0; column < gridSize; column++)
            {
                if (grid[row][column] == 0){
                    for (int number = 1; number <= gridSize; number++)
                    {
                        if (sudokuChecker(row,column,number)){
                            grid[row][column] = number;

                        }else grid[row][column] = 0;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void print(){
        for (int row = 0; row < 9; row++)
        {
            for (int column = 0; column < 9; column++)
            {
                System.out.print(grid[row][column]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        grid = new int[][]
                {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 1, 9, 0, 0, 0, 0},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        print();
        boolean a = sudokuChecker(2,2,5);
        System.out.println(a);
        sudokuSolver();
        print();
    }
}
