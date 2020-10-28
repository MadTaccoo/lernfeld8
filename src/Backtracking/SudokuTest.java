package Backtracking;

public class SudokuTest
{
    private static int gridSize = 4;
    private static int grid[][];
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

    public static void solve(){
        for (int y = 0; y < gridSize; y++)
        {
            for (int x = 0; x < gridSize; x++)
            {
                if (grid[y][x] == 0){
                    for (int n = 1; n <= gridSize; n++)
                    {
                        if (sudokuChecker(y,x,n)){
                            grid[y][x] = n;

                        }else{
                            grid[y][x] = 0;
                        }
                        solve();
                        break;
                    }
                }
            }
        }
    }

    public static void print(){
        for (int row = 0; row < gridSize; row++)
        {
            for (int column = 0; column < gridSize; column++)
            {
                System.out.print(grid[row][column]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        /*grid = new int[][]
                {{3, 0, 6, 5, 0, 8, 4, 0, 0 },
                        { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                        { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                        { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                        { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                        { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                        { 0, 0, 5, 2, 0, 6, 3, 0, 0 }};*/
        grid = new int[][]{{1,0,3,0},
                {0,0,2,1},
                {0,1,0,2},
                {2,4,0,0}};

        print();

        System.out.println();

        solve();

        print();
    }
}
