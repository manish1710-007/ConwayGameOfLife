public class ConwayGameOfLife {
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;

    public static void main(String[] args) {
        boolean[][] board = new boolean[ROWS][COLUMNS];
        initializeBoard(board);
        runGame(board);
    }

    public static void initializeBoard(boolean[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = Math.random() < 0.5;
            }
        }
    }

    public static void runGame(boolean[][] board) {
        while (true) {
            printBoard(board);
            board = getNextGeneration(board);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printBoard(boolean[][] board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] ? "■ " : "□ ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean[][] getNextGeneration(boolean[][] board) {
        boolean[][] newBoard = new boolean[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int liveNeighbors = countLiveNeighbors(board, i, j);
                if (board[i][j]) {
                    newBoard[i][j] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    newBoard[i][j] = liveNeighbors == 3;
                }
            }
        }
        return newBoard;
    }

    public static int countLiveNeighbors(boolean[][] board, int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < ROWS && j >= 0 && j < COLUMNS && !(i == row && j == col) && board[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
