class Solution {
    int rec(int level, int n, int s1, int s2, int a[], int dp[][]) {
        // pruning

        // base case
        if (level == n) {
            return Math.abs(s1 - s2);
        }
        // cache check
        if (dp[level][s1] != -1) {
            return dp[level][s1];
        }
        // compute
        int ans = (int) 1e9;
        ans = Math.min(ans, rec(level + 1, n, s1 + a[level], s2 - a[level], a, dp));
        ans = Math.min(ans, rec(level + 1, n, s1, s2, a, dp));
        // save and return
        return dp[level][s1] = ans;
    }

    public int minDifference(int arr[]) {
        int n = arr.length;
        int s = 0;
        for (int i : arr) {
            s += i;
        }
        int dp[][] = new int[n][s + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return rec(0, n, 0, s, arr, dp);
    }
}
