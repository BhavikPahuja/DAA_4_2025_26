class Solution {
  boolean check(int row, int col, int n, char b[][]) {
    for (int i=0; i<row; i++) {
      if (b[i][col] == 'Q') {
        return false;
      }
    }
    for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
      if (b[i][j] == 'Q') {
        return false;
      }
    }
    for (int i=row-1, j=col+1; i>=0 && j<n; i--, j++) {
      if (b[i][j] == 'Q') {
        return false;
      }
    }
    return true;
  }
  List<String> construct(char b[][]) {
    List<String> ans = new ArrayList<>();
    for (char i[] : b) {
      ans.add(new String(i));
    }
    return ans;
  }
  void rec(int row, int n, char b[][], List<List<String>> ans) {
    if (row == n) {
      ans.add(construct(b));
    }
    for (int i=0; i<n; i++) {
      if (check(row, i, n, b)) {
        b[row][i] = 'Q';
        rec(row + 1, n, b, ans);
        b[row][i] = '.';
      }
    }
  }
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> ans = new ArrayList<>();
    char b[][] = new char[n][n];
    for (int i=0; i<n; i++) {
      Arrays.fill(b[i], '.');
    }
    rec(0, n, b, ans);
    return ans;
  }
}