import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SubsetSum {
    private static final long DEFAULT_TIMEOUT_MS = 2_000;

    private static class Metrics {
        long operations = 0;
    }

    private static class DecisionResult {
        boolean found = false;
        boolean timedOut = false;
        List<Integer> subset = new ArrayList<>();
    }

    private static boolean decisionDfs(
            int index,
            int[] values,
            int target,
            int runningSum,
            List<Integer> currentSubset,
            long deadlineNs,
            Metrics metrics,
            DecisionResult result) {
        if (System.nanoTime() > deadlineNs) {
            result.timedOut = true;
            return false;
        }

        metrics.operations++;
        if (runningSum == target) {
            result.found = true;
            result.subset = new ArrayList<>(currentSubset);
            return true;
        }

        metrics.operations++;
        if (index == values.length) {
            return false;
        }

        currentSubset.add(values[index]);
        metrics.operations++;
        if (decisionDfs(index + 1, values, target, runningSum + values[index], currentSubset, deadlineNs, metrics,
                result)) {
            return true;
        }
        if (result.timedOut) {
            return false;
        }

        currentSubset.remove(currentSubset.size() - 1);
        metrics.operations++;
        return decisionDfs(index + 1, values, target, runningSum, currentSubset, deadlineNs, metrics, result);
    }

    private static boolean verifySubset(List<Integer> subset, int target, Metrics metrics) {
        int sum = 0;
        for (int value : subset) {
            metrics.operations++;
            sum += value;
        }

        metrics.operations++;
        return sum == target;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if (n < 0) {
            System.out.println("Decision Result: Not Found");
            System.out.println("Execution Time (ms): 0.000");
            System.out.println("Approx Operations: 0");
            System.out.println("Feasibility: Completed");
            System.out.println("Verification Result: Skipped (invalid input size)");

            ExperimentLogger.log(
                    "SubsetSum-Decision",
                    n,
                    0.0,
                    0,
                    "Completed",
                    "Invalid input size");
            ExperimentLogger.log(
                    "SubsetSum-Verification",
                    n,
                    0.0,
                    0,
                    "Completed",
                    "Skipped (invalid input size)");
            sc.close();
            return;
        }

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        long timeoutMs = sc.hasNextLong() ? sc.nextLong() : DEFAULT_TIMEOUT_MS;
        if (timeoutMs <= 0) {
            timeoutMs = 1;
        }

        Metrics decisionMetrics = new Metrics();
        DecisionResult decisionResult = new DecisionResult();
        List<Integer> workingSubset = new ArrayList<>();

        long decisionStartNs = System.nanoTime();
        long deadlineNs = decisionStartNs + timeoutMs * 1_000_000L;
        decisionDfs(0, values, target, 0, workingSubset, deadlineNs, decisionMetrics, decisionResult);
        long decisionElapsedNs = System.nanoTime() - decisionStartNs;
        double decisionElapsedMs = decisionElapsedNs / 1_000_000.0;

        String decisionFeasibility = decisionResult.timedOut ? "Timeout" : "Completed";
        String decisionOutcome;
        if (decisionResult.timedOut) {
            decisionOutcome = "Timeout";
        } else {
            decisionOutcome = decisionResult.found ? "Found" : "Not Found";
        }

        System.out.println("Decision Result: " + decisionOutcome);
        if (decisionResult.found) {
            System.out.println("Witness Subset: " + decisionResult.subset);
        }
        System.out.printf(Locale.US, "Execution Time (ms): %.3f%n", decisionElapsedMs);
        System.out.println("Approx Operations: " + decisionMetrics.operations);
        System.out.println("Feasibility: " + decisionFeasibility);

        ExperimentLogger.log(
                "SubsetSum-Decision",
                n,
                decisionElapsedMs,
                decisionMetrics.operations,
                decisionFeasibility,
                decisionOutcome);

        Metrics verificationMetrics = new Metrics();
        double verificationElapsedMs = 0.0;
        String verificationOutcome;
        if (decisionResult.found && !decisionResult.timedOut) {
            long verificationStartNs = System.nanoTime();
            boolean valid = verifySubset(decisionResult.subset, target, verificationMetrics);
            long verificationElapsedNs = System.nanoTime() - verificationStartNs;
            verificationElapsedMs = verificationElapsedNs / 1_000_000.0;
            verificationOutcome = valid ? "Valid" : "Invalid";

            System.out.println("Verification Result: " + verificationOutcome);
            System.out.printf(Locale.US, "Verification Time (ms): %.3f%n", verificationElapsedMs);
            System.out.println("Verification Operations: " + verificationMetrics.operations);
            System.out.println("Verification Feasibility: Completed");
        } else if (decisionResult.timedOut) {
            verificationOutcome = "Skipped (decision timeout)";
            System.out.println("Verification Result: " + verificationOutcome);
        } else {
            verificationOutcome = "Skipped (no subset found)";
            System.out.println("Verification Result: " + verificationOutcome);
        }

        ExperimentLogger.log(
                "SubsetSum-Verification",
                n,
                verificationElapsedMs,
                verificationMetrics.operations,
                "Completed",
                verificationOutcome);

        sc.close();
    }
}
