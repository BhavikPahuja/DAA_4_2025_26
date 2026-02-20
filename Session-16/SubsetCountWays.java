import java.util.*;

public class SubsetCountWays {
    public static int solve(int i, int sum, int arr[], int target, int dp[][]) {
        if (i == arr.length) {
            if (sum == target)
                return 1;
            else
                return 0;
        }
        int take = 0;
        if (dp[i][sum] != -1)
            return dp[i][sum];
        if (sum + arr[i] <= target)
            take = solve(i + 1, sum + arr[i], arr, target, dp);
        int nonTake = solve(i + 1, sum, arr, target, dp);
        int ans = take + nonTake;
        dp[i][sum] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int n = sc.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter array of integers: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.println("Enter target value: ");
        int target = sc.nextInt();
        int dp[][] = new int[n + 1][target + 1];
        for (int row[] : dp)
            Arrays.fill(row, -1);
        System.out.println("Number of subsets: " + solve(0, 0, arr, target, dp));
        sc.close();
    }
}