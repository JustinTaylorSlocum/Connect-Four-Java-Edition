public class C4Model {
    /*
    R = Red
    B = Blue
    O = Nothing
     */
    private char[][] grid;

    public C4Model() {
        grid = new char[7][6];
        populateGrid();
    }

    private void populateGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int k = 0; k < grid[i].length; k++) {
                grid[i][k] = 'O';
            }
        }
    }

    private boolean checkVertical() {

        return true;
    }

    private boolean checkHorizontal() {

        return true;
    }

    private boolean checkDiagonal() {

        return true;
    }

    public void showGrid() {
        for (int i = 0; i < grid.length; i++) {
            System.out.println();
            for (int k = 0; k < grid[i].length; k++) {
                System.out.print(grid[i][k]);
            }
        }
    }

}
