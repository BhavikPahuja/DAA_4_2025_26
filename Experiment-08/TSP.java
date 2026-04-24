import java.util.Scanner;
import java.util.Locale;

public class TSP {
    private static final long DEFAULT_TIMEOUT_MS = 2_000;

    private static int numCities;
    private static int[][] distances;
    private static boolean[] visited;
    private static int minCost = Integer.MAX_VALUE;
    private static long operations;
    private static long deadlineNs;
    private static boolean timedOut;

    private static void findShortestPath(int currCity, int currentCost, int count) {
        if (timedOut) {
            return;
        }

        operations++;
        if (System.nanoTime() > deadlineNs) {
            timedOut = true;
            return;
        }

        operations++;
        if (count == numCities) {
            int finalCost = currentCost + distances[currCity][0];
            minCost = Math.min(minCost, finalCost);
            return;
        }

        for (int i = 0; i < numCities; i++) {
            operations++;
            if (!visited[i]) {
                visited[i] = true;
                findShortestPath(i, currentCost + distances[currCity][i], count + 1);
                visited[i] = false;

                if (timedOut) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        numCities = sc.nextInt();
        if (numCities <= 0) {
            System.out.println("Minimum Traveling Cost: Not Available");
            System.out.println("Execution Time (ms): 0.000");
            System.out.println("Approx Operations: 0");
            System.out.println("Feasibility: Completed");

            ExperimentLogger.log(
                    "TSP-BruteForce",
                    numCities,
                    0.0,
                    0,
                    "Completed",
                    "Invalid city count");
            sc.close();
            return;
        }

        distances = new int[numCities][numCities];
        visited = new boolean[numCities];
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                distances[i][j] = sc.nextInt();
            }
        }

        long timeoutMs = sc.hasNextLong() ? sc.nextLong() : DEFAULT_TIMEOUT_MS;
        if (timeoutMs <= 0) {
            timeoutMs = 1;
        }

        minCost = Integer.MAX_VALUE;
        visited[0] = true;
        operations = 0;
        timedOut = false;

        long startNs = System.nanoTime();
        deadlineNs = startNs + timeoutMs * 1_000_000L;
        findShortestPath(0, 0, 1);
        long elapsedNs = System.nanoTime() - startNs;
        double elapsedMs = elapsedNs / 1_000_000.0;

        String feasibility = timedOut ? "Timeout" : "Completed";
        if (!timedOut && minCost != Integer.MAX_VALUE) {
            System.out.println("Minimum Traveling Cost: " + minCost);
        } else if (timedOut) {
            System.out.println("Minimum Traveling Cost: Timeout");
        } else {
            System.out.println("Minimum Traveling Cost: Not Available");
        }

        System.out.printf(Locale.US, "Execution Time (ms): %.3f%n", elapsedMs);
        System.out.println("Approx Operations: " + operations);
        System.out.println("Feasibility: " + feasibility);

        String details;
        if (timedOut) {
            details = "Timeout";
        } else if (minCost == Integer.MAX_VALUE) {
            details = "No tour found";
        } else {
            details = "MinCost=" + minCost;
        }

        ExperimentLogger.log(
                "TSP-BruteForce",
                numCities,
                elapsedMs,
                operations,
                feasibility,
                details);

        sc.close();
    }
}