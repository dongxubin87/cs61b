import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {
    private final Map<Character, Integer> frequencyTable;
    private Node root;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        this.frequencyTable = frequencyTable;
        MinPQ<Node> pq = new MinPQ<>();
        frequencyTable.forEach((key, val) -> {
            pq.insert(new Node(key, val, null, null));
        });
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.f + right.f, left, right);
            pq.insert(parent);
        }
        root = pq.delMin();
    }

    public Node getRoot() {
        return root;
    }

    private class Node implements Comparable<Node>, Serializable {
        private char ch;
        private int f;
        private Node left;
        private Node right;

        Node(char ch, int f, Node left, Node right) {
            this.ch = ch;
            this.f = f;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.f - that.f;
        }
    }


    public Match longestPrefixMatch(BitSequence querySequence) {
        Map<Character, BitSequence> lookupTable = buildLookupTable();
        String s = querySequence.toString();
        String prefix = "";
        for (int i = 0; i < s.length(); i++) {
            prefix += s.charAt(i);
            for (Character c : lookupTable.keySet()) {
                if (lookupTable.get(c).toString().equals(prefix)) {
                    return new Match(new BitSequence(prefix), c);
                }
            }
        }
        throw new IllegalArgumentException("Enter a legal querySequence");
    }

    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> lookupTable = new HashMap<>();
        buildLookupTableHelper(lookupTable, root, "");
        return lookupTable;
    }

    private void buildLookupTableHelper(Map<Character, BitSequence> lookupTable, Node x, String s) {
        if (!x.isLeaf()) {
            buildLookupTableHelper(lookupTable, x.left, s + '0');
            buildLookupTableHelper(lookupTable, x.right, s + '1');
        } else {
            lookupTable.put(x.ch, new BitSequence(s));
        }
    }
}
