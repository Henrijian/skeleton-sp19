public class BubbleGrid {
    /** Invariants
     * grid: record the original grid of bubble, 1 represent bubble, 0 represent empty. */
    private int[][] grid;

    /** Construct from the grid ,
     * first dimension represents rows, from top to bottom,
     * second dimension represents columns, from left to right,
     * each row must have same number of columns. */
    public BubbleGrid(int[][] bubbleGrid) {
        int rowCount = bubbleGrid.length;
        int colCount = bubbleGrid[0].length;
        this.grid = new int[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            this.grid[row] = bubbleGrid[row].clone();
        }
    }

    /** Throw darts in sequence at position in [row, column] format,
     *  and return the sequence of number of fallen bubbles(not include popped bubble). */
    public int[] popBubbles(int[][] darts) {
        /* Create the grid of bubbles to represent darts have been thrown. */
        int rowCount = this.grid.length;
        int colCount = this.grid[0].length;
        int[][] dartedGrid = new int[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            dartedGrid[row] = this.grid[row].clone();
        }
        for (int[] dart : darts) {
            int dartRow = dart[0];
            int dartCol = dart[1];
            dartedGrid[dartRow][dartCol] = 0;
        }

        /* Initiate a disjoint sets to represent each connected bubble sets. */
        int cellCount = colCount * rowCount;
        UnionFind bubbleSets = new UnionFind(cellCount + 1);
        int sourceNodeIdx = cellCount;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                int cellIdx = row * colCount + col;
                int cellVal = dartedGrid[row][col];
                if (cellVal == 0) {
                    continue;
                }
                if (row == 0) {
                    bubbleSets.union(cellIdx, sourceNodeIdx);
                }
                int upRow = row - 1;
                if (0 <= upRow && dartedGrid[upRow][col] == 1) {
                    int upCellIdx = upRow * colCount + col;
                    bubbleSets.union(cellIdx, upCellIdx);
                }
                int leftCol = col - 1;
                if (0 <= leftCol && dartedGrid[row][leftCol] == 1) {
                    int leftCellIdx = row * colCount + leftCol;
                    bubbleSets.union(cellIdx, leftCellIdx);
                }
            }
        }

        int[] result = new int[darts.length];
        for (int i = darts.length - 1; i >= 0; i--) {
            int dartRow = darts[i][0];
            int dartCol = darts[i][1];
            int dartIdx = dartRow * colCount + dartCol;
            if (this.grid[dartRow][dartCol] == 0) {
                result[i] = 0;
            } else {
                int afterBubblesCount = bubbleSets.sizeOf(sourceNodeIdx);
                dartedGrid[dartRow][dartCol] = 1;
                int[][] adjacentCells = {{dartRow - 1, dartCol}, {dartRow + 1, dartCol},
                        {dartRow, dartCol - 1}, {dartRow, dartCol + 1}};
                for (int[] adjacentCell: adjacentCells) {
                    int adjacentRow = adjacentCell[0];
                    int adjacentCol = adjacentCell[1];
                    int adjacentIdx = adjacentRow * colCount + adjacentCol;
                    if (0 <= adjacentRow && adjacentRow < rowCount && 0 <= adjacentCol && adjacentCol < colCount &&
                            dartedGrid[adjacentRow][adjacentCol] == 1) {
                        bubbleSets.union(dartIdx, adjacentIdx);
                    }
                }
                int beforeBubblesCount = bubbleSets.sizeOf(sourceNodeIdx);
                result[i] = beforeBubblesCount - afterBubblesCount - 1; // -1 = dart
            }
        }
        return result;
    }

    public static void main(String[] argv) {
        int[][] grid = {{1, 1, 0},{1, 0, 0}, {1, 1, 0},{1, 1, 1}};
        BubbleGrid bubbleGrid = new BubbleGrid(grid);
        int[][] darts = {{2, 2}, {2, 0}};
        int[] poppedBubbles = bubbleGrid.popBubbles(darts);
    }
}
