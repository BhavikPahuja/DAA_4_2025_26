class Solution {
    public boolean isSafe(List<String> board, int row, int col, int n) {
        for (int i = 0; i < n; i++)
            if (board.get(i).charAt(col) == 'Q')
                return false;
        for (int j = 0; j < n; j++)
            if (board.get(row).charAt(j) == 'Q')
                return false;
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board.get(i).charAt(j) == 'Q')
                return false;
        for (int i = row, j = col; i >= 0 && j < n; i--, j++)
            if (board.get(i).charAt(j) == 'Q')
                return false;
        return true;
    }

    public void nQueens(int n, List<List<String>> ans, List<String> board, int row) {
        if (row == n) {
            ans.add(new ArrayList<>(board));
            return;
        }
        for (int j = 0; j < n; j++) {
            if (isSafe(board, row, j, n)) {
                StringBuilder sb = new StringBuilder(board.get(row));
                sb.setCharAt(j, 'Q');
                board.set(row, sb.toString());
                nQueens(n, ans, board, row + 1);
                sb.setCharAt(j, '.');
                board.set(row, sb.toString());
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++)
            board.add(".".repeat(n));
        nQueens(n, ans, board, 0);
        return ans;
    }
}