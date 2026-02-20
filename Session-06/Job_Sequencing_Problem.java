import java.util.*;

class Pair {
    int deadline, profit;

    public Pair(int deadline, int profit) {
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Job_Sequencing_Problem {
    static int[] parent;

    static int find(int i) {
        if (i == parent[i])
            return i;
        return parent[i] = find(parent[i]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt())
            return;
        int n = sc.nextInt();
        Pair[] jobs = new Pair[n];
        int maxDeadline = 0;

        int[] deadlines = new int[n];
        for (int i = 0; i < n; i++)
            deadlines[i] = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int p = sc.nextInt();
            jobs[i] = new Pair(deadlines[i], p);
            maxDeadline = Math.max(maxDeadline, deadlines[i]);
        }

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        parent = new int[maxDeadline + 1];
        for (int i = 0; i <= maxDeadline; i++)
            parent[i] = i;

        int count = 0, totalProfit = 0;

        for (Pair job : jobs) {
            int availableSlot = find(job.deadline);
            if (availableSlot > 0) {
                parent[availableSlot] = find(availableSlot - 1);
                count++;
                totalProfit += job.profit;
            }
        }

        System.out.println(count + " " + totalProfit);
        sc.close();
    }
}