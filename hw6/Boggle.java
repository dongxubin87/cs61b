import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Boggle {

    // File path of dictionary file
    static String dictPath = "words.txt";

    /**
     * Solves a Boggle puzzle.
     *
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     * The Strings are sorted in descending order of length.
     * If multiple words have the same length,
     * have them in ascending alphabetical order.
     */
    private static char[][] board;
    private static boolean[][] visited;
    private static Trie trie;
    private static int rows, cols;
    private static Set<String> wordSet;


    public static List<String> solve(int k, String boardFilePath) {
        loadBoard(boardFilePath);
        loadDictionary();
        wordSet = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(i, j, "", trie.getRoot());
            }
        }
        List<String> sorted = new ArrayList<>(wordSet);
        sorted.sort((a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });
        return sorted.subList(0, Math.min(k, sorted.size()));
    }

    private static void dfs(int i, int j, String prefix, Trie.TrieNode node) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j]) {
            return;
        }
        char ch = board[i][j];
        if (!node.children.containsKey(ch)) {
            return;
        }
        visited[i][j] = true;
        Trie.TrieNode tempNode = node.children.get(ch);
        String word = prefix + ch;
        if (tempNode.isWord) {
            wordSet.add(word);
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    dfs(i + dx, j + dy, word, tempNode);
                }
            }
        }
        visited[i][j] = false;
    }

    private static void loadBoard(String boardFilePath) {
        In inFile = new In(boardFilePath);
        List<String> lines = new ArrayList<>();
        while (inFile.hasNextLine()) {
            lines.add(inFile.readLine());
        }
        rows = lines.size();
        cols = lines.get(0).length();
        board = new char[rows][cols];
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            board[i] = lines.get(i).toCharArray();
        }
    }

    private static void loadDictionary() {
        In in = new In(dictPath);
        trie = new Trie();
        while (in.hasNextLine()) {
            trie.add(in.readLine());
        }
    }

    public static void main(String args[]) {
        String file = "smallBoard2.txt";

        for (String x : solve(27, file)) {
            System.out.println(x);
        }
    }
}
