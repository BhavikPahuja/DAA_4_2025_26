import java.util.*;

class Node {
    int freq;
    Node left, right;

    Node(int f) {
        this.freq = f;
    }
}

public class HuffmanCoding {
    public void generateCodes(Node root, String code, ArrayList<String> res) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            res.add(code);
            return;
        }
        generateCodes(root.left, code + "0", res);
        generateCodes(root.right, code + "1", res);
    }

    public ArrayList<String> huffmanCodes(String S, int[] f, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for (int i = 0; i < N; i++)
            pq.add(new Node(f[i]));

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node merged = new Node(left.freq + right.freq);
            merged.left = left;
            merged.right = right;
            pq.add(merged);
        }

        ArrayList<String> res = new ArrayList<>();
        generateCodes(pq.peek(), "", res);
        return res;
    }

    public static void main(String[] args) {
        String S = "abcdef";
        int[] f = { 5, 9, 12, 13, 16, 45 };
        int N = 6;

        HuffmanCoding hc = new HuffmanCoding();
        for (String code : hc.huffmanCodes(S, f, N))
            System.out.print(code + " ");
    }
}