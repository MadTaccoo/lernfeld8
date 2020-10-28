package Backtracking;

public abstract class Sudoku
{
    public static int[][] grid;
    // field [row][column]
    public static boolean sudokuChecker(int row, int column, int number){

        // checking if the row contains the number
        for (int col = 0; col < grid.length; col++)
        {
            if(grid[row][col] == number) return false;
        }
        // checking if the columns contains the number
        for (int r = 0; r < grid.length; r++)
        {
            if (grid[r][column] == number) return false;
        }

        int root = (int) Math.sqrt(grid.length);
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

    public static void setVal(int x, int y, int n){
        if (sudokuChecker(x,y,n)){
            grid[x][y] = n;
        }else if (n == 0){
            grid[x][y] = n;
        }
    }

    public static boolean solve(){
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (grid[x][y] == 0){
                    for(int j = 1;j<=9;j++) {
                        if (sudokuChecker(x,y,j)){
                            setVal(x, y, j);
                            if(solve()){
                                return true;
                            }else {
                                setVal(x, y, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void print(){
        for (int row = 0; row < grid.length; row++)
        {
            for (int column = 0; column < grid.length; column++)
            {
                System.out.print(grid[row][column]+" ");
            }
            System.out.println();
        }
    }

}
