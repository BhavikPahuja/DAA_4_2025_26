import java.util.*;

public class SubsetLongest {
    public static ArrayList<Integer> longest(int dp[][], int arr[], int target) {
        ArrayList<Integer> ans = new ArrayList<>();
        int currentSum = 0;
        int INF = 99999;
        int i = 0;
        while (i < arr.length && currentSum <= target) {
            int take = -INF;
            if (currentSum + arr[i] <= target)
                take = 1 + dp[i + 1][currentSum + arr[i]];
            int skip = dp[i + 1][currentSum];
            if (take >= skip && take > -INF) {
                ans.add(arr[i]);
                currentSum += arr[i];
                i++;
            } else
                i++;
        }
        return ans;
    }

    public static int solve(int i, int sum, int arr[], int dp[][], int target) {
        if (i == arr.length) {
            if (sum == target)
                return 0;
            else
                return -99999;
        }
        if (dp[i][sum] != -1)
            return dp[i][sum];
        int take = -99999;
        if (sum + arr[i] <= target)
            take = 1 + solve(i + 1, sum + arr[i], arr, dp, target);
        int skip = solve(i + 1, sum, arr, dp, target);
        return dp[i][sum] = (take > skip) ? take : (take == skip) ? take : skip;
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
        System.out.println("Length of longest subset: " + solve(0, 0, arr, dp, target));
        System.out.println("Longest subset: " + longest(dp, arr, target));
        sc.close();
    }
}