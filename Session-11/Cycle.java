import java.util.*;

public class Cycle {
    static List<List<Integer>> adjList;
    static List<Integer> path;
    static boolean[] visited;
    static int V;

    static boolean hasEdge(int u, int v) {
        for (int x : adjList.get(u))
            if (x == v)
                return true;
        return false;
    }

    static boolean solve(int u, int count) {
        if (count == V)
            return hasEdge(u, path.get(0));
        for (int v : adjList.get(u)) {
            if (!visited[v]) {
                visited[v] = true;
                path.add(v);
                if (solve(v, count + 1))
                    return true;
                visited[v] = false;
                path.remove(path.size() - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        V = 5;
        adjList = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adjList.add(new ArrayList<>());
        adjList.get(0).addAll(Arrays.asList(1, 3));
        adjList.get(1).addAll(Arrays.asList(0, 2, 3, 4));
        adjList.get(2).addAll(Arrays.asList(1, 4));
        adjList.get(3).addAll(Arrays.asList(0, 1, 4));
        adjList.get(4).addAll(Arrays.asList(1, 2, 3));
        visited = new boolean[V];
        path = new ArrayList<>();
        path.add(0);
        visited[0] = true;
        if (solve(0, 1)) {
            for (int i : path)
                System.out.print(i + " ");
            System.out.println();
        } else
            System.out.println("No path exists");
    }
}