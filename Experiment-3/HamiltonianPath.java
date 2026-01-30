import java.util.*;

class Solution {
    boolean dfs(int u, boolean[] seen, int cnt, int n, Map<Integer, Set<Integer>> adj) {
        if (cnt == n)
            return true;

        seen[u] = true;

        // Get neighbors from the map; handle cases where a node might have no edges
        Set<Integer> neighbors = adj.getOrDefault(u, Collections.emptySet());

        for (int v : neighbors) {
            if (!seen[v]) {
                if (dfs(v, seen, cnt + 1, n, adj))
                    return true;
            }
        }

        // Backtrack
        seen[u] = false;
        return false;
    }

    boolean check(int n, int m, ArrayList<ArrayList<Integer>> edges) {
        // Map to store adjacency: Node -> Set of Neighbors
        Map<Integer, Set<Integer>> adj = new HashMap<>();

        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            adj.computeIfAbsent(u, k -> new HashSet<>()).add(v);
            adj.computeIfAbsent(v, k -> new HashSet<>()).add(u);
        }

        boolean[] seen = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (dfs(i, seen, 1, n, adj))
                return true;
        }

        return false;
    }
}