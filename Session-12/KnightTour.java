class Solution {
    private int[][] moves = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 } };

    public ArrayList<ArrayList<Integer>> knightTour(int n) {
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = -1;
        board[0][0] = 0;
        if (solve(0, 0, 1, n, board)) {
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < n; j++)
                    row.add(board[i][j]);
                result.add(row);
            }
            return result;
        }
        return new ArrayList<>();
    }

    private boolean solve(int r, int c, int count, int n, int[][] board) {
        if (count == n * n)
            return true;
        List<int[]> nextMoves = new ArrayList<>();
        for (int[] m : moves) {
            int nr = r + m[0];
            int nc = c + m[1];
            if (isValid(nr, nc, n, board))
                nextMoves.add(new int[] { nr, nc });
        }
        nextMoves.sort((a, b) -> getDegree(a[0], a[1], n, board) - getDegree(b[0], b[1], n, board));
        for (int[] next : nextMoves) {
            int nr = next[0];
            int nc = next[1];
            board[nr][nc] = count;
            if (solve(nr, nc, count + 1, n, board))
                return true;
            board[nr][nc] = -1;
        }
        return false;
    }

    private int getDegree(int r, int c, int n, int[][] board) {
        int count = 0;
        for (int[] m : moves)
            if (isValid(r + m[0], c + m[1], n, board))
                count++;
        return count;
    }

    private boolean isValid(int r, int c, int n, int[][] board) {
        return r >= 0 && c >= 0 && r < n && c < n && board[r][c] == -1;
    }
}