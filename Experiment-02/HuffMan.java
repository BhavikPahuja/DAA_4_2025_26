import java.util.*;

class Node {
    char ch;
    int freq;
    Node left;
    Node right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(int freq, Node l, Node r) {
        this.freq = freq;
        left = l;
        right = r;
    }
}

public class HuffMan {

    private static void build(Node n, String c, Map<Character, String> m) {
        if (n == null)
            return;
        if (n.left == null && n.right == null)
            m.put(n.ch, c);
        build(n.left, c + "0", m);
        build(n.right, c + "1", m);
    }

    private static String decode(Node root, String s) {
        StringBuilder sb = new StringBuilder();
        Node curr = root;
        for (char bit : s.toCharArray()) {
            curr = (bit == '0') ? curr.left : curr.right;
            if (curr.left == null && curr.right == null) {
                sb.append(curr.ch);
                curr = root;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Map<Character, Integer> fm = new HashMap<>();
        for (char c : s.toCharArray())
            fm.put(c, fm.getOrDefault(c, 0) + 1);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));
        for (var e : fm.entrySet())
            pq.add(new Node(e.getKey(), e.getValue()));

        while (pq.size() > 1) {
            Node l = pq.poll(), r = pq.poll();
            pq.add(new Node(l.freq + r.freq, l, r));
        }

        Node root = pq.poll();
        Map<Character, String> codes = new TreeMap<>();
        build(root, "", codes);

        StringBuilder encoded = new StringBuilder();
        for (char c : s.toCharArray())
            encoded.append(codes.get(c));

        String encodedStr = encoded.toString();
        String decodedStr = decode(root, encodedStr);

        System.out.println("Codes: " + codes);
        System.out.println("Encoded: " + encodedStr);
        System.out.println("Decoded: " + decodedStr);
    }
}