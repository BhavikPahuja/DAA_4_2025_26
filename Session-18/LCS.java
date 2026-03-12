// class Solution {
//   int rec(int i, int j, String s1, String s2, int dp[][]) {
//     if (i < 0 || j < 0) return 0;
//     if (dp[i][j] != -1) return dp[i][j];
//     if (s1.charAt(i) == s2.charAt(j)) dp[i][j] = 1 + rec(i-1, j-1, s1, s2, dp);
//     else dp[i][j] = Math.max(rec(i-1, j, s1, s2, dp), rec(i, j-1, s1, s2, dp));
//     return dp[i][j];
//   }
//   public int longestCommonSubsequence(String text1, String text2) {
//     int n = text1.length(), m = text2.length();
//     int dp[][] = new int[n][m];
//     for (int i=0; i<n; i++) Arrays.fill(dp[i], -1);
//     return rec(n - 1, m - 1, text1, text2, dp);
//   }
// }

class Solution {
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        int prev[] = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            int curr[] = new int[m + 1];
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    curr[j] = 1 + prev[j - 1];
                else
                    curr[j] = Math.max(curr[j - 1], prev[j]);
            }
            prev = curr;
        }
        return prev[m];
    }
}