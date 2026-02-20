class Solution {
  // int rec(int n) {
  //   if (n <= 1) return 1;
  //   return rec(n-1) + rec(n-2);
  // }
  public int climbStairs(int n) {
    // return rec(n);


    // int dp[] = new int[n+1];
    // dp[0] = 1;
    // dp[1] = 1;
    // for (int i=2; i<=n; i++) {
    //   dp[i] = dp[i-1] + dp[i-2];
    // }
    // return dp[n];


    int p1 = 1;
    int p2 = 1;
    for (int i=2; i<=n; i++) {
      int curr = p1 + p2;
      p2 = p1;
      p1 = curr;
    }
    return p1;
  }
}