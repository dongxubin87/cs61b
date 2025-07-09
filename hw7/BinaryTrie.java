import java.io.Serializable;
import java.util.Map;

public class BinaryTrie implements Serializable {
    private final Map<Character, Integer> frequencyTable;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        this.frequencyTable = frequencyTable;
    }

    private class Node {
        private char c;
        private int f;
        private Node left;
        private Node right;

        Node(char c, int f, Node left, Node right) {
            this.c = c;
            this.f = f;
            this.left = left;
            this.right = right;
        }
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        querySequence.toString();
        return null;
    }

    public Map<Character, BitSequence> buildLookupTable() {
        return null;
    }
}
