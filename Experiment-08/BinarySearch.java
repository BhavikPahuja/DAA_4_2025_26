import java.util.*;

public class BinarySearch {
    private static class Metrics {
        long operations = 0;
    }

    private static int binarySearch(int[] a, int k, Metrics metrics) {
        int l = 0;
        int r = a.length - 1;

        while (l <= r) {
            metrics.operations++;
            int m = l + (r - l) / 2;

            metrics.operations++;
            if (a[m] == k) {
                return m;
            }

            metrics.operations++;
            if (a[m] < k) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if (n < 0) {
            System.out.println("Not Found");
            System.out.println("Execution Time (ms): 0.000");
            System.out.println("Approx Operations: 0");
            System.out.println("Feasibility: Completed");

            ExperimentLogger.log(
                    "BinarySearch",
                    n,
                    0.0,
                    0,
                    "Completed",
                    "Invalid input size");
            sc.close();
            return;
        }

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        Metrics metrics = new Metrics();
        long startNs = System.nanoTime();
        int idx = binarySearch(a, k, metrics);
        long elapsedNs = System.nanoTime() - startNs;
        double elapsedMs = elapsedNs / 1_000_000.0;

        String result = (idx >= 0) ? "Found" : "Not Found";
        System.out.println(result);
        System.out.printf(Locale.US, "Execution Time (ms): %.3f%n", elapsedMs);
        System.out.println("Approx Operations: " + metrics.operations);
        System.out.println("Feasibility: Completed");

        ExperimentLogger.log(
                "BinarySearch",
                n,
                elapsedMs,
                metrics.operations,
                "Completed",
                result);

        sc.close();
    }
}
