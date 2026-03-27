public class Solution {
    int rec(int level, int a[], int dp[]) {
        // pruning
        if (level < 0) {
            return 0;
        }
        // base case

        // cache check
        if (dp[level] != -1) {
            return dp[level];
        }
        // compute
        int ans = 1;
        for (int prev = 0; prev < level; prev++) {
            if (a[prev] < a[level]) {
                ans = Math.max(ans, 1 + rec(prev, a, dp));
            }
        }
        // store and return
        return dp[level] = ans;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int ans = 1;
        // int dp[][] = new int[n][n];
        // for (int i=0; i<n; i++) {
        // Arrays.fill(dp[i], -1);
        // }
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, rec(i, nums, dp));
        }
        return ans;
    }
}