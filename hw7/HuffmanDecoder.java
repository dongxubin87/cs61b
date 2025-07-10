import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    public void main(String args[]) {
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie trie = (BinaryTrie) or.readObject();
        int symbolCount = (int) or.readObject();
        BitSequence allBits = (BitSequence) or.readObject();
        List<Character> decoded = new ArrayList<>();
        int tempLength = 0;
        for (int i = 0; i < symbolCount; i++) {
            Match match = trie.longestPrefixMatch(allBits);
            decoded.add(match.getSymbol());
            tempLength += match.getSequence().length();
            allBits = allBits.allButFirstNBits(tempLength);
        }
        char[] result = new char[symbolCount];
        for (int i = 0; i < symbolCount; i++) {
            result[i] = decoded.get(i);
        }
        FileUtils.writeCharArray(args[1], result);
    }
}
